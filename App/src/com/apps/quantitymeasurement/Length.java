package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    // 🔹 Enum with base unit = INCHES
    public enum LengthUnit {
        FEET(12.0),           // 1 ft = 12 inches
        INCHES(1.0),          // base unit
        YARDS(36.0),          // 1 yard = 36 inches
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // 🔹 Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // 🔹 Convert to base unit (inches)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    // 🔹 Compare method
    public boolean compare(Length that) {
        return Double.compare(this.convertToBaseUnit(),
                that.convertToBaseUnit()) == 0;
    }

    // 🔹 equals override
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return this.compare(other);
    }
    // Convert this length to another unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1: Convert current value to base unit (inches)
        double baseValue = this.convertToBaseUnit();

        // Step 2: Convert base unit to target unit
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        // Optional: round to 2 decimal places
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        // Convert to base unit (inches)
        double baseValue = value * source.getConversionFactor();

        // Convert to target unit
        double result = baseValue / target.getConversionFactor();

        return Math.round(result * 100.0) / 100.0;
    }

    // UC6: Add two lengths
    public Length add(Length thatLength) {

        if (thatLength == null) {
            throw new IllegalArgumentException("Cannot add null length");
        }

        // Convert both to base unit (inches)
        double base1 = this.convertToBaseUnit();
        double base2 = thatLength.convertToBaseUnit();

        // Add
        double sumBase = base1 + base2;

        // Convert back to unit of FIRST operand
        double result = sumBase / this.unit.getConversionFactor();

        // Round
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, this.unit);
    }

    // 🔹 Test main
    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(l1.equals(l2)); // true

        Length l3 = new Length(1.0, LengthUnit.YARDS);
        Length l4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println(l3.equals(l4)); // true

        Length l5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length l6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println(l5.equals(l6)); // true
    }
}