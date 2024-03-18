package com.songareeit.tacocloud.messaging.jms.provider;

import com.songareeit.tacocloud.domain.Order;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
public interface OrderMessagingService {

    void sendOrder(Order order);
}
