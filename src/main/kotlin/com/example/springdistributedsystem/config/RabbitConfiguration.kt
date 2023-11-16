package com.example.springdistributedsystem.config

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {
    companion object {
        val queueName = "LINK_QUEUE"
    }

    @Value("\${rabbitmq.username}")
    private lateinit var username: String

    @Value("\${rabbitmq.password}")
    private lateinit var password: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("localhost")
        connectionFactory.username = username
        connectionFactory.setPassword(password)
        return connectionFactory
    }

    @Bean
    fun amqpAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }

    @Bean
    fun linkQueue(): Queue {
        return Queue(queueName)
    }
}