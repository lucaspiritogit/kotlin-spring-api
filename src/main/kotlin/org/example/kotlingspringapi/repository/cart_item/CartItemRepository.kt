package org.example.kotlingspringapi.repository.cart_item

import org.example.kotlingspringapi.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository: JpaRepository<CartItem, Long> {
    fun findByCartIdAndProductId(cartId: Long, productId: Long): CartItem?
    fun deleteByCartIdAndProductId(cartId: Long, productId: Long)
}