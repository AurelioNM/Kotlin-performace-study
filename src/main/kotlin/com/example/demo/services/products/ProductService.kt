package com.example.demo.services.products

import com.example.demo.models.Product
import com.example.demo.models.currency.UsdBrlConversionDto
import com.example.demo.repositories.products.ProductRepository
import com.example.demo.services.publicApis.ApiDataLoaderService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class ProductService(
    val apiDataLoaderService: ApiDataLoaderService,
    val cacheImplementation: CacheImplementation,
    val repository: ProductRepository,
): IProductService {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    fun executePersistencyOfCurrencyCache() {
        logger.info("executing persitency of currency cache...")
        val currencyInfo: ResponseEntity<UsdBrlConversionDto> = apiDataLoaderService.loadCurrencyInfoApi()
        logger.info("response of api: $currencyInfo")

        val name = currencyInfo.body?.usdbrl?.code
        val amount = currencyInfo.body?.usdbrl?.ask

        if (currencyInfo.statusCode == HttpStatus.OK) {
            logger.info("currency name: $name; currency amount $amount")
            cacheImplementation.setCurrencyCache(name!!, amount!!)
        }
    }

    fun checkIfCurrencyCacheExists() {
        logger.info("checking if currency cache exists...")
        val currencyCache = cacheImplementation.getCurrencyCache("USD")

        if (currencyCache == null) {
            logger.info("cache doesnt exist")
            executePersistencyOfCurrencyCache()
        } else {
            logger.info("currency cache: $currencyCache")
        }
    }

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