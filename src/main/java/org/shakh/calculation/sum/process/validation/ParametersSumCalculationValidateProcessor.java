package org.shakh.calculation.sum.process.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.validation.validator.ParametersSumCalculationValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParametersSumCalculationValidateProcessor {

    private final List<ParametersSumCalculationValidator> validators;

    public void process(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        validators.forEach(validator -> validator.validate(sumOfNumberCalculationModel));
    }
}
