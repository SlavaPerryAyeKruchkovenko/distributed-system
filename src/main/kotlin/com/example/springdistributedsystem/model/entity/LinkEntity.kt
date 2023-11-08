package com.example.springdistributedsystem.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "LINKS")
data class LinkEntity(
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(name = "link") @NotNull val url: String = "",

    @NotNull @Size(min = 1, max = 32) val author: String = "",
)