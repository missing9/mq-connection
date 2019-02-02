package com.personal.mqconnection;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfig {

    @Bean
    public Queue saveQueue() {
        return new Queue("save");
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue("delete");
    }

    @Bean
    public Queue getQueue() {
        return new Queue("get");
    }

    @Bean
    public Queue findAllQueue() {
        return new Queue("findAll");
    }

    @Bean
    public Queue saveReturnQueue() {
        return new Queue("saveReturn");
    }

}
