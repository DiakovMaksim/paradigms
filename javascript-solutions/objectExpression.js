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
}

function Variable(name) {
    this.toString = () => name
    this.evaluate = (x, y, z) => name === "x" ? x : name === "y" ? y : z
}

function AbstractOperation(name, f, n, string, ...args) {
    operations.set(string, [name, n, f])
    this.getName = () => string
    this.args = new Array(...args)
}
AbstractOperation.prototype.toString = function () {
    return ArrToStr(this.args.map(i => i.toString() + " ")) + this.getName()
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
                stack.push(new Const(Number.parseInt(s)))
            }
        }
    )
    return stack.pop()
}