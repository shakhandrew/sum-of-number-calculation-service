package org.shakh.calculation.sum.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tcp.server")
public record TspServerProperties(
        int port,
        int timeout
) {
}
