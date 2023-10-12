package com.example.demo.services.products

import com.example.demo.models.Message
import com.example.demo.models.Product
import java.util.*

interface IProductService {

    fun findProducts(): List<Product>

    fun findProductById(id: UUID): Product?

    fun saveProduct(message: Product)

    fun deleteProduct(id: UUID)
}