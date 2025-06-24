package org.shakh.calculation.sum.process.validation.validator;

import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.validation.config.ValidationProperties;
import org.shakh.calculation.sum.process.validation.error.ValidationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderSystemValidator implements ParametersSumCalculationValidator {

    private final ValidationProperties validationProperties;

    /**
     * Валидация входит ли система в списов доверенных.
     */
    @Override
    public void validate(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        if (!validationProperties.availableSenderSystems().contains(sumOfNumberCalculationModel.system())) {
            throw new ValidationException("System is not trusted");
        }
    }

}
