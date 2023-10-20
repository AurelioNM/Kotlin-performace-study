package com.example.demo.services.products

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

@Service
class CacheImplementation() {

    private val logger = LoggerFactory.getLogger(javaClass.name)
    private val jedis: Jedis = JedisPool("127.0.0.1", 6395).resource

    init {
        jedis.flushAll()
    }

    fun setCurrencyCache(currencyName: String, currencyAmount: Double) {
        logger.info("setting currency cache...")
        val result = jedis.set(currencyName, currencyAmount.toString())
        logger.info(result)
    }

    fun getCurrencyCache(currencyName: String): String? {
        logger.info("geting currency cache...")
        return jedis.get(currencyName) ?: return null
    }
}