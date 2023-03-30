"use strict"
const operations = new Map([
    ["+", [Add, 2]],
    ["-", [Subtract, 2]],
    ["*", [Multiply, 2]],
    ["/", [Divide, 2]],
    ["negate", [Negate, 1]],
    ["exp", [Exp, 1]],
    ["ln", [Ln, 1]]
])
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

function AbstractOperation(args) {
    this.args = args
}
AbstractOperation.prototype.toString = function () {return ArrToStr(this.args.map(i => i.toString() + " ")) + this.getName()}
AbstractOperation.prototype.evaluate = function (x, y, z) {return this.eval(...(this.args.map(i => i.evaluate(x, y, z))))}

function Add(left, right) {
    AbstractOperation.call(this, [left, right])
    this.eval = (left, right) => left + right
    this.getName = () => "+"
}
Add.prototype = Object.create(AbstractOperation.prototype)
function Subtract(left, right) {
    AbstractOperation.call(this, [left, right])
    this.eval = (left, right) => left - right
    this.getName = () => "-"
}
Subtract.prototype = Object.create(AbstractOperation.prototype)
function Multiply(left, right) {
    AbstractOperation.call(this, [left, right])
    this.eval = (left, right) => left * right
    this.getName = () => "*"
}
Multiply.prototype = Object.create(AbstractOperation.prototype)
function Divide(left, right) {
    AbstractOperation.call(this, [left, right])
    this.eval = (left, right) => (left / right)
    this.getName = () => "/"
}
Divide.prototype = Object.create(AbstractOperation.prototype)
function Negate(value) {
    AbstractOperation.call(this, [value])
    this.eval = value => -value
    this.getName = () => "negate"
}
Negate.prototype = Object.create(AbstractOperation.prototype)
function Exp(value) {
    AbstractOperation.call(this, [value])
    this.eval = value => Math.exp(value)
    this.getName = () => "exp"
}
Exp.prototype = Object.create(AbstractOperation.prototype)
function Ln(value) {
    AbstractOperation.call(this, [value])
    this.eval = (value) => Math.log(value)
    this.getName = () => "ln"
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
