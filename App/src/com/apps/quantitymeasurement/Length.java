package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    // 🔹 Enum for units
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

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

    // 🔹 Compare logic
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

    // 🔹 Optional main for quick test
    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are equal? " + l1.equals(l2)); // true
    }
}