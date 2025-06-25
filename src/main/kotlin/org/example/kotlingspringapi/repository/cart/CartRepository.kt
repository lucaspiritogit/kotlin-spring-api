package org.example.kotlingspringapi.repository.cart

import org.example.kotlingspringapi.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CartRepository : JpaRepository<Cart, Long> {

    override fun findById(id: Long): Optional<Cart>

}