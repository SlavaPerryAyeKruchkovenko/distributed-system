package com.example.springdistributedsystem.linkconsumer.service

import com.example.springdistributedsystem.linkconsumer.model.Link
import com.example.springdistributedsystem.linkconsumer.model.UpdateLinkStatus
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

@Service
class LinkService {
    private val webClient = WebClient.create()

    @Cacheable("urlStatusCache")
    fun getStatusByUrl(url: String): Int {
        return try {
            val response = webClient.get().uri(url).retrieve().onStatus({
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
            }.toBodilessEntity().block()
            response?.statusCode?.value() ?: 500
        } catch (e: Exception) {
            500
        }
    }

    fun updateLinkStatus(id: Long, status: Int) {
        val appUrl = "http://nginx/api/DistributedSystem/v1/links/{id}/status"
        val updateStatus = BodyInserters.fromValue(UpdateLinkStatus(status))
        webClient.patch().uri(appUrl, id).body(updateStatus).retrieve().toEntity(Link::class.java).subscribe {
            println("change link status into $status response: ${it.body}")
        }
    }
}