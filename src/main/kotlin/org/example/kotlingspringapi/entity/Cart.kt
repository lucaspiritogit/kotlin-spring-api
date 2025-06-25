package org.example.kotlingspringapi.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Cart {
    constructor()
    constructor(totalInCents: Int, cartItems: MutableSet<CartItem>) {
        this.totalInCents = totalInCents
        this.cartItems = cartItems
    }

    constructor(id: Long, totalInCents: Int) {
        this.id = id
        this.totalInCents = totalInCents
    }

    constructor(id: Long, totalInCents: Int, cartItems: MutableSet<CartItem>) {
        this.id = id
        this.totalInCents = totalInCents
        this.cartItems = cartItems
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "total_in_cents")
    var totalInCents: Int = 0

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var cartItems: MutableSet<CartItem> = mutableSetOf()
}