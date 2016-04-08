package com.juliakram.core;

import model.constants.Complexity;

public interface Algorithm<T, S> {

    Complexity getOTime();
    Complexity getOSpace();

    S solve(T input);
}
