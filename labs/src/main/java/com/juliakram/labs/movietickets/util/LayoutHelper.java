package com.juliakram.labs.movietickets.util;

import com.google.gwt.thirdparty.guava.common.io.ByteStreams;

import java.io.IOException;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
public class LayoutHelper {

    public static byte[] imageToByteArray(String resourcePath) {
        try {
            return ByteStreams.toByteArray(LayoutHelper.class.getClassLoader().getResourceAsStream(resourcePath));
        } catch (IOException e) {
            throw new RuntimeException("Internal resource unaccessible");
        }
    }
}
