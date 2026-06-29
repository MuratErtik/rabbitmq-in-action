package org.murat.publisher.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

//    @Bean
//    public Queue exampleQueue(){
//        // durable: false, exclusive: false, autoDelete: false
//        return new Queue("example-queue", false, false, false);
//    }

    @Bean
    public Queue exampleQueue() {
        return QueueBuilder.durable("example-queue") // durable means if rabbitmq restart it is still there
                .withArgument("x-dead-letter-exchange", "")            // default exchange
                .withArgument("x-dead-letter-routing-key", "example-queue.dlq")
                .build();
    }

    //with this bean able to sending DTO to consumer
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //using for sending a message.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
