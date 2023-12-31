package com.example.demo.models.currency

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyDto(
    val code: String,
    val codein: String,
    val name: String,
    val high: Double,
    val low: Double,
    val varBid: Double,
    val pctChange: Double,
    val bid: Double,
    val ask: Double,
    val timestamp: String,
    @JsonProperty("create_date") val createDate: String,
)
