package com.juliakram.java10cycles.datastructures.flat.stack;

public class ArrayStackTest extends StackTest {

    @Override
    protected Stack<String> createEmpty() {
        return new ArrayStack<String>();
    }
}
