package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;
import com.juliakram.labs.unitconventer.unit.CompoundUnit;
import com.juliakram.labs.unitconventer.unit.DerivedUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class Time implements Measure {

    private static final int SECONDS_IN_A_MINUTE = 60;

    private static final int MINUTES_IN_AN_HOUR = SECONDS_IN_A_MINUTE;

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (Medieval): 1/86400 of a day.
     *
     * Interim (1956): 1/31556925.9747 of the tropical year for 1900 January 0 at 12 hours ephemeris time.
     *
     * Current (1967): The duration of 9192631770 periods of the radiation corresponding to the transition
     * between the two hyperfine levels of the ground state of the caesium 133 atom.
     */
    public static final BaseUnit<Time> SECOND = new BaseUnit<>("s");

    public static final DerivedUnit<Time> MINUTE = new DerivedUnit<>("m", SECOND.times(SECONDS_IN_A_MINUTE));

    public static final DerivedUnit<Time> HOUR = new DerivedUnit<>("h", SECOND.times(SECONDS_IN_A_MINUTE * MINUTES_IN_AN_HOUR));

    public static final CompoundUnit<Time> COMPOUND_HOUR_MINUTE_SECOND = HOUR.compound(MINUTE).compound(SECOND);

}
