package com.songareeit.tacocloud.messaging;

import com.songareeit.tacocloud.domain.Order;
import jakarta.jms.JMSException;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
public interface OrderReceiver {

    Order receiveOrder() throws JMSException;
}
