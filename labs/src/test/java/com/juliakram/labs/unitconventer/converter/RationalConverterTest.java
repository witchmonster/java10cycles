package com.juliakram.labs.unitconventer.converter;

import com.juliakram.labs.unitconventer.UnitConverterTest;

import static com.juliakram.labs.unitconventer.unit.Unit.getNewRationalConverter;

public class RationalConverterTest extends UnitConverterTest {

    protected void initConverter() {
        converter = getNewRationalConverter(2, 3);
    }


}