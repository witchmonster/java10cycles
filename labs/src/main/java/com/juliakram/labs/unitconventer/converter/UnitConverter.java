package com.juliakram.labs.unitconventer.converter;

import com.juliakram.labs.unitconventer.unit.Unit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public abstract class UnitConverter {

    public static final UnitConverter IDENTITY = new UnitConverter() {

        @Override
        public UnitConverter inverse() {
            return this;
        }

        @Override
        public double convert(double value) {
            return value;
        }
    };
    private Unit<?> unit;

    public abstract UnitConverter inverse();

    public abstract double convert(double value);

    public UnitConverter compose(UnitConverter that) {
        return new CompoundConverter(this, that);
    }

}
