package com.songareeit.tacocloud.messaging;

import com.songareeit.tacocloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 18
 * @description
 */
@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(Order order) {
        // TODO: Beef this up to do more than just log the received taco.
        //       To display it in some sort of UI.
        log.info("RECEIVED ORDER:  " + order);
    }

}
