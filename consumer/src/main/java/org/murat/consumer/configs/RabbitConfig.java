package org.murat.consumer.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


//    @Bean
//    public Queue exampleQueue() {
//        // durable: false, exclusive: false, autoDelete: false
//        //it must be a same conf with publisher
//        return new Queue("example-queue", false, false, false);
//    }


    //must be a same conf with publisher
    @Bean
    public Queue exampleQueue() {
        return QueueBuilder.durable("example-queue") // durable means if rabbitmq restart it is still there
                .withArgument("x-dead-letter-exchange", "")            // default exchange
                .withArgument("x-dead-letter-routing-key", "example-queue.dlq")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("example-queue.dlq").build();
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        // header and __TypeId__ ignored.
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }

    //unnecessary for consumer

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
//                                         Jackson2JsonMessageConverter converter) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(converter);
//        return template;
//    }
}
