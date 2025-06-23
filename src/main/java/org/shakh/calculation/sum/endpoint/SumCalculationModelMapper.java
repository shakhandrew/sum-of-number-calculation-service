package org.shakh.calculation.sum.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.endpoint.model.in.SumOfNumberCalculationMessageIn;
import org.shakh.calculation.sum.endpoint.model.out.SumOfNumberCalculationMessageOut;
import org.shakh.calculation.sum.process.model.MessageType;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SumCalculationModelMapper {

    private final ObjectMapper objectMapper;

    public SumOfNumberCalculationModel toModel(SumOfNumberCalculationMessageIn messageIn) {
        return new SumOfNumberCalculationModel(
                messageIn.system(),
                MessageType.valueOf(messageIn.type()),
                messageIn.id(),
                messageIn.values()
        );
    }

    public SumOfNumberCalculationMessageIn deserialize(byte[] messageIn) {
        try {
            return objectMapper.readValue(messageIn, SumOfNumberCalculationMessageIn.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] serialize(SumOfNumberCalculationMessageOut messageOut) {
        try {
            return objectMapper.writeValueAsBytes(messageOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
