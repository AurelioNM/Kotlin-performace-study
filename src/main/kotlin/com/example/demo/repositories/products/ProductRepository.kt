package com.example.demo.repositories.products

import com.example.demo.models.Product
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepository(val db: JdbcTemplate) {

    fun findProducts(): List<Product> {
        val sql = "SELECT * FROM products"
        return db.query(sql) { response, _ ->
            Product(
                response.getString("id"),
                response.getString("name"),
                response.getBigDecimal("amount"),
            )
        }
    }

    fun findProductById(id: UUID): Product? {
        val sql = "SELECT * FROM products WHERE id = ?"
        val results = db.query(sql, id ) { response, _ ->
            Product(
                response.getString("id"),
                response.getString("name"),
                response.getBigDecimal("amount"),
            )
        }
        return results.firstOrNull()
    }

    fun saveProduct(product: Product) {
        val sql = "INSERT INTO products (name, amount) VALUES (?,?)"
        db.update(sql, product.name, product.amount)
    }

    fun deleteProduct(id: UUID): Boolean {
        val sql = "DELETE FROM products WHERE id = ?"
        val rowsDeleted = db.update(sql, id)
        return rowsDeleted > 0
    }
}
