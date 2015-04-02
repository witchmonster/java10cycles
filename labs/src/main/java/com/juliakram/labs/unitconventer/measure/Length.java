package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;
import com.juliakram.labs.unitconventer.unit.CompoundUnit;
import com.juliakram.labs.unitconventer.unit.DerivedUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class Length implements Measure {

    private static final long INTERNATIONAL_METRE_TO_FOOT_MULTIPLIER = 3048;

    private static final long INTERNATIONAL_METRE_TO_MILE_MULTIPLIER = 1609344;

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     * <p>
     * DEFINITION:
     * Original (1793): 1/10000000 of the meridian through Paris between the North Pole and the Equator.
     * <p>
     * Interim (1960): 1650763.73 wavelengths in a vacuum of the radiation corresponding to the transition
     * between the 2p^10 and 5d^5 quantum levels of the krypton-86 atom.
     * <p>
     * Current (1983): The distance travelled by light in vacuum in
     * 1/299792458 second.
     */
    public static final BaseUnit<Length> METRE = new BaseUnit<>("m");

    public static final DerivedUnit<Length> KILOMETRE = new DerivedUnit<>("km", METRE.times(KILO_MULTIPLIER));

    public static final DerivedUnit<Length> MILLIMETRE = new DerivedUnit<>("mm", METRE.divide(MILLI_DIVISOR));

    public static final DerivedUnit<Length> FOOT = new DerivedUnit<>("ft", METRE.times(INTERNATIONAL_METRE_TO_FOOT_MULTIPLIER).divide(10000));

    public static final DerivedUnit<Length> INCH = new DerivedUnit<>("in", FOOT.divide(12));

    public static final DerivedUnit<Length> YARD = new DerivedUnit<>("yd", FOOT.times(3));

    public static final DerivedUnit<Length> MILE = new DerivedUnit<>("mi", METRE.times(INTERNATIONAL_METRE_TO_MILE_MULTIPLIER).divide(1000));

    public static final CompoundUnit<Length> COMPOUND_FOOT_INCH = FOOT.compound(INCH);

    public static final CompoundUnit<Length> COMPOUND_YARD_FOOT_INCH = YARD.compound(FOOT).compound(INCH);

    public static final CompoundUnit<Length> COMPOUND_MILE_YARD_FOOT_INCH = MILE.compound(YARD).compound(FOOT).compound(INCH);


}
