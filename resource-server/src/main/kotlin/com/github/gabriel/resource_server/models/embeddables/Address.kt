package com.github.gabriel.resource_server.models.embeddables

import jakarta.persistence.Embeddable

@Embeddable
class Address(val street: String, val number: Int, val zipCode: String) {
}