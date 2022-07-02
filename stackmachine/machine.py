#!/usr/bin/python3

import fileinput

def add(stack):
    stack.append(stack.pop() + stack.pop())

def subtract(stack):
    op2 = stack.pop()
    op1 = stack.pop()
    stack.append(op1 - op2)

def multiply(stack):
    stack.append(stack.pop() * stack.pop())

def divide(stack):
    op2 = stack.pop()
    op1 = stack.pop()
    stack.append(op1 / op2)

stack = []
operations = {
    "+" : add,
    "-" : subtract,
    "*" : multiply,
    "/" : divide
}

for line in fileinput.input():
    value = line.rstrip()
    if value.replace(".", "", 1).isnumeric():
        stack.append(float(value))
    else:
        operations[value](stack)

print(stack.pop())
