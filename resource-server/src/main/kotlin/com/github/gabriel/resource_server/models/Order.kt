package com.github.gabriel.resource_server.models

import com.github.gabriel.resource_server.models.embeddables.Address
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import kotlin.jvm.Transient

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = false")
@SQLRestriction("deleted is false")
class Order(
    @AttributeOverride(name = "number", column = Column(name = "streetNumber"))
    val address: Address
) : BaseModel() {

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private var listOrderProduct: MutableList<OrderProduct>? = null

    @Transient
    var products: MutableList<Product> = mutableListOf()

    @PostLoad
    fun onPostLoad() {
        products = products ?: mutableListOf()
        listOrderProduct?.forEach {
            products.add(it.product)
        }
    }
}