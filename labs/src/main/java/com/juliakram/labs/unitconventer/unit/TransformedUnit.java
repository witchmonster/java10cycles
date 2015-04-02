package com.juliakram.labs.unitconventer.unit;

import com.juliakram.labs.unitconventer.converter.UnitConverter;
import com.juliakram.labs.unitconventer.measure.Measure;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class TransformedUnit<M extends Measure> extends Unit<M> {

    private final UnitConverter toParent;
    private final Unit<M> parent;

    public TransformedUnit(Unit<M> parent, UnitConverter toParent) {
        this(null, parent, toParent);
    }

    public TransformedUnit(String symbol, Unit<M> parent, UnitConverter toParent) {
        super(symbol);
        this.parent = parent;
        this.toParent = toParent;
    }

    public UnitConverter getToParent() {
        return toParent;
    }

    public Unit<M> getParent() {
        return parent;
    }

    @Override
    public UnitConverter toStandardUnit() {
        return parent.toStandardUnit().compose(toParent);
    }

    @Override
    public Unit<? super M> getStandardUnit() {
        return parent.getStandardUnit();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
               || o instanceof TransformedUnit
                    && super.equals(o)
                    && parent.equals(((TransformedUnit) o).parent)
                    && toParent.equals(((TransformedUnit) o).toParent);

    }

    @Override
    public int hashCode() {
        return 31 * (31 * super.hashCode() + toParent.hashCode())
                + parent.hashCode();
    }
}