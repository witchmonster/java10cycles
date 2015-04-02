package com.juliakram.java10cycles.datastructures.flat.list;

import com.juliakram.java10cycles.datastructures.framework.DataStructure;

/**
 * Created by yuliya.kramarenko on 09.12.2014.
 */
public class LinkedListTest extends ListTest {
    @Override
    protected DataStructure<String> createEmpty() {
        return new LinkedList<>();
    }
}
