package com.example.springdistributedsystem.controller

import com.example.springdistributedsystem.model.Link
import com.example.springdistributedsystem.model.request.LinkRequest
import com.example.springdistributedsystem.service.LinkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/DistributedSystem/v1/links")
class LinkController(private val linkService: LinkService) {
    @GetMapping
    fun getLinks() {
        linkService.getLinks()
    }

    @GetMapping("{id}")
    fun getLink(@PathVariable id: Long) {
        linkService.getLink(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createLink(@RequestBody link: LinkRequest) {
        linkService.addLink(Link(0, link.url, link.author))
    }
}