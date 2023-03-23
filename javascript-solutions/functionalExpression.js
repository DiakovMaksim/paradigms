"use strict"
const cnst = value => () => value
const one = () => 1
const two = () => 2
const variable = name => (x, y, z) => name === "x" ? x : name === "y" ? y : z
const operation = f => (...args) => (x, y, z) => f(...args.map(i => i(x, y, z)))
const subtract = operation((a, b) => a - b)
const multiply = operation((a, b) => a * b)
const add = operation((a, b) => a + b)
const divide = operation((a, b) => a / b)
const negate = operation(a => -a)
const sin = operation(a => Math.sin(a))
const cos = operation(a => Math.cos(a))
const operations = new Map([
    ["+", [add, 2]],
    ["-", [subtract, 2]],
    ["*", [multiply, 2]],
    ["/", [divide, 2]],
    ["negate", [negate, 1]],
    ["sin", [sin, 1]],
    ["cos", [cos, 1]]
])
const consts = new Map([
    ["one", one],
    ["two", two]
])
const parse = expression => {
    expression = expression.split(' ').filter(part => part.length > 0)
    let stack = []
    expression.map(s => {
            if (operations.get(s) !== undefined) {
                let elems = stack.slice(-operations.get(s)[1])
                stack = stack.slice(0, -operations.get(s)[1])
                stack.push(operations.get(s)[0](...elems))
            } else if (s === "x" || s === "y" || s === "z") {
                stack.push(variable(s))
            } else if (consts.get(s) !== undefined) {
                stack.push(consts.get(s))
            } else {
                stack.push(cnst(Number.parseInt(s)))
            }
        }
    )
    return stack.pop()
}