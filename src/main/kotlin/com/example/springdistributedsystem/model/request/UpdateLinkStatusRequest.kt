package com.example.springdistributedsystem.model.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

data class UpdateLinkStatusRequest(
    @field:NotEmpty(message = "{validation.field.link.status.empty}")
    @field:Min(100, message = "{validation.field.link.status.min}")
    @field:Max(599, message = "{validation.field.link.status.max}")
    val status: Int,
)
