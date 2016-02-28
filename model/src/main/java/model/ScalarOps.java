package model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by juliakram on 15/02/16.
 */
@RequiredArgsConstructor
public class ScalarOps<T, S extends Number> {
    @NonNull
    public T arg;

    @NonNull
    public S scalar;
}
