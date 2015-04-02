package com.juliakram.labs.unitconventer.converter;

import com.juliakram.labs.unitconventer.UnitConverterTest;

import static com.juliakram.labs.unitconventer.unit.Unit.getNewScaleConverter;

public class ScaleConverterTest extends UnitConverterTest {

    protected void initConverter() {
        converter = getNewScaleConverter(0.7894);
    }
}