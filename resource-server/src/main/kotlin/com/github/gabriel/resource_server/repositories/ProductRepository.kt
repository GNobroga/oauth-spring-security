package com.github.gabriel.resource_server.repositories

import com.github.gabriel.resource_server.models.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long> {
}