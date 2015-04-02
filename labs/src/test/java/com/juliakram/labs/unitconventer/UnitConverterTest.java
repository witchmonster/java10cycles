package com.juliakram.labs.unitconventer;

import com.juliakram.labs.unitconventer.converter.UnitConverter;
import org.junit.Before;
import org.junit.Test;

import static com.juliakram.labs.unitconventer.measure.Length.*;
import static com.juliakram.labs.unitconventer.measure.TermodynamicTemperature.CELSIUS;
import static com.juliakram.labs.unitconventer.measure.TermodynamicTemperature.FAHRENHEIT;
import static com.juliakram.labs.unitconventer.measure.TermodynamicTemperature.KELVIN;
import static com.juliakram.labs.unitconventer.measure.Time.COMPOUND_HOUR_MINUTE_SECOND;
import static com.juliakram.labs.unitconventer.measure.Time.SECOND;
import static junit.framework.TestCase.assertEquals;

public abstract class UnitConverterTest {

    protected static final double DEFAULT_VALUE = 100.0;
    protected UnitConverter converter;

    @Before
    public void init() {
        initConverter();
    }

    @Test
    public void shouldInverse() throws Exception {
        UnitConverter inversedConverter = converter.inverse();
        double convertedValue = converter.convert(DEFAULT_VALUE);
        assertEquals(DEFAULT_VALUE, inversedConverter.convert(convertedValue));
    }

    @Test
    public void conversionTest() throws Exception {
        assertEquals("160.9344 km", KILOMETRE.format(MILE.convertTo(KILOMETRE).convert(100)));

        assertEquals("1000.0 m", METRE.format(KILOMETRE.convertTo(METRE).convert(1)));

        assertEquals("32.222222222222285 ºF", FAHRENHEIT.format(CELSIUS.convertTo(FAHRENHEIT).convert(90)));

        double lengthValue = METRE.convertTo(COMPOUND_MILE_YARD_FOOT_INCH).convert(10000);
        assertEquals("6.0 mi 376.0 yd 0.0 ft 4.787401574780233 in", COMPOUND_MILE_YARD_FOOT_INCH.format(lengthValue));

        double timeValue = SECOND.convertTo(COMPOUND_HOUR_MINUTE_SECOND).convert(10000);
        assertEquals("2.0 h 46.0 m 40.0 s", COMPOUND_HOUR_MINUTE_SECOND.format(timeValue));
    }

    @Test(expected = ConversionException.class)
    public void shouldThrowException() throws Exception {
        KILOMETRE.convertTo(KELVIN);
    }

    protected abstract void initConverter();
}