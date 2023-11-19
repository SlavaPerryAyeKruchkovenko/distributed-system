package com.example.springdistributedsystem.app.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "LINKS")
data class LinkEntity(
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(name = "link") @NotNull var url: String = "",

    @NotNull @Size(min = 1, max = 32) var author: String = "",

    @Min(100) @Max(599) var status: Int? = null,
)