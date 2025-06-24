package org.shakh.calculation.sum.endpoint.model.out;

import org.shakh.calculation.sum.endpoint.model.MessageType;

public record SumOfNumberCalculationMessageOut(
        MessageType type,
        long id,
        long request,
        double result,
        ReasonForRefusal reason
) {

    public SumOfNumberCalculationMessageOut(MessageType type, long id, long requestId, double result) {
        this(type, id, requestId, result, null);
    }

    @Override
    public String toString() {
        return "SumOfNumberCalculationRs{" +
                "type=" + type +
                ", id=" + id +
                ", request=" + request +
                ", result=" + result +
                ", reason=" + reason +
                '}';
    }

}
