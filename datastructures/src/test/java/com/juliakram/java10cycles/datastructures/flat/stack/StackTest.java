package com.juliakram.java10cycles.datastructures.flat.stack;

import com.juliakram.java10cycles.datastructures.DataStructureTest;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yuliya.kramarenko on 03.12.2014.
 */
public abstract class StackTest extends DataStructureTest {

    protected String[] data = new String[] {"bottom","middle","top"};
    private Stack<String> stringStack;

    @Test
    public void shouldPeekWhenNotEmpty() throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        stringStack.push("middle");
        stringStack.push("top");
        assertEquals(stringStack.peek(), "top");
        assertNotEquals(stringStack.peek(), "middle");
    }

    @Test(expected = EmptyStackException.class)
    public void shouldThrowEmptyStackExceptionWhenPopEmpty() throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.pop();
    }

    @Test
    public void shouldPushToEmptyStack() throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        assertEquals(stringStack.pop(), "bottom");
    }

    @Test(expected = EmptyStackException.class)
    public void shouldThrowEmptyStackExceptionWhenPeekingEmptyStack() throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.peek();
    }

    @Test
    public void shouldPopWholeStackWhenNotEmpty() throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        stringStack.push("middle");
        stringStack.push("top");
        assertEquals(stringStack.pop(), "top");
        assertEquals(stringStack.pop(), "middle");
        assertEquals(stringStack.pop(), "bottom");
    }
}
