package com.example.demo.services.products

import com.example.demo.models.Product
import com.example.demo.repositories.products.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class ProductService(val repository: ProductRepository): IProductService {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    override fun findProducts(): List<Product> {
        logger.info("getting all products")
        return repository.findProducts()
    }

    override fun findProductById(id: UUID): Product? {
        logger.info("getting product by id: $id")
        return repository.findProductById(id)
    }

    override fun saveProduct(product: Product) {
        logger.info("persisting new product: ${product.name}")
        repository.saveProduct(product)
    }

    override fun deleteProduct(id: UUID) {
        logger.info("deleting product: $id")
        val wasProductDeleted = repository.deleteProduct(id)
        if (!wasProductDeleted) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist")
    }
}