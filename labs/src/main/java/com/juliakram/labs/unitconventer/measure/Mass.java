package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;
import com.juliakram.labs.unitconventer.unit.DerivedUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class Mass implements Measure {

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (1793): The grave was defined as being the weight [mass] of one
     * cubic decimetre of pure water at its freezing point.
     *
     * Current (1889): The mass of the International prototype kilogram.
     */
    public static final BaseUnit<Mass> KILOGRAM = new BaseUnit<>("kg");

    public static final DerivedUnit<Mass> GRAM = new DerivedUnit<>("g", KILOGRAM.divide(KILO_MULTIPLIER));

}
