package com.example.springdistributedsystem.model.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class UpdateLinkStatusRequest(
    @field:NotNull(message = "{validation.properties.empty}")
    @field:Min(100, message = "{validation.properties.min}")
    @field:Max(599, message = "{validation.properties.max}")
    val status: Int,
)
