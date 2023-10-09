package com.example.demo.models.currency

import com.fasterxml.jackson.annotation.JsonProperty

data class UsdBrlConversionDto(
    @JsonProperty("USDBRL")
    val usdbrl: CurrencyDto
)