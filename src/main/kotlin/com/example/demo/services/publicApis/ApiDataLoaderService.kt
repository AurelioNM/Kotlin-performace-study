package com.example.demo.services.publicApis

import com.example.demo.models.catFact.RandomCatFactDto
import com.example.demo.models.currency.UsdBrlConversionDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class ApiDataLoaderService(val publicApiHandlerService: PublicApiHandlerService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val randonCartFactUrl = "https://catfact.ninja/fact?max_length=140"
    private val dolarConversionUrl = "https://economia.awesomeapi.com.br/json/last/USD-BRL"

    fun loadCurrencyInfoApi(): ResponseEntity<UsdBrlConversionDto> {
        logger.info("loading usd currency info...")
        val res: UsdBrlConversionDto? = publicApiHandlerService.sendRequest(dolarConversionUrl, UsdBrlConversionDto::class.java)
        return publicApiHandlerService.buildResponseEntity(res)
    }

    fun loadCatInfoApi(): ResponseEntity<RandomCatFactDto> {
        logger.info("loading a random cat fact...")
        val res: RandomCatFactDto? =  publicApiHandlerService.sendRequest(randonCartFactUrl, RandomCatFactDto::class.java)
        return publicApiHandlerService.buildResponseEntity(res)
    }
}