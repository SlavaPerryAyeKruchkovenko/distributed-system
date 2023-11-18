package com.example.springdistributedsystem.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {
    companion object {
        val queueName = "LINK_QUEUE"
        val topicExchangeName = "LINK_TOPIC"
        val connectionName = "LINK_CONNECTION"
    }

    @Value("\${rabbitmq.username}")
    private lateinit var username: String

    @Value("\${rabbitmq.password}")
    private lateinit var password: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("rabbitMQ")
        connectionFactory.username = username
        connectionFactory.setPassword(password)
        return connectionFactory
    }

    @Bean
    fun amqpAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }

    @Bean
    fun jsonConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(converter: Jackson2JsonMessageConverter): RabbitTemplate {
        return RabbitTemplate(connectionFactory()).apply {
            messageConverter = converter
        }
    }

    @Bean
    fun linkQueue(): Queue {
        return Queue(queueName)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(topicExchangeName);
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(connectionName)
    }
}