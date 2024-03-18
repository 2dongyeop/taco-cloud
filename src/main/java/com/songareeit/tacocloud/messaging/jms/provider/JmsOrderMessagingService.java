package com.songareeit.tacocloud.messaging.jms.provider;

import com.songareeit.tacocloud.domain.Order;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description JMS 로 Order 메시지를 전송하는 서비스
 */
@Service
@RequiredArgsConstructor
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final Destination destination;

    @Value("${messaging.queue.order.name}")
    private String orderQueueName;

    @Override
    public void sendOrder(Order order) {

        /* 커스텀 헤더를 추가한 뒤 전송 예시 */

        // v4 : v3를 메서드 참조로
        jms.convertAndSend(orderQueueName, order, this::addOrderSource);

        // v3 : v2를 람다로
//        jms.convertAndSend(orderQueueName, order, message -> {
//            message.setStringProperty("X_ORDER_SOURCE", "WEB");
//            return message;
//        });

        // v2
//        jms.convertAndSend(orderQueueName, order, new MessagePostProcessor() {
//                    @Override
//                    public Message postProcessMessage(Message message) throws JMSException {
//                        message.setStringProperty("X_ORDER_SOURCE", "WEB");
//                        return message;
//                    }
//                }
//        );

        // v1
//        jms.send(orderQueueName, session -> {
//            final Message message = session.createObjectMessage(order);
//            message.setStringProperty("X_ORDER_SOURCE", "WEB");
//            return message;
//        });

        /**
         *  단순 메시지 전송 버전별 예시 */
        // v4
        // jms.convertAndSend(orderQueueName, order);

        // v3
        // jms.send(
        //        orderQueueName,
        //        session -> session.createObjectMessage(order));

        // v2
        //jms.send(
        //        destination,
        //        session -> session.createObjectMessage(order));


        // v1
        // jms.send(session -> session.createObjectMessage(order));
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
