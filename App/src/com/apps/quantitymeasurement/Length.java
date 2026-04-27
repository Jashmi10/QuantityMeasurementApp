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