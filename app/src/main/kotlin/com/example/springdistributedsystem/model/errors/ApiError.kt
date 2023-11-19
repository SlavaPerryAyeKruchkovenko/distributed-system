package com.example.springdistributedsystem.model.errors

data class ApiError(val fields: Map<String,String?>,val global: List<String?>)