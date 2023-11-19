package com.example.springdistributedsystem.app.model.errors

data class ApiError(val fields: Map<String,String?>,val global: List<String?>)