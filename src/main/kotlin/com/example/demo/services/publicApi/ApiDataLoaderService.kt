package com.example.demo.services.publicApi

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ApiDataLoaderService {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val restTemplate = RestTemplate()

    fun <T> sendRequest(apiUrl: String, responseType: Class<T>): T? {
        logger.info("sending request to api: $apiUrl")
        return restTemplate.getForObject(apiUrl, responseType)
    }

    fun <T> buildResponseEntity(response: T?): ResponseEntity<T> {
        return response?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}