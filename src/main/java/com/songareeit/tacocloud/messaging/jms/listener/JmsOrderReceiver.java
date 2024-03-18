package com.songareeit.tacocloud.messaging.jms.listener;

import com.songareeit.tacocloud.domain.Order;
import com.songareeit.tacocloud.messaging.OrderReceiver;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
@Component
@RequiredArgsConstructor
public class JmsOrderReceiver implements OrderReceiver {
    private final JmsTemplate jms;
    private final MessageConverter converter;

    @Value("${messaging.queue.order.name}")
    private String orderQueueName;

    @Override
    public Order receiveOrder() throws JMSException {
        // v2
        return (Order) jms.receiveAndConvert(orderQueueName);

        // v1
//        Message message = jms.receive(orderQueueName);
//        return (Order) converter.fromMessage(message);
    }
}
