package org.example.kotlingspringapi.integration

import org.example.kotlingspringapi.entity.Cart
import org.example.kotlingspringapi.entity.CartItem
import org.example.kotlingspringapi.entity.Product
import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.repository.cart.CartRepository
import org.example.kotlingspringapi.repository.cart_item.CartItemRepository
import org.example.kotlingspringapi.repository.product.ProductRepository
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CartControllerIntegrationTest @Autowired constructor(
    val mockMvc: MockMvc,
    val cartRepository: CartRepository,
    val recipeRepository: RecipeRepository,
    val productRepository: ProductRepository,
    val cartItemRepository: CartItemRepository
) {

    @BeforeEach
    fun cleanup() {
        cartRepository.deleteAll()
        recipeRepository.deleteAll()
        productRepository.deleteAll()
        cartItemRepository.deleteAll()
    }

    @Test
    fun `should add recipe to cart via endpoint`() {
        val product = productRepository.save(Product(name = "Apple", priceInCents = 200))
        val recipe = recipeRepository.save(Recipe(name = "Fruit Salad", products = mutableSetOf(product)))
        val cart = cartRepository.save(Cart())

        mockMvc.perform(
            post("/carts/${cart.id}/add_recipe/${recipe.id}")
        )
            .andExpect(status().isOk)

        val updatedCart = cartRepository.findById(cart.id).get()
        assertEquals(200, updatedCart.totalInCents)
        assertEquals(1, updatedCart.cartItems.size)
    }

    @Test
    fun `should get cart by id`() {
        val cart = cartRepository.save(Cart())

        mockMvc.perform(get("/carts/${cart.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(cart.id))
    }

    @Test
    fun `should remove recipe from cart via endpoint`() {
        val product = productRepository.save(Product(name = "Apple", priceInCents = 200))
        val recipe = recipeRepository.save(Recipe(name = "Fruit Salad", products = mutableSetOf(product)))
        val cart = cartRepository.save(Cart())

        mockMvc.perform(
            post("/carts/${cart.id}/add_recipe/${recipe.id}")
        )
            .andExpect(status().isOk)

        mockMvc.perform(
            delete("/carts/${cart.id}/recipes/${recipe.id}")
        )
            .andExpect(status().isOk)

        val updatedCart = cartRepository.findById(cart.id).get()
        assertEquals(0, updatedCart.totalInCents)
        assertEquals(0, updatedCart.cartItems.size)
    }
}
