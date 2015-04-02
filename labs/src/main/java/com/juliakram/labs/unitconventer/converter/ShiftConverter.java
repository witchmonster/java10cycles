package com.juliakram.labs.unitconventer.converter;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class ShiftConverter extends UnitConverter {

    private final double offset;

    public ShiftConverter(double offset) {
        this.offset = offset;
    }

    public double getOffset() {
        return offset;
    }

    @Override
    public UnitConverter inverse() {
        return new ShiftConverter(-offset);
    }

    @Override
    public double convert(double value) {
        return value + offset;
    }
}
