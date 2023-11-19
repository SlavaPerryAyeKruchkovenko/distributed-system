package com.example.springdistributedsystem.app.model.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class UpdateLinkRequest(
    @field:NotEmpty(message = "{validation.field.link.url.empty}")
    val url: String,
    @field:NotEmpty(message = "{validation.field.link.author.empty}")
    val author: String,
    @field:Min(100, message = "{validation.field.link.status.min}")
    @field:Max(599, message = "{validation.field.link.status.max}")
    val status: Int?
)
