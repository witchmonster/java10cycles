package com.juliakram.core;

import model.constants.Complexity;

/**
 * Created by juliakram on 14/02/16.
 */
public interface Algorithm<T, S> {

    Complexity getOTime();
    Complexity getOSpace();

    S solve(T input);
}
