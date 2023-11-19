package com.example.springdistributedsystem.linkconsumer.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Link(
    @JsonProperty("id") val id: Long,
    @JsonProperty("url") val url: String,
    @JsonProperty("author") val author: String,
    @JsonProperty("status") val status: Int?
)