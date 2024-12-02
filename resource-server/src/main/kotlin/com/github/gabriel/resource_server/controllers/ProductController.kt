package com.github.gabriel.resource_server.controllers

import com.github.gabriel.resource_server.dtos.CreateProductRequestDTO
import com.github.gabriel.resource_server.models.Order
import com.github.gabriel.resource_server.models.OrderProduct
import com.github.gabriel.resource_server.models.Product
import com.github.gabriel.resource_server.models.embeddables.Address
import com.github.gabriel.resource_server.repositories.ProductRepository
import jakarta.persistence.EntityManager
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/products"], consumes = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    val repository: ProductRepository,
    val entityManager: EntityManager,
) {
    @GetMapping
    fun getAll(@AuthenticationPrincipal jwt: Jwt): Iterable<Product> {
        println(jwt.toString())
        return repository.findAll()
    }

    @PostMapping
    @Transactional
    fun create(@RequestBody request: List<CreateProductRequestDTO>): ResponseEntity<Void> {
        println("Entering in create method")
        val order = Order(
            Address(street="Rodovia Família Requieri", number=95, zipCode="293600000")
        )

        request.forEach {
            entityManager.persist(OrderProduct(it.toProduct(), order, 1))
        }

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/")
            .build()
            .toUri()
        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/order/{id}")
    fun getByOrderId(@PathVariable id: Long): ResponseEntity<Order> {
        val order = entityManager.find(Order::class.java, id)
        return ResponseEntity.ok(order)
    }
}