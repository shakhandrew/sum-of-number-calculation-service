package org.shakh.calculation.sum.process.validation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "sum-of-number-calculation.validator")
public record ValidationProperties(
        Integer arrayLength,
        Set<String> availableSenderSystems
) {
}
