package org.shakh.calculation.sum.process.config;

import org.shakh.calculation.sum.config.TspServerProperties;
import org.shakh.calculation.sum.process.validation.config.ValidationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
@EnableConfigurationProperties(ValidationProperties.class)
public class SumCalculationConfiguration {

    @Bean
    public ForkJoinPool calculationForkJoinPool(
            @Value("${sum-of-number-calculation.parallelism}") int contextParallelism
    ) {
        return new ForkJoinPool(contextParallelism);
    }

}
