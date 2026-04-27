package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    // 🔹 Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    // 🔹 Convert to base unit (FEET handled by LengthUnit)
    private double convertToBaseUnit() {
        return unit.toBase(this.value);
    }

    // 🔹 Compare method
    public boolean compare(Length that) {
        if (that == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        return Double.compare(
                this.convertToBaseUnit(),
                that.convertToBaseUnit()
        ) == 0;
    }

    // 🔹 equals override
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return this.compare(other);
    }

    // 🔹 Convert this length to another unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.unit.toBase(this.value);
        double converted = targetUnit.fromBase(baseValue);

        return new Length(round(converted), targetUnit);
    }

    // 🔹 Static conversion utility
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue = source.toBase(value);
        double result = target.fromBase(baseValue);

        return Math.round(result * 100.0) / 100.0;
    }

    // 🔹 UC6: Add → result in unit of first operand
    public Length add(Length thatLength) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        return addAndConvert(thatLength, this.unit);
    }

    // 🔹 UC7: Add with target unit
    public Length add(Length thatLength, LengthUnit targetUnit) {

        if (thatLength == null || targetUnit == null) {
            throw new IllegalArgumentException("Length or target unit cannot be null");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(thatLength.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        return addAndConvert(thatLength, targetUnit);
    }

    // 🔹 Internal helper (UC6 + UC7 reuse)
    private Length addAndConvert(Length thatLength, LengthUnit targetUnit) {

        double base1 = this.unit.toBase(this.value);
        double base2 = thatLength.unit.toBase(thatLength.value);

        double sum = base1 + base2;

        double result = targetUnit.fromBase(sum);

        return new Length(round(result), targetUnit);
    }

    // 🔹 Rounding helper
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // 🔹 toString (important for output)
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
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

        // UC6
        System.out.println(l1.add(l2)); // Quantity(2.0, FEET)

        // UC7
        System.out.println(l1.add(l2, LengthUnit.INCHES)); // Quantity(24.0, INCHES)
    }
}