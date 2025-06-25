package org.example.kotlingspringapi.dto

import org.example.kotlingspringapi.entity.Recipe

data class RecipeResponseDTO(
    val id: Long,
    val name: String,
    val ingredients: List<ProductResponseDTO>
)

fun Recipe.toResponseDTO() = RecipeResponseDTO(
    id = this.id,
    name = this.name,
    ingredients = this.products.map { it.toResponseDTO() }
)