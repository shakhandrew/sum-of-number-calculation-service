package org.shakh.calculation.sum.process.validation.validator;

import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.validation.config.ValidationProperties;
import org.shakh.calculation.sum.process.validation.error.ValidationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArrayOfValuesValidator implements ParametersSumCalculationValidator {

    private static final String MESSAGE = "The array size is not %s";

    private final ValidationProperties validationProperties;

    @Override
    public void validate(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        if (sumOfNumberCalculationModel.values().length != validationProperties.arrayLength()) {
            throw new ValidationException(String.format(MESSAGE, validationProperties.arrayLength()));
        }
    }

}
