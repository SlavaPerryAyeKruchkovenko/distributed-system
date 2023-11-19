package com.example.springdistributedsystem.linkconsumer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController {
    @GetMapping("/ready")
    fun ready(): ResponseEntity<String> = ResponseEntity.ok("true")

    @GetMapping("/alive")
    fun alive(): ResponseEntity<String> = ResponseEntity.ok("true")
}