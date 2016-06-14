package com.juliakram.foo.teststuff;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by juliakram on 02/06/16.
 */
public class HashSetRemove {

    public static void main(String[] args) {
        Set<Foo> fooSet = new HashSet<>();

        Foo foo0 = new Foo("1", 1);
        Foo foo1 = new Foo("2", 2);
        Foo foo2 = new Foo("3", 3);
        Foo foo3 = new Foo("4", 4);
        Foo foo4 = new Foo("5", 5);


        fooSet.add(foo0);
        fooSet.add(foo1);
        fooSet.add(foo2);
        fooSet.add(foo3);
        fooSet.add(foo4);

        fooSet.remove(new Foo("1", 6789));

        System.out.println(fooSet);
    }

    @Data
    @ToString
    @EqualsAndHashCode(of = "id")
    private static class Foo {

        private final String id;
        private final int value;

    }
}
