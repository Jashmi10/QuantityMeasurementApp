package com.apps.quantitymeasurement;

/**
 * UC8: Standalone LengthUnit enum
 * Handles all conversion logic (SRP + low coupling)
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    // conversion factor relative to base unit (FEET)
    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /**
     * Convert given value to base unit (FEET)
     */
    public double toBase(double value) {
        return value * toFeetFactor;
    }

    /**
     * Convert from base unit (FEET) to this unit
     */
    public double fromBase(double baseValue) {
        return baseValue / toFeetFactor;
    }
}