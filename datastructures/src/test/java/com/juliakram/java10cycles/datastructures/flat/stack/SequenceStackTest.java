package com.juliakram.java10cycles.datastructures.flat.stack;

/**
 * Created by yuliya.kramarenko on 30.11.2014.
 */
public class SequenceStackTest extends StackTest {

    @Override
    protected Stack<String> createEmpty() {
        return new SequenceStack<>();
    }
}
