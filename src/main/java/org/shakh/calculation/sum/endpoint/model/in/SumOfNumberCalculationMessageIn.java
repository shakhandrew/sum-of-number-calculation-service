package org.shakh.calculation.sum.endpoint.model.in;

import org.shakh.calculation.sum.endpoint.model.MessageType;

import java.util.Arrays;

public record SumOfNumberCalculationMessageIn(
        String system,
        MessageType type,
        long id,
        double[] values
) {
    @Override
    public String toString() {
        return "SumOfNumberCalculationMessageIn{" +
                "system=" + system +
                ", type=" + type +
                ", id=" + id +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
