package org.murat.consumer.consumers;

import lombok.extern.slf4j.Slf4j;
import org.murat.consumer.dtos.OrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = "example-queue")
    public void receive(OrderMessage message) {
        System.out.println("message: " + message.orderId() + " - " + message.status());
    }

    @RabbitListener(queues = "example-queue.dlq")
    public void handleDead(OrderMessage message) {
        log.error("the message which does not process went to DLQ: {}", message);
        //alert, manuel debugging, saving DB etc...
    }
}
