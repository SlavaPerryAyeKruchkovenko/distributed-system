package com.example.springdistributedsystem.controller

import com.example.springdistributedsystem.model.Link
import com.example.springdistributedsystem.model.request.UpdateLinkStatusRequest
import com.example.springdistributedsystem.model.request.CreateLinkRequest
import com.example.springdistributedsystem.model.request.UpdateLinkRequest
import com.example.springdistributedsystem.service.LinkService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/DistributedSystem/v1/links")
class LinkController(private val linkService: LinkService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getLinks(): List<Link> {
        return linkService.getLinks()
    }

    @GetMapping("{id}")
    fun getLink(@PathVariable id: Long): ResponseEntity<Link> {
        val link = linkService.getLink(id)
        return if (link != null) {
            ResponseEntity.ok(link)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("{id}/link")
    fun updateLinkStatus(
        @PathVariable id: Long,
        @Valid @RequestBody
        request: UpdateLinkStatusRequest
    ): ResponseEntity<Link> {
        return try {
            val link = linkService.updateStatus(id, request.status)
            ResponseEntity.ok(link)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }

    }

    @PutMapping("{id}")
    fun updateLink(
        @PathVariable id: Long,
        @Valid @RequestBody
        request: UpdateLinkRequest
    ): ResponseEntity<Link> {
        return try {
            val link = linkService.updateLink(id, Link(0, request.url, request.url, request.status))
            ResponseEntity.ok(link)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createLink(@RequestBody link: CreateLinkRequest) {
        linkService.addLink(Link(0, link.url, link.author, null))
    }
}