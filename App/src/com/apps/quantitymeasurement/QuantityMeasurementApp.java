package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic method
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // Method with parameters
    public static void demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                   double value2, Length.LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        System.out.println("Comparing: " + value1 + " " + unit1 +
                " and " + value2 + " " + unit2 +
                " → Equal: " + l1.equals(l2));
    }

    public static Length demonstrateLengthConversion(double value,
                                                     Length.LengthUnit fromUnit,
                                                     Length.LengthUnit toUnit) {

        double result = Length.convert(value, fromUnit, toUnit);
        return new Length(result, toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {

        Length result1 = demonstrateLengthConversion(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        System.out.println("1 ft = " + result1);

        Length length = new Length(2.0, Length.LengthUnit.YARDS);
        Length result2 = demonstrateLengthConversion(length,
                Length.LengthUnit.INCHES);

        System.out.println("2 yards = " + result2);
    }
}