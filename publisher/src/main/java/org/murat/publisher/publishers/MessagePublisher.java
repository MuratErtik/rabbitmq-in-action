package org.murat.publisher.publishers;

import lombok.RequiredArgsConstructor;
import org.murat.publisher.dtos.OrderMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    private void publishHello() {

        OrderMessage orderMessage = new OrderMessage(1L,"CREATED");

        //String exchange, String routingKey, Object message
        // "" -> it is default exchange (direct exchange)
        rabbitTemplate.convertAndSend("","example-queue", orderMessage);
    }


}
