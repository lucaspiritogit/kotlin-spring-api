package org.example.kotlingspringapi.dto

import org.example.kotlingspringapi.entity.Cart

data class CartResponseDTO(
    val id: Long,
    val totalInCents: Int,
    val items: List<ProductResponseDTO>
)

fun Cart.toResponseDTO() = CartResponseDTO(
    id = this.id,
    totalInCents = this.totalInCents,
    items = this.cartItems.mapNotNull { it.product?.toResponseDTO() }
)