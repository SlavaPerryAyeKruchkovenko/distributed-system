package com.example.springdistributedsystem.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDistributedSystemApplication

fun main(args: Array<String>) {
	runApplication<SpringDistributedSystemApplication>(*args)
}
