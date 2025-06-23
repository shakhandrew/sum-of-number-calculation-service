package org.shakh.calculation.sum.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shakh.calculation.sum.endpoint.model.MessageType;
import org.shakh.calculation.sum.endpoint.model.in.SumOfNumberCalculationMessageIn;
import org.shakh.calculation.sum.endpoint.model.out.SumOfNumberCalculationMessageOut;
import org.shakh.calculation.sum.process.SumCalculationProcessor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationResult;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;
import java.util.Arrays;

@MessageEndpoint
@Slf4j
@RequiredArgsConstructor
public class SumOfNumberCalculationEndpoint {

    private final SumCalculationModelMapper mapper;
    private final SumCalculationProcessor sumCalculationProcessor;

    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] convert(byte[] message) {
        SumOfNumberCalculationMessageIn messageIn = mapper.deserialize(message);
        log.info("Incoming message received: {}", messageIn);

        SumOfNumberCalculationModel sumOfNumberCalculationModel = mapper.toModel(messageIn);
        SumOfNumberCalculationResult calculationResult = sumCalculationProcessor.process(sumOfNumberCalculationModel);
        SumOfNumberCalculationMessageOut messageOut = initMessageOut(messageIn, calculationResult);
        log.info("Outgoing message sent: {}", messageOut);

        return mapper.serialize(messageOut);
    }

    private SumOfNumberCalculationMessageOut initMessageOut(
            SumOfNumberCalculationMessageIn messageIn,
            SumOfNumberCalculationResult calculationResult
    ) {
        return new SumOfNumberCalculationMessageOut(
                messageIn.type(),
                calculationResult.id(),
                messageIn.id(),
                calculationResult.result()
        );
    }

}
