package com.xc.cmsclient.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Value("${xc.mq.publishRoutingKey}")
    public String publishRoutingKey;

    @Value("${xc.mq.publishQueue}")
    public String publishQueue;

    @Value("${xc.mq.deleteRoutingKey}")
    public String deleteRoutingKey;

    @Value("${xc.mq.deletehQueue}")
    public String deletehQueue;

    @Value("${xc.mq.exchange}")
    private String exchange;

    @Bean("publishQueue")
    public Queue publishQueue() {
        return new Queue(publishQueue);
    }

    @Bean("deleteQueue")
    public Queue deleteQueue() {
        return new Queue(deletehQueue);
    }

    @Bean("exchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    public Binding binding1(@Qualifier("publishQueue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(publishRoutingKey).noargs();
    }

    @Bean
    public Binding binding2(@Qualifier("deleteQueue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(deleteRoutingKey).noargs();
    }

    /**
     * 使用jackson的序列化器
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
