package com.juliakram.foo.teststuff;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by juliakram on 02/06/16.
 */
public class HashSetAnyMatch {

    public static void main(String[] args) {
        Set<Foo> fooSet = new HashSet<>();

        Foo foo = new Foo(1, 1);

        fooSet.contains(foo);
        fooSet.stream().anyMatch(foo1 -> foo1.equals(foo));
    }

    @Data
    @EqualsAndHashCode(of = "id")
    private static class Foo {
        private final int id;
        private final int value;
    }
}
