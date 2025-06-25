package org.example.kotlingspringapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
class CartItem{

    constructor(id: Long, cart: Cart, product: Product) {
        this.id = id
        this.cart = cart
        this.product = product
    }


    constructor(cart: Cart, product: Product) {
        this.id = id
        this.cart = cart
        this.product = product
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    val cart: Cart

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    val product: Product
}