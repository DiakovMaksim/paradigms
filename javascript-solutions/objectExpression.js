"use strict"
const operations = new Map();
const variables = new Map([
    ["x", 0],
    ["y", 1],
    ["z", 2]
]);
function createElement(toString, evaluate) {
    const result = function (argument) {
        this.value = argument;
        this.getName = argument.toString();
    }
    result.prototype.toString = toString;
    result.prototype.evaluate = evaluate;
    result.prototype.prefix = result.prototype.toString;
    return result;
}
const Const = createElement(function () { return (this.value).toString() }, function () { return this.value });

const Variable = createElement(function () { return this.getName }, function (...args) { return args[variables.get(this.getName)] });

function AbstractOperation(f, string, ...args) {
    this.f = f;
    this.getName = string;
    this.args = args;
}

AbstractOperation.prototype.toString = function () {
    return `${this.args.join(" ")} ${this.getName}`;
}
AbstractOperation.prototype.prefix = function () {
    return `(${this.getName} ${this.args.map(i => i.prefix()).join(" ")})`;
}
AbstractOperation.prototype.evaluate = function (...args) {
    return this.f(...(this.args.map(i => i.evaluate(...args))));
}

function createOperation(f, n, string) {
    const result = function () {
        AbstractOperation.call(this, f, string, ...arguments);
    }
    result.prototype = Object.create(AbstractOperation.prototype);
    result.f = f;
    result.numberOfArguments = n;
    result.getName = string;
    operations.set(string, result);
    return result;
}

const Add = createOperation((a, b) => a + b, 2, "+");
const Subtract = createOperation((a, b) => a - b, 2, "-");
const Multiply = createOperation((a, b) => a * b, 2, "*");
const Divide = createOperation((a, b) => a / b, 2, "/");
const Negate = createOperation(a => -a, 1, "negate");
const Exp = createOperation(a => Math.exp(a), 1, "exp");
const Ln = createOperation(a => Math.log(a), 1, "ln");
const Sum = createOperation((...args) => args.reduce((a, b) => a + b, 0), 0, "sum");
const Avg = createOperation((...args) => args.reduce((a, b) => a + b, 0) / args.length, 0, "avg");
const parse = expression => {
    expression = expression.split(' ').filter(part => part.length > 0);
    let stack = [];
    expression.map(s => {
            if (operations.has(s)) {
                const currentOperation = operations.get(s);
                let elems = stack.slice(-currentOperation.numberOfArguments);
                stack = stack.slice(0, -currentOperation.numberOfArguments);
                stack.push(new currentOperation(...elems));
            } else if (variables.has(s)) {
                stack.push(new Variable(s));
            } else {
                stack.push(new Const(+s));
            }
        }
    )
    return stack.pop();
}
const parsePrefix = string => {
    string = string.replaceAll("(", " ( ").replaceAll(")", " ) ").split(" ").filter(part => part.length > 0);
    let bracketFlag = false;
    let balance = 0;
    let nextElement = null;
    let stack = string.reduceRight(function (stack, element, index) {
            if (element !== "(" && bracketFlag) {
                throw new BracketsError(element, index, `expect "(" before operation ${nextElement}`);
            }
            if (element === ")") {
                balance++;
                stack.push(element);
            } else if (element === "(") {
                if (nextElement !== null && !operations.has(nextElement)) {
                    throw new InvalidElementError(nextElement, index, "Expect operation after '('");
                }
                balance--;
                if (balance < 0) {
                    throw new BracketsError(element, index, `missing ")"`);
                }
                bracketFlag = false;
            } else if (operations.has(element)) {
                const currentOperation = operations.get(element);
                let elems = stack.slice(stack.lastIndexOf(")") + 1, stack.length).reverse();
                if (currentOperation.numberOfArguments !== 0 && elems.length < currentOperation.numberOfArguments) {
                    throw new InvalidNumberOfArgumentsError(element, index, `Not enough operands: expect ${currentOperation.numberOfArguments}, but found ${elems.length}`);
                }
                if (currentOperation.numberOfArguments !== 0 && elems.length > currentOperation.numberOfArguments) {
                    throw new InvalidNumberOfArgumentsError(element, index, `Too many operands: expect ${currentOperation.numberOfArguments}, but found ${elems.length}`);
                }
                stack = stack.slice(0, -elems.length - 1);
                stack.push(new currentOperation(...elems));
                bracketFlag = true;
            } else if (variables.has(element)) {
                stack.push(new Variable((element), index));
            } else {
                if (!isNaN(element)) {
                    stack.push(new Const(Number.parseInt(element)));
                } else {
                    throw new InvalidElementError(element, index, `Unexpected element ${element}`);
                }
            }
            nextElement = element;
            return stack;
        }, []
    )
    stack = stack.filter(t => !(t === "(" || t === ")"));
    if (stack.length === 0) {
        throw new InvalidInputError("No elements");
    }
    if (stack.length > 1) {
        throw new InvalidNumberOfArgumentsError(stack[0], 0, "Too many arguments");
    }
    if (balance !== 0) {
        throw new BracketsError(stack[0], 0, `missing "("`);
    }
    return stack[0];
};

class ParsingError extends Error {
    constructor(element, index, message) {
        super(`${message} in element "${element}" at index ${index}`);
    }
}

class BracketsError extends ParsingError {
    constructor(element, index, message) {
        super(element, index, `Problem with brackets: ${message}`);
    }
}

class InvalidElementError extends ParsingError {
    constructor(element, index, message) {
        super(element, index, message);
    }
}

class InvalidNumberOfArgumentsError extends ParsingError {
    constructor(element, index, message) {
        super(element, index, message);
    }
}

class InvalidInputError extends Error {
    constructor(message) {
        super(`Incorrect input: ${message}`);
    }
}
