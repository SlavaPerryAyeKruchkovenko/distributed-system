package com.example.springdistributedsystem.linkconsumer.consumer

import org.springframework.web.reactive.function.client.WebClient;
import com.example.springdistributedsystem.linkconsumer.config.RabbitConfiguration
import com.example.springdistributedsystem.linkconsumer.model.Link
import com.example.springdistributedsystem.linkconsumer.model.UpdateLinkStatus
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.toEntity
import reactor.core.publisher.Mono

@Component
class LinkConsumer {
    @RabbitListener(queues = [RabbitConfiguration.queueName])
    fun listener(link: Link) {
        val webClient = WebClient.create()
        try {
            webClient.get()
                .uri(link.url)
                .retrieve()
                .toEntity(Void::class.java)
                .subscribe {
                    changeLinkStatus(webClient, link.id, it.statusCode.value())
                }
            println(link)
        } catch (e: Exception) {
            changeLinkStatus(webClient, link.id, 500)
        }
    }

    fun changeLinkStatus(client: WebClient, id: Long, status: Int) {
        val appUrl = "http://app:3000/api/DistributedSystem/v1/links/{id}/status"
        val updateStatus = BodyInserters.fromValue(UpdateLinkStatus(status))
        client.patch()
            .uri(appUrl, id)
            .body(updateStatus)
            .retrieve()
            .toEntity(Link::class.java)
            .subscribe {
                println("change link status into $status response: ${it.body}")
            }
    }
}