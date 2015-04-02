package com.juliakram.labs.unitconventer.unit;

import com.juliakram.labs.unitconventer.converter.UnitConverter;
import com.juliakram.labs.unitconventer.measure.Measure;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class BaseUnit<M extends Measure> extends Unit<M> {

    public BaseUnit(String symbol) {
        super(symbol);
    }

    @Override
    public UnitConverter toStandardUnit() {
        return UnitConverter.IDENTITY;
    }

    @Override
    public Unit<? super M> getStandardUnit() {
        return this;
    }
}
