package com.raghu.app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerRabbitMqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    // Queues
    @Value("${rabbitmq.start.request.queue.name}")
    private String startRequestQueueName;

    @Value("${rabbitmq.end.request.queue.name}")
    private String endRequestQueueName;

    @Value("${rabbitmq.start.response.queue.name}")
    private String startResponseQueueName;

    @Value("${rabbitmq.end.response.queue.name}")
    private String endResponseQueueName;

    // Routing Keys
    @Value("${rabbitmq.start.request.routingkey.name}")
    private String startRequestRoutingKeyName;

    @Value("${rabbitmq.end.request.routingkey.name}")
    private String endRequestRoutingKeyName;

    // Routing Keys
    @Value("${rabbitmq.start.response.routingkey.name}")
    private String startResponseRoutingKeyName;

    @Value("${rabbitmq.end.response.routingkey.name}")
    private String endResponseRoutingKeyName;

    @Bean
    public Queue startResponseQueue() {
        return new Queue(startResponseQueueName, true);
    }

    @Bean
    public Queue endResponseQueue() {
        return new Queue(endResponseQueueName, true);
    }

    @Bean
    public Binding startResponseBinding(Queue startResponseQueue) {
        return BindingBuilder.bind(startResponseQueue).to(new DirectExchange(exchangeName))
                .with(startResponseRoutingKeyName);
    }

    @Bean
    public Binding endResponseBinding(Queue endResponseQueue) {
        return BindingBuilder.bind(endResponseQueue).to(new DirectExchange(exchangeName))
                .with(endResponseRoutingKeyName);
    }

    @Bean
    public Jackson2JsonMessageConverter consumerJsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate consumerAmqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(consumerJsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Queue startRequestQueue() {
        return new Queue(startRequestQueueName, true);
    }

    @Bean
    public Queue endRequestQueue() {
        return new Queue(endRequestQueueName, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding startRequestBinding(Queue startRequestQueue, DirectExchange exchange) {
        return BindingBuilder.bind(startRequestQueue).to(exchange).with(startRequestRoutingKeyName);
    }

    @Bean
    public Binding endRequestBinding(Queue endRequestQueue, DirectExchange exchange) {
        return BindingBuilder.bind(endRequestQueue).to(exchange).with(endRequestRoutingKeyName);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate producerAmqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJsonMessageConverter());
        return rabbitTemplate;
    }
}

