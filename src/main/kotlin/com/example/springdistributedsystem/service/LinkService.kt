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
}