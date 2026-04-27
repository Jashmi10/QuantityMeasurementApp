package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testInchesEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testInchesInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testSameReference() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void testNullComparison() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    @Test
    public void testYardToFeet() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testCmToInch() {
        assertTrue(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testAllUnitsTransitive() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inch = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        assertEquals(12.0, result);
    }

    @Test
    public void testConversion_YardsToInches() {
        double result = Length.convert(1.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.INCHES);

        assertEquals(36.0, result);
    }

    @Test
    public void testConversion_CmToInches() {
        double result = Length.convert(2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES);

        assertEquals(1.0, result, 0.01); // epsilon
    }
}