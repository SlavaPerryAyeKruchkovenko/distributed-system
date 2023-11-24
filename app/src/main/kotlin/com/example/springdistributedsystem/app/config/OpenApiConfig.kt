package com.example.springdistributedsystem.app.config

import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springdoc.core.customizers.OpenApiCustomizer
@Configuration
class OpenApiConfig {
    @Bean
    fun customizer(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            openApi.servers = mutableListOf(
                Server().url("http://localhost")
            )
        }
    }
}