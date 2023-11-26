package com.example.springdistributedsystem.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class SpringAppApplication

fun main(args: Array<String>) {
    runApplication<SpringAppApplication>(*args)
}
