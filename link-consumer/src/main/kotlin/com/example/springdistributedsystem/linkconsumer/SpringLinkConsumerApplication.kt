package com.example.springdistributedsystem.linkconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class SpringLinkConsumerApplication

fun main(args: Array<String>) {
    runApplication<SpringLinkConsumerApplication>(*args)
}
