package com.example.demo.controllers

import com.example.demo.models.currency.UsdBrlConversionDto
import com.example.demo.models.catFact.RandomCatFactDto
import com.example.demo.services.publicApis.ApiDataLoaderService
import com.example.demo.util.calcExecutionTime
import com.example.demo.util.getStartTime
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public-api")
class PublicApiController(val apiDataLoaderService: ApiDataLoaderService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @GetMapping("/cat")
    fun getCatFact(): ResponseEntity<RandomCatFactDto> {
        val startTime = getStartTime()
        val res: ResponseEntity<RandomCatFactDto> = apiDataLoaderService.loadCatInfoApi()
        logger.info("response: ${res.toString()}")

        calcExecutionTime(startTime)
        return res
    }

    @GetMapping("/dolar")
    fun getDolarConversion(): ResponseEntity<UsdBrlConversionDto> {
        val startTime = getStartTime()
        val res: ResponseEntity<UsdBrlConversionDto> = apiDataLoaderService.loadCurrencyInfoApi()
        logger.info("response: ${res.toString()}")

        calcExecutionTime(startTime)
        return res;
    }
}
