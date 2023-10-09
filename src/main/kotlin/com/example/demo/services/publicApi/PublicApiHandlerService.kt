package com.example.demo.services.publicApi

import com.example.demo.models.catFact.RandomCatFactDto
import com.example.demo.models.currency.UsdBrlConversionDto
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class PublicApiHandlerService(val apiDataLoaderService: ApiDataLoaderService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val randonCartFactUrl = "https://catfact.ninja/fact?max_length=140"
    private val dolarConversionUrl = "https://economia.awesomeapi.com.br/json/last/USD-BRL"

    fun loadCurrencyInfoApi(): ResponseEntity<UsdBrlConversionDto> {
        logger.info("loading usd currency info...")
        val res: UsdBrlConversionDto? = apiDataLoaderService.sendRequest(dolarConversionUrl, UsdBrlConversionDto::class.java)
        return apiDataLoaderService.buildResponseEntity(res)
    }

    fun loadCatInfoApi(): ResponseEntity<RandomCatFactDto> {
        logger.info("loading a random cat fact...")
        val res: RandomCatFactDto? =  apiDataLoaderService.sendRequest(randonCartFactUrl, RandomCatFactDto::class.java)
        return apiDataLoaderService.buildResponseEntity(res)
    }
}