package com.example.springdistributedsystem.linkconsumer.consumer

import org.springframework.web.reactive.function.client.WebClient
import com.example.springdistributedsystem.linkconsumer.config.RabbitConfiguration
import com.example.springdistributedsystem.linkconsumer.model.Link
import com.example.springdistributedsystem.linkconsumer.model.UpdateLinkStatus
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@Component
class LinkConsumer {
    @RabbitListener(queues = [RabbitConfiguration.QUEUE_NAME])
    fun listener(link: Link) {
        println(link)
        val webClient = WebClient.create()
        try {
            webClient.get()
                .uri(link.url)
                .retrieve()
                .onStatus({
                    it.is4xxClientError || it.is5xxServerError
                }) { response ->
                    response.bodyToMono(String::class.java).flatMap { body ->
                        Mono.error(
                            WebClientResponseException.create(
                                response.statusCode().value(),
                                response.statusCode().toString(),
                                HttpHeaders.EMPTY,
                                body.toByteArray(),
                                null
                            )
                        )
                    }
                }
                .toBodilessEntity()
                .subscribe({
                    changeLinkStatus(webClient, link.id, it.statusCode.value())
                    println("change link status ${it.statusCode}")
                }, {
                    if (it is WebClientResponseException) {
                        changeLinkStatus(webClient, link.id, it.statusCode.value())
                    } else {
                        changeLinkStatus(webClient, link.id, 500)
                    }
                })
        } catch (e: Exception) {
            changeLinkStatus(webClient, link.id, 500)
        }
    }

    fun changeLinkStatus(client: WebClient, id: Long, status: Int) {
        val appUrl = "http://nginx/api/DistributedSystem/v1/links/{id}/status"
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