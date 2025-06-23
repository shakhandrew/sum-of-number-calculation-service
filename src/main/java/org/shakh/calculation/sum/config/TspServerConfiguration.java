package org.shakh.calculation.sum.config;

import org.shakh.calculation.sum.endpoint.model.out.ReasonForRefusal;
import org.shakh.calculation.sum.endpoint.model.out.ReasonForRefusalCode;
import org.shakh.calculation.sum.process.validation.error.ValidationException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(TspServerProperties.class)
@EnableIntegration
public class TspServerConfiguration {

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory(
            TspServerProperties properties
    ) {
        var factory = new TcpNetServerConnectionFactory(properties.port());
        factory.setSoTimeout(properties.timeout());
        return factory;
    }

    @Bean
    public ByteArrayRawSerializer byteArrayRawSerializer() {
        return new ByteArrayRawSerializer();
    }

    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel errorChannel() {
        return new QueueChannel(500);
    }

    @Bean
    public TcpInboundGateway tcpInboundGateway(
            AbstractServerConnectionFactory serverConnectionFactory,
            MessageChannel inboundChannel,
            MessageChannel errorChannel
    ) {
        var gateway = new TcpInboundGateway();
        gateway.setConnectionFactory(serverConnectionFactory);
        gateway.setRequestChannel(inboundChannel);
        gateway.setErrorChannel(errorChannel);
        return gateway;
    }

    @Bean
    public Map<Class<? extends Exception>, ReasonForRefusalCode> exceptionReasonForRefusalMap() {
        var exceptionReasonForRefusalMap = new HashMap<Class<? extends Exception>, ReasonForRefusalCode>();
        exceptionReasonForRefusalMap.put(ValidationException.class, ReasonForRefusalCode.VALIDATION_EXCEPTION);
        return exceptionReasonForRefusalMap;
    }

}
