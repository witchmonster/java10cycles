package com.juliakram.labs.unitconventer.unit;

import com.juliakram.labs.unitconventer.ConversionException;
import com.juliakram.labs.unitconventer.converter.RationalConverter;
import com.juliakram.labs.unitconventer.converter.ScaleConverter;
import com.juliakram.labs.unitconventer.converter.ShiftConverter;
import com.juliakram.labs.unitconventer.converter.UnitConverter;
import com.juliakram.labs.unitconventer.measure.Measure;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public abstract class Unit<M extends Measure> {

    private final String symbol;

    protected Unit(String symbol) {
        this.symbol = symbol;
    }

    public static ShiftConverter getNewShiftConverter(double offset) {
        return new ShiftConverter(offset);
    }

    public static ScaleConverter getNewScaleConverter(double multiplier) {
        return new ScaleConverter(multiplier);
    }

    public static RationalConverter getNewRationalConverter(long dividend, long divisor) {
        return new RationalConverter(dividend, divisor);
    }

    public Unit<M> shift(double offset) {
        return transform(getNewShiftConverter(offset));
    }

    public Unit<M> scaleBy(double factor) {
        return transform(getNewScaleConverter(factor));
    }

    public Unit<M> divide(long divisor) {
        return transform(getNewRationalConverter(1, divisor));
    }

    public Unit<M> times(long multiplier) {
        return transform(getNewRationalConverter(multiplier, 1));
    }

    private Unit<M> transform(UnitConverter converter) {
        return new TransformedUnit<>(this, converter);
    }

    public String getSymbol() {
        return symbol;
    }

    public UnitConverter convertTo(Unit<?> that) throws ConversionException {
        if (this.getStandardUnit().equals(that.getStandardUnit())) {
            return that.toStandardUnit().inverse().compose(this.toStandardUnit());
        } else {
            throw new ConversionException("Units must be of the same measure");
        }
    }

    public String format(double value) {
        return value + (getSymbol() == null
                ? ""
                : " " + getSymbol());
    }

    public abstract UnitConverter toStandardUnit();

    public abstract Unit<? super M> getStandardUnit();

    public CompoundUnit<M> compound(Unit<M> unit) {
        return new CompoundUnit<M>(this, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        return symbol.equals(unit.symbol);

    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}