package org.shakh.calculation.sum.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.endpoint.error.SumCalculationEndpointException;
import org.shakh.calculation.sum.endpoint.model.in.SumOfNumberCalculationMessageIn;
import org.shakh.calculation.sum.endpoint.model.out.SumOfNumberCalculationMessageOut;
import org.shakh.calculation.sum.process.model.MessageType;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SumCalculationModelMapper {

    private final ObjectMapper objectMapper;

    public SumOfNumberCalculationModel toModel(SumOfNumberCalculationMessageIn messageIn) {
        return new SumOfNumberCalculationModel(
                messageIn.system(),
                Optional.ofNullable(messageIn.type())
                        .map(m -> MessageType.valueOf(m.name()))
                        .orElse(null),
                messageIn.id(),
                messageIn.values()
        );
    }

    public SumOfNumberCalculationMessageIn deserialize(byte[] messageIn) {
        try {
            return objectMapper.readValue(messageIn, SumOfNumberCalculationMessageIn.class);
        } catch (IOException e) {
            throw new SumCalculationEndpointException("Message deserialize error");
        }
    }

    public byte[] serialize(SumOfNumberCalculationMessageOut messageOut) {
        try {
            return objectMapper.writeValueAsBytes(messageOut);
        } catch (IOException e) {
            throw new SumCalculationEndpointException("Message serialize error");
        }
    }

}
