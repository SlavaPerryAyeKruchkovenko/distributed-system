package com.example.springdistributedsystem.app.model.request

import jakarta.validation.constraints.NotEmpty

data class CreateLinkRequest(
    @field:NotEmpty(message = "{validation.field.link.url.empty}")
    val url: String,
    @field:NotEmpty(message = "{validation.field.link.author.empty}")
    val author: String
)