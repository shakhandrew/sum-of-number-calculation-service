package org.shakh.calculation.sum.process;

import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationResult;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.utils.DoubleUtils;
import org.shakh.calculation.sum.process.validation.ParametersSumCalculationValidateProcessor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SumCalculationProcessor {

    private final ParametersSumCalculationValidateProcessor validateProcessor;
    private final SumCalculationService sumCalculationService;

    public SumOfNumberCalculationResult process(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        validateProcessor.process(sumOfNumberCalculationModel);
        Double result = sumCalculationService.calculateSum(sumOfNumberCalculationModel);

        return initResult(sumOfNumberCalculationModel, result);
    }

    private SumOfNumberCalculationResult initResult(
            SumOfNumberCalculationModel sumOfNumberCalculationModel,
            Double result
    ) {
        return new SumOfNumberCalculationResult(
                new Random().nextLong(),
                sumOfNumberCalculationModel.id(),
                DoubleUtils.scale(result)
        );
    }

}
