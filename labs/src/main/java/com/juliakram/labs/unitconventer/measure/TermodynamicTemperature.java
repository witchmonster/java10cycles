package com.juliakram.labs.unitconventer.measure;

import com.juliakram.labs.unitconventer.unit.BaseUnit;
import com.juliakram.labs.unitconventer.unit.DerivedUnit;

/**
 * Created by yuliya.kramarenko on 22.12.2014.
 */
public class TermodynamicTemperature implements Measure {

    /**
     * According to http://en.wikipedia.org/wiki/International_System_of_Units
     *
     * DEFINITION:
     * Original (1743): The centigrade scale is obtained by assigning 0 °C to the freezing point of water
     * and 100 °Cto the boiling point of water.
     *
     * Interim (1954): The triple point of water (0.01 °C) defined to be exactly 273.16 K.
     *
     * Current (1967): 1/273.16 of the thermodynamic temperature of the triple point of water
     */
    public static final BaseUnit<TermodynamicTemperature> KELVIN = new BaseUnit<>("K");

    public static final DerivedUnit<TermodynamicTemperature> RANKINE = new DerivedUnit<TermodynamicTemperature>("ºC", KELVIN.times(9).divide(5));

    public static final DerivedUnit<TermodynamicTemperature> CELSIUS = new DerivedUnit<TermodynamicTemperature>("ºC", KELVIN.shift(-273.15));

    public static final DerivedUnit<TermodynamicTemperature> FAHRENHEIT = new DerivedUnit<TermodynamicTemperature>("ºF", KELVIN.times(9).divide(5).shift(-459.67));

}
