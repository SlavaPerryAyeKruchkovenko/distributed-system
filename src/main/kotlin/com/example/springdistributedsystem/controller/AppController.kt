package com.example.springdistributedsystem.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class AppController {
    @GetMapping
    fun test(): ResponseEntity<String> = ResponseEntity.ok("connection is ok")
}