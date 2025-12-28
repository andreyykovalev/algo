package org.algo;

import java.util.Stack;

class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> stackOut;

    public MyQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        while (!stack.isEmpty()) {
            stackOut.push(stack.pop());
        }
        return stackOut.pop();
    }

    public int peek() {

    }

    public boolean empty() {

    }
}
