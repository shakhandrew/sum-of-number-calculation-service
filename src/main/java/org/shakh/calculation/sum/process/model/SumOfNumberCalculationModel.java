package org.shakh.calculation.sum.process.model;

public record SumOfNumberCalculationModel(
        String system,
        MessageType type,
        long id,
        double[] values
) {
}
