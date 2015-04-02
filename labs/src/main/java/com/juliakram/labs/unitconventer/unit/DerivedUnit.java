package com.juliakram.labs.unitconventer.unit;

import com.juliakram.labs.unitconventer.converter.UnitConverter;
import com.juliakram.labs.unitconventer.measure.Measure;

/**
 * Created by yuliya.kramarenko on 23.12.2014.
 */
public class DerivedUnit<M extends Measure> extends Unit<M> {

    protected final Unit<M> unit;

    public DerivedUnit(String symbol, Unit<M> unit) {
        super(symbol);
        this.unit = unit;
    }

    public DerivedUnit(Unit<M> unit) {
        this(null, unit);
    }

    @Override
    public boolean equals(Object o) {
        return this == o
               ||  o instanceof DerivedUnit
                   && super.equals(o)
                   && unit.equals(((DerivedUnit) o).unit);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + unit.hashCode();
    }

    @Override
    public UnitConverter toStandardUnit() {
        return unit.toStandardUnit();
    }

    @Override
    public Unit<? super M> getStandardUnit() {
        return unit.getStandardUnit();
    }
}
