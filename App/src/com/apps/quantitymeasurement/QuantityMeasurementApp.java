package com.apps.quantitymeasurement;
import com.apps.quantitymeasurement.LengthUnit;

public class QuantityMeasurementApp {

    // Generic method
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // Method with parameters
    public static void demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                   double value2, LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        System.out.println("Comparing: " + value1 + " " + unit1 +
                " and " + value2 + " " + unit2 +
                " → Equal: " + l1.equals(l2));
    }

    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {

        double result = Length.convert(value, fromUnit, toUnit);
        return new Length(result, toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // UC6: Demonstrate addition
    /**
     * Demonstrate addition with target unit (UC7)
     */
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            LengthUnit targetUnit) {

        if (length1 == null || length2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        return length1.add(length2, targetUnit);
    }

    public static void main(String[] args) {

        Length result1 = demonstrateLengthConversion(1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        System.out.println("1 ft = " + result1);

        Length length = new Length(2.0, LengthUnit.YARDS);
        Length result2 = demonstrateLengthConversion(length,
                LengthUnit.INCHES);

        System.out.println("2 yards = " + result2);
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        // UC7: Addition with explicit target unit

        Length resultInFeet = demonstrateLengthAddition(l1, l2, LengthUnit.FEET);
        System.out.println("Result in FEET: " + resultInFeet);

        Length resultInInches = demonstrateLengthAddition(l1, l2, LengthUnit.INCHES);
        System.out.println("Result in INCHES: " + resultInInches);

        Length resultInYards = demonstrateLengthAddition(l1, l2, LengthUnit.YARDS);
        System.out.println("Result in YARDS: " + resultInYards);
    }
}