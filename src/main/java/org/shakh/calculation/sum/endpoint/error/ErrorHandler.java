package org.shakh.calculation.sum.endpoint.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shakh.calculation.sum.endpoint.SumCalculationModelMapper;
import org.shakh.calculation.sum.endpoint.model.in.SumOfNumberCalculationMessageIn;
import org.shakh.calculation.sum.endpoint.model.out.ReasonForRefusal;
import org.shakh.calculation.sum.endpoint.model.out.ReasonForRefusalCode;
import org.shakh.calculation.sum.endpoint.model.out.SumOfNumberCalculationMessageOut;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.ErrorMessage;

import java.util.Map;

@MessageEndpoint
@RequiredArgsConstructor
@Slf4j
public class ErrorHandler {

    private final SumCalculationModelMapper mapper;
    private final Map<Class<? extends Exception>, ReasonForRefusalCode> exceptionReasonForRefusalMap;

    @ServiceActivator(inputChannel = "errorChannel")
    public byte[] transform(ErrorMessage errorMessage) {
        SumOfNumberCalculationMessageIn messageIn = mapper.deserialize(
                (byte[]) errorMessage.getOriginalMessage().getPayload()
        );
        SumOfNumberCalculationMessageOut messageOut = initMessageOut(messageIn, errorMessage.getPayload());
        log.info("Outgoing message sent: {}", messageOut);

        return mapper.serialize(messageOut);
    }

    private SumOfNumberCalculationMessageOut initMessageOut(
            SumOfNumberCalculationMessageIn messageIn,
            Throwable throwablePayload
    ) {
        Throwable cause = throwablePayload.getCause();
        var reasonForRefusalCode = exceptionReasonForRefusalMap.getOrDefault(
                cause.getClass(),
                ReasonForRefusalCode.UNKNOWN_EXCEPTION
        );
        return new SumOfNumberCalculationMessageOut(
                messageIn.type(),
                -1,
                messageIn.id(),
                0,
                new ReasonForRefusal(reasonForRefusalCode, cause.getMessage())
        );
    }

}