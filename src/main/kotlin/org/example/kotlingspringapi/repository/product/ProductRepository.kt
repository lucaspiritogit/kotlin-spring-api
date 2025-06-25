package org.example.kotlingspringapi.repository.product

import org.example.kotlingspringapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}