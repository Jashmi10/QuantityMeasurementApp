package com.apps.quantitymeasurement;

public enum WeightUnit {

    KILOGRAM(1.0),        // base unit
    GRAM(0.001),          // 1 g = 0.001 kg
    POUND(0.453592);      // 1 lb ≈ 0.453592 kg

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    // Convert to base unit (kg)
    public double toBase(double value) {
        return value * toKgFactor;
    }

    // Convert from base unit (kg)
    public double fromBase(double baseValue) {
        return baseValue / toKgFactor;
    }
}