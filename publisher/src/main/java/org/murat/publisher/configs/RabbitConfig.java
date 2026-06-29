package org.murat.publisher.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue exampleQueue(){
        // durable: false, exclusive: false, autoDelete: false
        return new Queue("example-queue", false, false, false);
    }
}
