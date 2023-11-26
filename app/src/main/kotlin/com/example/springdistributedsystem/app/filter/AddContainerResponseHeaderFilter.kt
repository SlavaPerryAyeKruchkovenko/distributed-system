package com.example.springdistributedsystem.app.filter

import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value

@WebFilter("/api/DistributedSystem/v1/*")
class AddContainerResponseHeaderFilter : Filter {
    @Value("\${spring.container.name}")
    private lateinit var containerName: String
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        /*if(request != null){
            println( request.getAttribute("x-forwarded-for"))
            println(request.requestId)
            println(request.serverName)
            println(request.serverPort)
            println(request.attributeNames)
            println(request.toString())
        }*/
        if(response is HttpServletResponse && chain != null) {
            response.setHeader("X-Container", containerName)
            chain.doFilter(request, response)
        }
    }
}