package org.shakh.calculation.sum.process;

import lombok.extern.slf4j.Slf4j;

import java.util.Spliterator;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoubleConsumer;

@Slf4j
public class SumOfNumberTask extends RecursiveTask<Double> {

    private static final int THRESHOLD = 2;

    private final Spliterator.OfDouble spliterator;

    public SumOfNumberTask(Spliterator.OfDouble spliterator) {
        this.spliterator = spliterator;
    }

    /**
     * Задача для расчета суммы чисел. Используется Spliterators для дальнейшего разделения массива на части.
     * Разбиение задачи на несколько и использование join для ее выполнения и получения результата.
     * Шаг до которого разбиваем массив 2.
     *
     * @return Результат расчета.
     */
    @Override
    protected Double compute() {
        if (spliterator.estimateSize() <= THRESHOLD) {
            return sum(spliterator);
        }

        Spliterator.OfDouble otherHalf = spliterator.trySplit();
        if (otherHalf == null) {
            return sum(spliterator);
        }

        var leftTask = new SumOfNumberTask(otherHalf);
        var rightTask = new SumOfNumberTask(spliterator);

        leftTask.fork();
        rightTask.fork();

        Double rightResult = rightTask.join();
        Double leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    private Double sum(Spliterator.OfDouble spliterator) {
        Double[] sum = {0.0};
        spliterator.forEachRemaining((DoubleConsumer) v -> {
            log.debug("Add element: {}", v);
            sum[0] += v;
        });

        log.debug("Sum: {}", sum[0]);
        return sum[0];
    }
}
