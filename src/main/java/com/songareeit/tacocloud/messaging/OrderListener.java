package com.songareeit.tacocloud.messaging;

import com.songareeit.tacocloud.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final KitchenUI ui;

    @JmsListener(destination = "${messaging.queue.order.name}")
    public void receiveOrder(final Order order) {
        ui.displayOrder(order);
    }
}