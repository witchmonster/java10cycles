package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class LuminousIntensity implements Measure {

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (1946): The value of the new candle is such that the brightness of the full radiator
     * at the temperature of solidification of platinum is 60 new candles per square centimetre.
     *
     * Current (1979): The luminous intensity, in a given direction, of a source that emits monochromatic radiation
     * of frequency 540×1012 hertz and that has a radiant intensity in that direction
     * of 1/683 watt per steradian.
     */
    public static final BaseUnit<LuminousIntensity> CANDELA = new BaseUnit<>("cd");

}
