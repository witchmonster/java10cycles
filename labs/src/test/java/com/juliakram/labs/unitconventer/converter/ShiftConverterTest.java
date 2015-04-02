package com.juliakram.labs.unitconventer.converter;

import com.juliakram.labs.unitconventer.UnitConverterTest;

import static com.juliakram.labs.unitconventer.unit.Unit.getNewShiftConverter;

public class ShiftConverterTest extends UnitConverterTest {

    private static final double[] INPUT = new double[] {};

    protected void initConverter() {
        converter = getNewShiftConverter(-2);
    }
}