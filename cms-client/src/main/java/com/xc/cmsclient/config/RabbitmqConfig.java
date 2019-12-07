package com.xc.cmsclient.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 吴后荣
 * @date : 2019/12/4 00:35
 * @description :
 */
@Configuration
public class RabbitmqConfig {

    @Value("${xc.mq.routingKey}")
    public String routingKey;

    @Value("${xc.mq.queue}")
    public String queue;

    @Value("${xc.mq.exchange}")
    private String exchange;

    @Bean("queue")
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean("exchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

}
