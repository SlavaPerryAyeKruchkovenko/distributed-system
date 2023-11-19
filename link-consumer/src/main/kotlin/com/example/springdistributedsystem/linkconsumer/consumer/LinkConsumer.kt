package com.example.springdistributedsystem.linkconsumer.consumer

import com.example.springdistributedsystem.linkconsumer.config.RabbitConfiguration
import com.example.springdistributedsystem.linkconsumer.model.Link
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class LinkConsumer {
    @RabbitListener(queues = [RabbitConfiguration.queueName])
    fun listener(link: Link) {
        println(link);
    }
}