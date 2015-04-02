package com.juliakram.java10cycles.datastructures.flat.base.sequence;

import com.juliakram.java10cycles.datastructures.framework.DataStructure;

/**
 * Created by yuliya.kramarenko on 04.12.2014.
 */
public abstract class AbstractSequenceStructure<T> implements DataStructure<T> {

    protected int size;

    public class Node<E> {
        E datum;

        public Node(E object, Node<E> link) {
            this.datum = object;
            this.nextNode = link;
        }

        public Node<E> next() {
            return nextNode;
        }

        Node<E> nextNode;

        public Node(E datum) {
            this.datum = datum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return !(datum != null ? !datum.equals(node.datum) : node.datum != null);
        }

        @Override
        public int hashCode() {
            return datum != null ? datum.hashCode() : 0;
        }

        public E getDatum() {
            return datum;
        }

        public void setDatum(E datum) {
            this.datum = datum;
        }

        public boolean hasNext() {
            return next() != null;
        }
    }

    protected Node<T> head;

    public AbstractSequenceStructure() {
        this.head = null;
    }

    public AbstractSequenceStructure(T[] data) {
        this();
        for (T aData : data) {
            add(aData);
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(T object) {
        head = new Node<>(object, head);
        size++;
    }

    @Override
    public boolean contains(T object) {
        Node<T> node = head;
        while (node != null) {
            if (object == null ? node.datum == null : object.equals(node.datum)) return true;
            node = node.nextNode;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(T object) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            if (object == null ? current.datum == null : object.equals(current.datum)) {
                if (previous != null) previous.nextNode = current.nextNode; else head = current.nextNode;
                size--;
            }
            previous = current;
            current = current.nextNode;
        }
    }
}
