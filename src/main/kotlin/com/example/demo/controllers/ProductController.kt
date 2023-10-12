package com.example.demo.controllers

import com.example.demo.models.Product
import com.example.demo.services.products.IProductService
import com.example.demo.util.calcExecutionTime
import com.example.demo.util.getStartTime
import jakarta.websocket.MessageHandler.Partial
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/product")
class ProductController(val service: IProductService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @GetMapping()
    fun findProducts(): ResponseEntity<List<Product>> {
        val startTime = getStartTime()

        val products = service.findProducts()

        calcExecutionTime(startTime)
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}")
    fun findProductById(@PathVariable id: UUID): ResponseEntity<Product> {
        val startTime = getStartTime()

        val product = service.findProductById(id)
        val res: ResponseEntity<Product> =
            if (product != null) ResponseEntity.ok(product)
            else ResponseEntity(HttpStatus.NOT_FOUND)

        calcExecutionTime(startTime)
        return res
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveProduct(@RequestBody product: Product) {
        val startTime = getStartTime()

        service.saveProduct(product)
        calcExecutionTime(startTime)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduct(@PathVariable id: UUID) {
        try {
            val startTime = getStartTime()

            service.deleteProduct(id)
            calcExecutionTime(startTime)
        } catch (e: ResponseStatusException) {
            throw e
        }
    }
}
