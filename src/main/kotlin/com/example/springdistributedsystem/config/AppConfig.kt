package com.example.springdistributedsystem.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:validation.properties")
class AppConfig {}