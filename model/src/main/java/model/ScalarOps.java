package model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScalarOps<T, S extends Number> {
    @NonNull
    public T arg;

    @NonNull
    public S scalar;
}
