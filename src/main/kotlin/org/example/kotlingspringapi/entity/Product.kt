package org.example.kotlingspringapi.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Product {

    constructor(name: String, priceInCents: Int) {
        this.name = name
        this.priceInCents = priceInCents
    }

    constructor(id:Long, name: String, priceInCents: Int) {
        this.id = id
        this.name = name
        this.priceInCents = priceInCents
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var name: String = ""

    @Column(name = "price_in_cents", nullable = false)
    var priceInCents: Int = 0
}