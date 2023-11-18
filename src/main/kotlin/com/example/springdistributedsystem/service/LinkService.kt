package com.example.springdistributedsystem.service

import com.example.springdistributedsystem.model.Link
import com.example.springdistributedsystem.model.entity.LinkEntity
import com.example.springdistributedsystem.producer.LinkProducer
import com.example.springdistributedsystem.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class LinkService(private val linkRepository: LinkRepository, private val linkProducer: LinkProducer) {
    fun addLink(link: Link) {
        linkRepository.save(LinkEntity(url = link.url, author = link.author))
        linkProducer.sendLink(link)
    }

    fun getLink(id: Long): Link? {
        val entity = linkRepository.findById(id)
        if (entity.isPresent) {
            return Link(entity.get().id, entity.get().url, entity.get().author, entity.get().status)
        }
        return null
    }

    fun getLinks(): List<Link> {
        return linkRepository.findAll().map {
            Link(it.id, it.url, it.author, it.status)
        }
    }

    fun updateStatus(id: Long, status: Int): Link {
        val entity = linkRepository.findById(id)
        if (entity.isPresent) {
            val link = entity.get()
            link.status = status
            linkRepository.save(link)
            return Link(link.id, link.url, link.author, link.status)
        } else {
            throw RuntimeException("Link not found")
        }
    }

    fun updateLink(id: Long, link: Link): Link {
        val entity = linkRepository.findById(id)
        if (entity.isPresent) {
            val linkEntity = entity.get()
            linkEntity.status = link.status
            linkEntity.url = link.url
            linkEntity.author = link.author
            linkRepository.save(linkEntity)
            return Link(linkEntity.id, link.url, link.author, link.status)
        } else {
            throw RuntimeException("Link not found")
        }
    }
}