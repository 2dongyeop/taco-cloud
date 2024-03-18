package com.songareeit.tacocloud.messaging.jms.provider;

import com.songareeit.tacocloud.domain.Order;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
@Configuration
public class DestinationConfig {

    @Bean
    public Destination destination() {
        return new ActiveMQQueue("tacocloud.order.queue");
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
