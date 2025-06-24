package org.shakh.calculation.sum.process;

import lombok.RequiredArgsConstructor;
import org.shakh.calculation.sum.process.model.SumOfNumberCalculationModel;
import org.springframework.stereotype.Service;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class SumCalculationService {

    private final ForkJoinPool calculationForkJoinPool;

    /**
     * Расчет суммы чисел. Используется Spliterators для дальнейшего разделения массива на части.
     *
     * @param sumOfNumberCalculationModel Модель со значениями для расчета.
     * @return Результат расчета.
     */
    public Double calculateSum(SumOfNumberCalculationModel sumOfNumberCalculationModel) {
        Spliterator.OfDouble spliterator = Spliterators.spliterator(sumOfNumberCalculationModel.values(), 0);

        var task = new SumOfNumberTask(spliterator);
        return calculationForkJoinPool.invoke(task);
    }

}
