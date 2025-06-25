package org.example.kotlingspringapi.service

import io.mockk.every
import io.mockk.mockk
import org.example.kotlingspringapi.entity.Product
import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RecipeServiceTest {

    private val recipeRepository: RecipeRepository = mockk()

    @Test
    fun `should get all recipes`() {
        val product = Product(id = 1, name = "Apple", priceInCents = 200)
        val recipe = Recipe(id = 1, name = "Fruit Salad", products = mutableSetOf(product))

        every { recipeRepository.findAll() } returns mutableListOf(recipe)

        assertFalse { recipeRepository.findAll().isEmpty() }
    }
}