package com.example.springdistributedsystem.repository

import com.example.springdistributedsystem.model.entity.LinkEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkRepository : CrudRepository<LinkEntity, Long>