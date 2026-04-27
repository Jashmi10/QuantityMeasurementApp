package com.apps.quantitymeasurement;

public class Weight {

    private double value;
    private WeightUnit unit;

    // 🔹 Constructor
    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    // 🔹 Convert to base (kg)
    private double convertToBaseUnit() {
        return unit.toBase(this.value);
    }

    // 🔹 Compare
    public boolean compare(Weight that) {
        if (that == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        return Double.compare(
                this.convertToBaseUnit(),
                that.convertToBaseUnit()
        ) == 0;
    }

    // 🔹 equals
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;

        return this.compare(other);
    }

    // 🔹 Convert to another unit
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = this.unit.toBase(this.value);
        double converted = targetUnit.fromBase(base);

        return new Weight(round(converted), targetUnit);
    }

    // 🔹 Static conversion
    public static double convert(double value, WeightUnit source, WeightUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double base = source.toBase(value);
        double result = target.fromBase(base);

        return Math.round(result * 100.0) / 100.0;
    }

    // 🔹 UC6: Add → result in first operand unit
    public Weight add(Weight that) {
        if (that == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        return addAndConvert(that, this.unit);
    }

    // 🔹 UC7: Add with target unit
    public Weight add(Weight that, WeightUnit targetUnit) {

        if (that == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(that.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        return addAndConvert(that, targetUnit);
    }

    // 🔹 Internal helper
    private Weight addAndConvert(Weight that, WeightUnit targetUnit) {

        double base1 = this.unit.toBase(this.value);
        double base2 = that.unit.toBase(that.value);

        double sum = base1 + base2;

        double result = targetUnit.fromBase(sum);

        return new Weight(round(result), targetUnit);
    }

    // 🔹 Round helper
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // 🔹 toString
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // 🔹 Main for testing
    public static void main(String[] args) {

        // Equality
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        System.out.println(w1.equals(w2)); // true

        // Conversion
        System.out.println(w1.convertTo(WeightUnit.GRAM)); // 1000 g

        // Addition UC6
        Weight w3 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w4 = new Weight(1000.0, WeightUnit.GRAM);
        System.out.println(w3.add(w4)); // 2 kg

        // Addition UC7
        System.out.println(w3.add(w4, WeightUnit.GRAM)); // 2000 g

        // Pounds example
        Weight w5 = new Weight(1.0, WeightUnit.POUND);
        Weight w6 = new Weight(0.453592, WeightUnit.KILOGRAM);
        System.out.println(w5.equals(w6)); // true
    }
}