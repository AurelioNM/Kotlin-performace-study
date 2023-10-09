package com.example.demo.util

import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Timer")

fun getStartTime(): Long = System.currentTimeMillis()

fun calcExecutionTime(startTime: Long): Long {
    val executionTime = System.currentTimeMillis() - startTime
    logger.info("execution time: ${executionTime}ms")
    return executionTime
}