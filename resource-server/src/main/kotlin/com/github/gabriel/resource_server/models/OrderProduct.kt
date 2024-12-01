package com.github.gabriel.resource_server.models

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrimaryKeyJoinColumn
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "order_product")
@SQLDelete(sql = "UPDATE order_product SET deleted = false")
@SQLRestriction("deleted is false")
class OrderProduct(
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @PrimaryKeyJoinColumn
    val product: Product,
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @PrimaryKeyJoinColumn
    val order: Order,
    val quantity: Int) : BaseModel()