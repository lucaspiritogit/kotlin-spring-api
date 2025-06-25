package org.example.kotlingspringapi.dto

import org.example.kotlingspringapi.entity.Product

data class ProductResponseDTO(
    val id: Long,
    val name: String,
    val price_in_cents: Int
)

fun Product.toResponseDTO() = ProductResponseDTO(
    id = this.id ?: 0,
    name = this.name,
    price_in_cents = this.priceInCents,
)