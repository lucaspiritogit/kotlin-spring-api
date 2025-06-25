package org.example.kotlingspringapi.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.kotlingspringapi.entity.Cart
import org.example.kotlingspringapi.entity.CartItem
import org.example.kotlingspringapi.entity.Product
import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.repository.cart.CartRepository
import org.example.kotlingspringapi.repository.cart_item.CartItemRepository
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import java.util.Optional
import kotlin.test.assertTrue

class CartServiceTest {

    private val cartRepository: CartRepository = mockk()
    private val recipeRepository: RecipeRepository = mockk()
    private val cartItemRepository: CartItemRepository = mockk()
    private val cartService = CartService(cartRepository, recipeRepository, cartItemRepository)

    @Test
    fun `should add recipe to cart`() {
        val product = Product(id = 1, name = "Apple", priceInCents = 200)
        val recipe = Recipe(id = 1, name = "Fruit Salad", products = mutableSetOf(product))
        val cart = Cart(id = 1, totalInCents = 0)

        every { cartRepository.findById(1) } returns Optional.of(cart)
        every { recipeRepository.findById(1) } returns Optional.of(recipe)
        every { cartItemRepository.findByCartIdAndProductId(1L, 1L) } returns null
        every { cartRepository.save(any()) } returns cart

        cartService.addRecipeToCart(1, 1)

        assertEquals(200, cart.totalInCents)
        assertEquals(1, cart.cartItems.size)

        verify { cartRepository.save(cart) }
    }

    @Test
    fun `should not add recipe to cart if the recipe already exists`() {
        val product = Product(id = 1, name = "Apple", priceInCents = 200)
        val recipe = Recipe(id = 1, name = "Fruit Salad", products = mutableSetOf(product))
        val cart = Cart(id = 1, totalInCents = 0)
        val cartItem = CartItem(id = 1, cart = cart, product = product)

        every { cartRepository.findById(1) } returns Optional.of(cart)
        every { recipeRepository.findById(1) } returns Optional.of(recipe)
        every { cartItemRepository.findByCartIdAndProductId(1L, 1L) } returns cartItem
        every { cartRepository.save(any()) } returns cart

        cartService.addRecipeToCart(1, 1)

        assertEquals(0, cart.totalInCents)
        assertTrue{ cart.cartItems.isEmpty() }

        verify { cartRepository.save(cart) }
    }

    @Test
    fun `should remove recipe from cart`() {
        val product = Product(id = 1, name = "Apple", priceInCents = 200)
        val recipe = Recipe(id = 1, name = "Fruit Salad", products = mutableSetOf(product))
        val cart = Cart(id = 1, totalInCents = 200)

        val cartItem = CartItem(id = 1, cart = cart, product = product)
        cart.cartItems.add(cartItem)

        every { cartRepository.findById(1) } returns Optional.of(cart)
        every { recipeRepository.findById(1) } returns Optional.of(recipe)
        every { cartItemRepository.findByCartIdAndProductId(1, 1) } returns cartItem
        every { cartItemRepository.delete(cartItem) } returns Unit
        every { cartRepository.save(any()) } returns cart

        cartService.removeRecipeFromCart(1, 1)

        assertEquals(0, cart.cartItems.size)
        assertEquals(0, cart.totalInCents)

        verify { cartItemRepository.delete(cartItem) }
        verify { cartRepository.save(cart) }
    }

    @Test
    fun `should find cart by id`() {
        val cart = Cart(id = 1, totalInCents = 0)

        every { cartRepository.findById(1) } returns Optional.of(cart)

        assertNotNull{cart}
    }
}
