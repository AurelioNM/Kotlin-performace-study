package com.example.demo.services.workers

import com.example.demo.models.catFact.RandomCatFactDto
import com.example.demo.services.publicApis.ApiDataLoaderService
import com.example.demo.util.calcExecutionTime
import com.example.demo.util.getStartTime
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.servlet.function.ServerResponse.async
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.awaitBody
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.coroutineContext


@Service
class SequentialWorkerService(private val apiDataLoaderService: ApiDataLoaderService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val webClient = WebClient.create()
    private val randonCartFactUrl = "https://catfact.ninja/fact?max_length=140"

    fun executeSequentialCatRequests(): MutableList<ResponseEntity<RandomCatFactDto>> {
        logger.info("executing SEQUENTIAL requests")
        val startTime = getStartTime()

        val responseEntityList = mutableListOf<ResponseEntity<RandomCatFactDto>>()
        repeat(10) {
            logger.info("request number: $it")
            val responseEntity = webClient.get()
                .uri(randonCartFactUrl)
                .retrieve()
                .toEntity(RandomCatFactDto::class.java)
                .block()

            responseEntity?.let { entity ->
                responseEntityList.add(entity)
            }
        }
        logger.info("list size: ${responseEntityList.size}")

        calcExecutionTime(startTime)
        return responseEntityList
    }
}