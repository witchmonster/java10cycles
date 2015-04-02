package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class AmountOfSubstance implements Measure {

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (1900): The molecular weight of a substance in mass grams.
     *
     * Current (1967): The amount of substance of a system which contains
     * as many elementary entities as there are atoms in 0.012 kilogram of carbon 12.
     */
    public static final BaseUnit<AmountOfSubstance> MOLE = new BaseUnit<>("mol");

}
