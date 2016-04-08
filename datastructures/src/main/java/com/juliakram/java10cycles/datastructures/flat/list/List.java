package com.juliakram.java10cycles.datastructures.flat.list;

import com.juliakram.java10cycles.datastructures.DataStructure;

public interface List<T> extends DataStructure<T> {

    T get(int index);
    void set(int index, T object);
    int indexOf(T object);

}
