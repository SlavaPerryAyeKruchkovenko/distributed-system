package com.example.springdistributedsystem.app.repository

import com.example.springdistributedsystem.app.model.entity.LinkEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkRepository : CrudRepository<LinkEntity, Long>