package com.juliakram.labs.unitconventer.converter;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class ScaleConverter extends UnitConverter {

    private final double scaleFactor;

    public ScaleConverter(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    @Override
    public UnitConverter inverse() {
        return new ScaleConverter(1 / scaleFactor);
    }

    @Override
    public double convert(double value) {
        return value * scaleFactor;
    }
}
