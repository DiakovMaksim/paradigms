"use strict"
const operations = new Map([])

function ArrToStr(arr) {
    let s = ""
    while (arr.length > 0) {
        s += arr.shift()
    }
    return s
}

function Const(value) {
    this.toString = () => (String)(value)
    this.evaluate = () => value
    this.prefix = this.toString
}

function Variable(name) {
    this.toString = () => name
    this.evaluate = (x, y, z) => name === "x" ? x : name === "y" ? y : z
    this.prefix = this.toString
}

function AbstractOperation(name, f, n, string, ...args) {
    operations.set(string, [name, n, f])
    this.getName = () => string
    this.args = new Array(...args)
}

AbstractOperation.prototype.toString = function () {
    return ArrToStr(this.args.map(i => i.toString() + " ")) + this.getName()
}
AbstractOperation.prototype.prefix = function () {
    return `(${this.getName()}${ArrToStr(this.args.map(i => " " + i.prefix()))})`
}
AbstractOperation.prototype.evaluate = function (x, y, z) {
    return operations.get(this.getName())[2](...(this.args.map(i => i.evaluate(x, y, z))))
}

function Add() {
    AbstractOperation.call(this, Add, (left, right) => left + right, 2, "+", ...arguments)
}

Add.prototype = Object.create(AbstractOperation.prototype)

function Subtract() {
    AbstractOperation.call(this, Subtract, (left, right) => left - right, 2, "-", ...arguments)
}

Subtract.prototype = Object.create(AbstractOperation.prototype)

function Multiply() {
    AbstractOperation.call(this, Multiply, (left, right) => left * right, 2, "*", ...arguments)
}

Multiply.prototype = Object.create(AbstractOperation.prototype)

function Divide() {
    AbstractOperation.call(this, Divide, (left, right) => left / right, 2, "/", ...arguments)
}

Divide.prototype = Object.create(AbstractOperation.prototype)

function Negate() {
    AbstractOperation.call(this, Negate, (operand) => -operand, 1, "negate", ...arguments)
}

Negate.prototype = Object.create(AbstractOperation.prototype)

function Exp() {
    AbstractOperation.call(this, Exp, (operand) => Math.exp(operand), 1, "exp", ...arguments)
}

Exp.prototype = Object.create(AbstractOperation.prototype)

function Ln() {
    AbstractOperation.call(this, Ln, (operand) => Math.log(operand), 1, "ln", ...arguments)
}

Ln.prototype = Object.create(AbstractOperation.prototype)

const parse = expression => {
    expression = expression.split(' ').filter(part => part.length > 0)
    let stack = []
    expression.map(s => {
            if (operations.get(s) !== undefined) {
                let elems = stack.slice(-operations.get(s)[1])
                stack = stack.slice(0, -operations.get(s)[1])
                stack.push(new (operations.get(s)[0])(...elems))
            } else if (s === "x" || s === "y" || s === "z") {
                stack.push(new Variable(s))
            } else {
                stack.push(new Const(+s))
            }
        }
    )
    return stack.pop()
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
                    throw new ParsingError(element, index, "Problem with brackets");
                }
                if (operationFlag) {
                    throw  new ParsingError(element, index, `Expect operation, but found ${stack[stack.length - 1]}`);
                }
            } else if (operations.has(element)) {
                let elems = stack.slice(stack.lastIndexOf(")", -operations.get(element)[1]) + 1, stack.length).reverse();
                if (elems.length !== operations.get(element)[1]) {
                    throw new ParsingError(element, index, "Not enough operands");
                }
                stack = stack.slice(0, -operations.get(element)[1] - 1);
                stack.push(new (operations.get(element)[0])(...elems))
                operationFlag = false;
            } else if (element === "x" || element === "y" || element === "z") {
                stack.push(new Variable((element), index));
            } else {
                if (!isNaN(element)) {
                    stack.push(new Const(Number.parseInt(element)));
                } else {
                    throw new ParsingError(element, index, `Unexpected element ${element}`)
                }
            }

            return stack;
        }, []
    )
    stack = stack.filter(t => !(t === "(" || t === ")"));
    if (stack.length === 0) {
        throw new ParsingError(undefined, 0, "No elements")
    }
    if (stack.length > 1) {
        throw new ParsingError(stack[0], 0, "Too many arguments")
    }
    if (balance !== 0) {
        throw new ParsingError(stack[0], 0, "Problem with brackets");
    }
    return stack[0];
};

class ParsingError extends Error {
    constructor(token, index, message) {
        super(`${message} in element "${token}" at index ${index}`);
    }
}
