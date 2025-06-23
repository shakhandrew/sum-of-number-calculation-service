package org.shakh.calculation.sum.endpoint.model.out;

public record SumOfNumberCalculationMessageOut(
        String type,
        long id,
        long requestId,
        double result,
        ReasonForRefusal reason
) {

    public SumOfNumberCalculationMessageOut(String type, long id, long requestId, double result) {
        this(type, id, requestId, result, null);
    }

    @Override
    public String toString() {
        return "SumOfNumberCalculationRs{" +
                "type=" + type +
                ", id=" + id +
                ", requestId=" + requestId +
                ", result=" + result +
                ", reason=" + reason +
                '}';
    }

}
