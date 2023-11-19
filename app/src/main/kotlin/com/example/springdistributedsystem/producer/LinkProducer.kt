package com.example.springdistributedsystem.producer

import com.example.springdistributedsystem.model.Link
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component


@Component
class LinkProducer(private val rabbitTemplate: RabbitTemplate, private val queue: Queue) {

    fun sendLink(link: Link) {
        rabbitTemplate.convertAndSend(queue.name, link)
        println("Сообщение отправлено: $link")
    }
}