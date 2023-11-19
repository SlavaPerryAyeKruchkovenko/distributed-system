package com.example.springdistributedsystem.app.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Link(
    @JsonProperty("id") var id: Long,
    @JsonProperty("url") val url: String,
    @JsonProperty("author") val author: String,
    @JsonProperty("status") val status: Int?
)