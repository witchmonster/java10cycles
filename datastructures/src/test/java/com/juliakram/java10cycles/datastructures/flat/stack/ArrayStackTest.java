package com.juliakram.java10cycles.datastructures.flat.stack;

/**
 * Created by yuliya.kramarenko on 03.12.2014.
 */

public class ArrayStackTest extends StackTest {

    @Override
    protected Stack<String> createEmpty() {
        return new ArrayStack<String>();
    }
}
