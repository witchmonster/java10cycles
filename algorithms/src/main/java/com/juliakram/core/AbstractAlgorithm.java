package com.juliakram.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.constants.Complexity;

/**
 * Created by juliakram on 15/02/16.
 */
@Getter
@RequiredArgsConstructor
abstract class AbstractAlgorithm<T, S> implements Algorithm<T, S>{

    @NonNull
    private Complexity oTime;

    @NonNull
    private Complexity oSpace;
}
