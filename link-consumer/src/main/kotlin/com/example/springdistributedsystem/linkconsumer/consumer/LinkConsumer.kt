package com.example.springdistributedsystem.linkconsumer.consumer

import org.springframework.web.reactive.function.client.WebClient
import com.example.springdistributedsystem.linkconsumer.config.RabbitConfiguration
import com.example.springdistributedsystem.linkconsumer.model.Link
import com.example.springdistributedsystem.linkconsumer.model.UpdateLinkStatus
import com.example.springdistributedsystem.linkconsumer.service.LinkService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters

@Component
class LinkConsumer(private val linkService: LinkService) {
    @RabbitListener(queues = [RabbitConfiguration.QUEUE_NAME])
    fun listener(link: Link) {
        println(link)
        val status = linkService.getStatusByUrl(link.url)
        linkService.updateLinkStatus(link.id, status)
    }
}