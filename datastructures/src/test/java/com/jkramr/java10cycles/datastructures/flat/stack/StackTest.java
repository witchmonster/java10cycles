package com.jkramr.java10cycles.datastructures.flat.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jkramr.java10cycles.datastructures.DataStructureTest;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public abstract class StackTest
        extends DataStructureTest {
    
    protected String[] data = new String[]{"bottom", "middle", "top"};
    private Stack<String> stringStack;
    
    @Test
    public void shouldPeekWhenNotEmpty()
            throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        stringStack.push("middle");
        stringStack.push("top");
        assertEquals(stringStack.peek(), "top");
        assertNotEquals(stringStack.peek(), "middle");
    }
    
    @Test
    public void shouldThrowEmptyStackExceptionWhenPopEmpty()
            throws Exception {
        stringStack = (Stack<String>) createEmpty();
        assertThrows(EmptyStackException.class, () -> stringStack.pop());
        
    }
    
    @Test
    public void shouldPushToEmptyStack()
            throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        assertEquals(stringStack.pop(), "bottom");
    }
    
    @Test
    public void shouldThrowEmptyStackExceptionWhenPeekingEmptyStack()
            throws Exception {
        stringStack = (Stack<String>) createEmpty();
        assertThrows(EmptyStackException.class, () -> stringStack.peek());
    }
    
    @Test
    public void shouldPopWholeStackWhenNotEmpty()
            throws Exception {
        stringStack = (Stack<String>) createEmpty();
        stringStack.push("bottom");
        stringStack.push("middle");
        stringStack.push("top");
        assertEquals(stringStack.pop(), "top");
        assertEquals(stringStack.pop(), "middle");
        assertEquals(stringStack.pop(), "bottom");
    }
}
