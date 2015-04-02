package com.juliakram.labs.unitconventer.converter;

/**
 * Created by yuliya.kramarenko on 23.12.2014.
 */
public class RationalConverter extends UnitConverter {
    private final long divisor;
    private final long dividend;

    public RationalConverter(long dividend, long divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public UnitConverter inverse() {
        return new RationalConverter(divisor, dividend);
    }

    @Override
    public double convert(double value) {
        return value * dividend / divisor;
    }
}
