package org.shakh.calculation.sum.process;

import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.shakh.calculation.sum.process.utils.DoubleUtils;
import org.springframework.stereotype.Service;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class SumCalculationService {

    private final ForkJoinPool calculationForkJoinPool;

    public Double calculateSum(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        Spliterator.OfDouble spliterator = Spliterators.spliterator(sumOfNumberCalculationModel.values(), 0);

        var task = new SumOfNumberTask(spliterator);
        var result = calculationForkJoinPool.invoke(task);

        return result;
    }

}
