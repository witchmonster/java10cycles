package com.juliakram.java10cycles.datastructures.flat.list;

import com.juliakram.java10cycles.datastructures.framework.DataStructureTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yuliya.kramarenko on 09.12.2014.
 */
public abstract class ListTest extends DataStructureTest {

    List<String> list;

    @Test
    public void shouldGet() throws Exception {
        list = (List<String>) createEmpty();
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals("two", list.get(1));
    }

    @Test
    public void shouldSet() throws Exception {
        list = (List<String>) createEmpty();
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals("two", list.get(1));
        list.set(1, "nottwo");
        assertEquals("nottwo", list.get(1));
    }

    @Test
    public void shouldReturnIndex() throws Exception {
        list = (List<String>) createEmpty();
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals(1, list.indexOf("two"));
    }
}
