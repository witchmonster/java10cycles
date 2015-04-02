package com.juliakram.labs.unitconventer.unit;

import com.juliakram.labs.unitconventer.converter.UnitConverter;
import com.juliakram.labs.unitconventer.measure.Measure;

/**
 * Created by yuliya.kramarenko on 24.12.2014.
 */
public class CompoundUnit<M extends Measure> extends Unit<M> {

    private final Unit<M> high;
    private final Unit<M> low;

    public CompoundUnit(String symbol, Unit<M> high, Unit<M> low) {
        super(symbol);
        if (!high.getStandardUnit().equals(low.getStandardUnit()))
            throw new IllegalArgumentException("Both units do not have the same system unit");
        this.high = high;
        this.low = low;
    }

    public CompoundUnit(Unit<M> high, Unit<M> low) {
        this(null, high, low);
    }

    public String format(double value) {
        return formatCompound(value, this, new StringBuffer("")).toString().trim();
    }

    private StringBuffer formatCompound(double value, Unit<M> unit, StringBuffer appendTo) {
        if (!(unit instanceof CompoundUnit)) {
            appendTo.append(unit.format(value)).append(" ");
            return appendTo;
        } else {
            Unit<M> high = ((CompoundUnit<M>) unit).getHigh();
            Unit<M> low = ((CompoundUnit<M>) unit).getLow();
            long highValue = (long) low.convertTo(high).convert(value);
            double highValueInLowUnit = high.convertTo(low).convert(highValue);
            double lowValue = value - highValueInLowUnit;
            formatCompound(highValue, high, appendTo);
            formatCompound(lowValue, low, appendTo);
            return appendTo;
        }
    }

    @Override
    public UnitConverter toStandardUnit() {
        return low.toStandardUnit();
    }

    @Override
    public Unit<? super M> getStandardUnit() {
        return low.getStandardUnit();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || o instanceof CompoundUnit
                && super.equals(o)
                && high.equals(((CompoundUnit) o).high)
                && low.equals(((CompoundUnit) o).low);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * super.hashCode() + low.hashCode())
                + high.hashCode();
    }

    public Unit<M> getLow() {
        return low;
    }

    public Unit<M> getHigh() {
        return high;
    }
}
