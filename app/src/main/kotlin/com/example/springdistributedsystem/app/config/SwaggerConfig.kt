package com.example.springdistributedsystem.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/*
@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("your.package.name")) // замените "your.package.name" на пакет, содержащий ваши контроллеры
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .servers(serverList())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Your API Title")
            .description("Your API Description")
            .version("1.0.0")
            .build()
    }

    private fun serverList(): List<Server> {
        return listOf(Server().url("http://localhost/"))
    }
}*/
