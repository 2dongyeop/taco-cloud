package com.songareeit.tacocloud.messaging.jms.provider;

import com.songareeit.tacocloud.domain.Order;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
@Configuration
@EnableJms
public class DestinationConfig {

    @Value("${messaging.queue.order.name}")
    private String orderQueueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue(orderQueueName);
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        final HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }
}
