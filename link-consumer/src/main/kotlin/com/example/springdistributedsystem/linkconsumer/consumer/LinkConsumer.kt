package com.example.springdistributedsystem.linkconsumer.consumer

import com.example.springdistributedsystem.linkconsumer.config.RabbitConfiguration
import com.example.springdistributedsystem.linkconsumer.model.Link
import com.example.springdistributedsystem.linkconsumer.service.LinkService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class LinkConsumer(private val linkService: LinkService) {
    @RabbitListener(queues = [RabbitConfiguration.QUEUE_NAME])
    fun listener(link: Link) {
        println("message received $link")
        val status = linkService.getStatusByUrl(link.url)
        linkService.updateLinkStatus(link.id, status)
    }
}