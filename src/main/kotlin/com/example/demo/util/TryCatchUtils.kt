package com.example.demo.util

import org.slf4j.Logger
import org.springframework.http.HttpStatus

fun entityNotFound(entityName: String, entityId: String, logger: Logger): HttpStatus {
    logger.warn("$entityName not found: $entityId")
    return HttpStatus.NOT_FOUND
}