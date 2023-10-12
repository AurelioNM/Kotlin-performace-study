package com.example.demo.controllers

import com.example.demo.models.catFact.RandomCatFactDto
import com.example.demo.services.workers.ParallelWorkerService
import com.example.demo.services.workers.SequentialWorkerService
import com.example.demo.util.calcExecutionTime
import com.example.demo.util.getStartTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.concurrent.ConcurrentLinkedQueue

@RestController
@RequestMapping("/workers")
class WorkersController(
    val sequentialWorkerService: SequentialWorkerService,
    val parallelWorkerService: ParallelWorkerService,
) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @GetMapping("/sequential-cat")
    fun executeSequentialWorker(): MutableList<ResponseEntity<RandomCatFactDto>> {
        val startTime = getStartTime()
        return sequentialWorkerService.executeSequentialCatRequests()
            .also {
                calcExecutionTime(startTime)
            }
    }

    @GetMapping("/parallel-cat")
    suspend fun executeParallelWorker(): ConcurrentLinkedQueue<RandomCatFactDto> {
        val startTime = getStartTime()
        return  parallelWorkerService.executeParallelCatRequests()
            .also {
                calcExecutionTime(startTime)
            }
    }

    @GetMapping("/parallel-test")
    suspend fun executeParallelWorkerTest() {
        val startTime = getStartTime()

        val jobs = List(4) {
            GlobalScope.async {
                parallelWorkerService.executeParallelTest()
            }
        }
        jobs.awaitAll()

        calcExecutionTime(startTime)
    }
}
