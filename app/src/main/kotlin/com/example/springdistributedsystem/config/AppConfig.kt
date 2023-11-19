package com.example.springdistributedsystem.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.ResourceBundleMessageSource

@Configuration
@PropertySource("classpath:validation.properties")
class AppConfig {
    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("validation") // указывает на файл validation.properties
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}