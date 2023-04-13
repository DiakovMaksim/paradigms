"use strict"
const operations = new Map();
const variables = new Map([
    ["x", 0],
    ["y", 1],
    ["z", 2]
]);

// :NOTE: use prototype
function Const(value) {
    this.toString = () => (value).toString();
    this.evaluate = () => value;
    this.prefix = this.toString;
}

function Variable(name) {
    this.toString = () => name;
    this.evaluate = (...args) => args[variables.get(name)];
    this.prefix = this.toString;
}

function AbstractOperation(name, f, n, string, ...args) {
    // :NOTE: один раз
    operations.set(string, [name, n, f]);
    this.getName = () => string;
    this.args = args;
}

AbstractOperation.prototype.toString = function () {
    return `${this.args.join(" ")} ${this.getName()}`;
}
AbstractOperation.prototype.prefix = function () {
    return `(${this.getName()} ${this.args.map(i => i.prefix()).join(" ")})`;
}
AbstractOperation.prototype.evaluate = function (...args) {
    return operations.get(this.getName())[2](...(this.args.map(i => i.evaluate(...args))));
}

function createOperation(f, n, string) {
    const result = function () {
        // не передавать в конструктор
        AbstractOperation.call(this, result, f, n, string, ...arguments);
    }
    result.prototype = Object.create(AbstractOperation.prototype);
    return result;
}

const Add = createOperation((a, b) => a + b, 2, "+");
const Subtract = createOperation((a, b) => a - b, 2, "-");
const Multiply = createOperation((a, b) => a * b, 2, "*");
const Divide = createOperation((a, b) => a / b, 2, "/");
const Negate = createOperation(a => -a, 1, "negate");
const Exp = createOperation(a => Math.exp(a), 1, "exp");
const Ln = createOperation(a => Math.log(a), 1, "ln");

const parse = expression => {
    expression = expression.split(' ').filter(part => part.length > 0);
    let stack = [];
    expression.map(s => {
            if (operations.has(s)) {
                let elems = stack.slice(-operations.get(s)[1]);
                stack = stack.slice(0, -operations.get(s)[1]);
                stack.push(new (operations.get(s)[0])(...elems));
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
    let operationFlag = true;
    let balance = 0;
    let stack = string.reduceRight(function (stack, element, index) {
            if (element === ")") {
                balance++;
                stack.push(element);
                operationFlag = true;
            } else if (element === "(") {
                balance--;
                if (balance < 0) {
                    throw new BracketsError(element, index, `missing ")"`);
                }
                if (operationFlag) {
                    throw new InvalidElementError(element, index, `Expect operation, but found "${stack[stack.length - 1]}"`);
                }
            } else if (operations.has(element)) {
                let elems = stack.slice(stack.lastIndexOf(")", -operations.get(element)[1]) + 1, stack.length).reverse();
                if (elems.length < operations.get(element)[1]) {
                    throw new InvalidNumberOfArgumentsError(element, index, `Not enough operands: expect ${operations.get(element)[1]}, but found ${elems.length}`);
                }
                if (elems.length > operations.get(element)[1]) {
                    throw new InvalidNumberOfArgumentsError(element, index, `Too many operands: expect ${operations.get(element)[1]}, but found ${elems.length}`);
                }
                stack = stack.slice(0, -operations.get(element)[1] - 1);
                stack.push(new (operations.get(element)[0])(...elems));
                operationFlag = false;
            } else if (variables.has(element)) {
                stack.push(new Variable((element), index));
            } else {
                if (!isNaN(element)) {
                    stack.push(new Const(Number.parseInt(element)));
                } else {
                    throw new InvalidElementError(element, index, `Unexpected element ${element}`);
                }
            }

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
