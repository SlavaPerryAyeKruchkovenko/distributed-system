package com.example.springdistributedsystem.app.controller

import com.example.springdistributedsystem.app.model.Link
import com.example.springdistributedsystem.app.model.errors.ApiError
import com.example.springdistributedsystem.app.model.request.UpdateLinkStatusRequest
import com.example.springdistributedsystem.app.model.request.CreateLinkRequest
import com.example.springdistributedsystem.app.model.request.UpdateLinkRequest
import com.example.springdistributedsystem.app.service.LinkService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/DistributedSystem/v1/links")
class LinkController(private val linkService: LinkService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getLinks(): ResponseEntity<List<Link>> {
        return ResponseEntity.ok().body(linkService.getLinks())
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getLink(@PathVariable id: Long): ResponseEntity<Link> {
        val link = linkService.getLink(id)
        return if (link != null) {
            ResponseEntity.ok().body(link)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createLink(@RequestBody @Valid link: CreateLinkRequest) {
        linkService.addLink(Link(0, link.url, link.author, null))
    }

    @PatchMapping("{id}/status")
    @Operation(hidden = true)
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(
        @PathVariable id: Long,
        @RequestBody @Valid
        request: UpdateLinkStatusRequest
    ): ResponseEntity<Link> {
        return try {
            val link = linkService.updateStatus(id, request.status)
            ResponseEntity.ok().body(link)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateLink(
        @PathVariable id: Long,
        @RequestBody @Valid
        request: UpdateLinkRequest
    ): ResponseEntity<Link> {
        return try {
            val link = linkService.updateLink(id, Link(0, request.url, request.author, request.status))
            ResponseEntity.ok().body(link)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeLink(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            linkService.removeLink(id)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun onError(cause: BindException): ApiError {
        val fields = cause.fieldErrors.associate { it.field to it.defaultMessage }
        val global = cause.globalErrors.map { it.defaultMessage }
        return ApiError(fields, global)
    }
}