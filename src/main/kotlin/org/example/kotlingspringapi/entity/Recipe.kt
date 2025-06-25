package org.example.kotlingspringapi.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import org.example.kotlingspringapi.dto.RecipeResponseDTO

@Entity
class Recipe {

    constructor(name: String, products: MutableSet<Product> = mutableSetOf())  {
        this.name = name
        this.products.addAll(products)
    }

    constructor(id: Long,name: String, products: MutableSet<Product> = mutableSetOf())  {
        this.id = id
        this.name = name
        this.products.addAll(products)
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var name: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "recipe_products",
        joinColumns = [JoinColumn(name = "recipe_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    val products: MutableSet<Product> = mutableSetOf()
}

