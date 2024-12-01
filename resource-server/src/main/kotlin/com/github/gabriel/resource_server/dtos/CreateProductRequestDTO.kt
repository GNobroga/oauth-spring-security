package com.github.gabriel.resource_server.dtos

import com.github.gabriel.resource_server.models.Product
import java.math.BigDecimal

data class CreateProductRequestDTO(
    val name: String,
    val price: BigDecimal,) {

    fun toProduct(): Product {
        return Product(name, price)
    }
}