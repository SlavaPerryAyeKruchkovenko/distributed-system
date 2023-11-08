package com.example.springdistributedsystem.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "LINKS")
class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0;

    @NotNull
    private val name: String = ""

    @NotNull
    @Size(min = 1, max = 32)
    private val author: String = ""
}