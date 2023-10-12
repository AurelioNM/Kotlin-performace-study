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
class ParallelWorkerService(private val apiDataLoaderService: ApiDataLoaderService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val webClient = WebClient.create()
    private val randonCartFactUrl = "https://catfact.ninja/fact?max_length=140"

    suspend fun executeParallelCatRequests(): ConcurrentLinkedQueue<RandomCatFactDto> {
        logger.info("executing Parallel requests")

        val responseList = ConcurrentLinkedQueue<RandomCatFactDto>()
        val jobs = List(10) { index ->
            GlobalScope.launch(Dispatchers.IO) {
                logger.info("launching request number: $index in coroutine: ${coroutineContext[Job]}")
                val response = webClient.get()
                    .uri(randonCartFactUrl)
                    .retrieve()
                    .awaitBody<RandomCatFactDto>()

                logger.info("request $index completed in coroutine: ${coroutineContext[Job]}")
                responseList.add(response)
            }
        }
        jobs.forEach { it.join() }
        return responseList
    }

    //        GlobalScope.launch(Dispatchers.IO) {
//        withContext(Dispatchers.IO) {
//        logger.info("request in coroutine: ${coroutineContext[Job]}")
//        logger.info("response in coroutine ${coroutineContext[Job]}: $response")
//    }

    suspend fun executeParallelTest() {
        logger.info("executing Parallel test")
        val response = webClient.get()
            .uri(randonCartFactUrl)
            .retrieve()
            .awaitBody<RandomCatFactDto>()

        logger.info("response: $response")
    }
}