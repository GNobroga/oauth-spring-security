package com.github.gabriel.resource_server.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.SQLSelect
import java.math.BigDecimal

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = false WHERE id = ?")
@SQLRestriction("deleted is false")
class Product(name: String, price: BigDecimal) : BaseModel() {

    var name: String = name

    var price: BigDecimal = price
}