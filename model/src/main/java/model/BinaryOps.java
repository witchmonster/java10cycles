package model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by juliakram on 14/02/16.
 */
@RequiredArgsConstructor
public class BinaryOps<T> {

    @NonNull
    public T right;

    @NonNull
    public T left;
}
