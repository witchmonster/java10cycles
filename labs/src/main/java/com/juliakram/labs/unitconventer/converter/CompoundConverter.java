package com.juliakram.labs.unitconventer.converter;

/**
* Created by yuliya.kramarenko on 06.01.2015.
*/
public class CompoundConverter extends UnitConverter {
    private final UnitConverter right;
    private final UnitConverter left;

    public CompoundConverter(UnitConverter left, UnitConverter right) {
        this.right = right;
        this.left = left;
    }

    @Override
    public UnitConverter inverse() {
        return new CompoundConverter(right.inverse(), left.inverse());
    }

    @Override
    public double convert(double value) {
        return right.convert(left.convert(value));
    }
}
