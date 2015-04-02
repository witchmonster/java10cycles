package com.juliakram.java10cycles.datastructures;

/**
 * Created by yuliya.kramarenko on 04.12.2014.
 * Equivalent of Java class Collection
 * @see java.util.Collection
 */
public interface DataStructure<T>  {

    void add(T object);
    void remove(T object);
    boolean contains(T object);
    boolean isEmpty();
    int size();
}
