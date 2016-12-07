package com.example.gpcorser.bitsandpizzas;

/**
 * Created by thoma on 01/12/2016.
 */

public class ConversionTools {

    private double multiplier;
    public enum Unit {
        GRAMS,
        KILOGRAMS,
        OUNCE,
        POUND,
        //volume
        LITER,
        MILLILITER;


        // Helper method to convert text to one of the above constants
        public static Unit fromString(String text) {
            if (text != null) {
                for (Unit myUnit : Unit.values()) {
                    if (text.equalsIgnoreCase(myUnit.toString())) {
                        return myUnit;
                    }
                }
            }

            throw new IllegalArgumentException("Cannot find a value for " + text);
        }
    }


    public ConversionTools(Unit from, Unit to) {
        double constant = 1;
        // Set the multiplier, else if fromMassUnit = toMassUnit, then it is 1
        switch (from) {
            case GRAMS:
                if (to == Unit.KILOGRAMS) {
                    constant = 0.001;
                } else if (to == Unit.OUNCE) {
                    constant = 0.035274;
                } else if (to == Unit.POUND) {
                    constant = 0.00220462;
                }
                break;
            case KILOGRAMS:
                if (to == Unit.GRAMS) {
                    constant = 1000;
                } else if (to == Unit.OUNCE) {
                    constant = 35.274;
                } else if (to == Unit.POUND) {
                    constant = 2.20462;
                }
                break;
            case OUNCE:
                if (to == Unit.GRAMS) {
                    constant = 28.3495;
                } else if (to == Unit.KILOGRAMS) {
                    constant = 0.0283495;
                } else if (to == Unit.POUND) {
                    constant = 0.0625;
                }
                break;
            case POUND:
                if (to == Unit.GRAMS) {
                    constant = 453.592;
                } else if (to == Unit.KILOGRAMS) {
                    constant = 0.453592;
                } else if (to == Unit.OUNCE) {
                    constant = 16;
                }
                break;

            //volume part
            case LITER:
                if (to == Unit.MILLILITER) {
                    constant = 1000;
                }
                break;
            case MILLILITER:
                if(to == Unit.LITER) {
                    constant = 0.001;
                }
                break;
        }

        multiplier = constant;
    }



    // Convert the Unit!
    public double convert(double input) {
        return input * multiplier;
    }
}
