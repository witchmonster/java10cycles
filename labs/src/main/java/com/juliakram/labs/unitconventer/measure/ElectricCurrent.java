package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class ElectricCurrent implements Measure {

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (1881): A tenth of the electromagnetic CGS unit of current. The [CGS] electromagnetic
     * unit of current is that current, flowing in an arc 1 cm long of a circle 1 cm
     * in radius creates a field of one oersted at the centre.
     *
     * Current (1946): The constant current which, if maintained in two straight parallel conductors
     * of infinite length, of negligible circular cross-section, and placed 1 m apart in vacuum,
     * would produce between these conductors a force equal to 2×10−7 newtons per metre of length.
     */
    public static final BaseUnit<ElectricCurrent> AMPERE = new BaseUnit<>("A");

}
