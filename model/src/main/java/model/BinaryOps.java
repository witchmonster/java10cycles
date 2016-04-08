package model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryOps<T> {

    @NonNull
    public T right;

    @NonNull
    public T left;
}
