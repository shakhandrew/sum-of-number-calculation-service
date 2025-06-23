package org.shakh.calculation.sum.process.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DoubleUtils {

    private DoubleUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Double scale(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
