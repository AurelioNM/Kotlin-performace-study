package com.example.demo.models

import java.math.BigDecimal

data class Product(
    val id: String?,
    val name: String,
    val amount: BigDecimal,
) {
    var amountUsd: BigDecimal? = null
        set(value) {
            field = value
        }
}
