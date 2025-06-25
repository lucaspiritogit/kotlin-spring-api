package org.example.kotlingspringapi.service

import jakarta.transaction.Transactional
import org.example.kotlingspringapi.dto.CartResponseDTO
import org.example.kotlingspringapi.dto.toResponseDTO
import org.example.kotlingspringapi.entity.Cart
import org.example.kotlingspringapi.entity.CartItem
import org.example.kotlingspringapi.exceptions.NotFoundException
import org.example.kotlingspringapi.repository.cart.CartRepository
import org.example.kotlingspringapi.repository.cart_item.CartItemRepository
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.springframework.stereotype.Service

@Service
open class CartService(
    private val cartRepository: CartRepository,
    private val recipeRepository: RecipeRepository,
    private val cartItemRepository: CartItemRepository,
) {

    open fun findCartById(id: Long): CartResponseDTO {
        val cart = cartRepository.findById(id).orElseThrow {
            NotFoundException("Cart not found. Try creating a cart by adding one product to it!")
        }
        return cart.toResponseDTO()
    }

    @Transactional
    open fun addRecipeToCart(cartId: Long, recipeId: Long): CartResponseDTO {
        var cart = cartRepository.findById(cartId).orElse(null)
        if (cart == null) {
            cart = cartRepository.save(Cart())
        }

        val recipe = recipeRepository.findById(recipeId).orElseThrow { NotFoundException("Recipe not found") }

        recipe.products.forEach { product ->
            val existingItem = cartItemRepository.findByCartIdAndProductId(cart.id, product.id)
            if (existingItem == null) {
                cart.cartItems.add(CartItem(cart = cart, product = product))
                cart.totalInCents += product.priceInCents
            }
        }

        cartRepository.save(cart)
        return cart.toResponseDTO()
    }

    @Transactional
    open fun removeRecipeFromCart(cartId: Long, recipeId: Long): CartResponseDTO {
        val cart = cartRepository.findById(cartId).orElseThrow { NotFoundException("Cart not found") }
        val recipe = recipeRepository.findById(recipeId).orElseThrow { NotFoundException("Recipe not found") }

        recipe.products.forEach { product ->
            val cartItem = cartItemRepository.findByCartIdAndProductId(cart.id, product.id)
            if (cartItem != null) {
                cart.cartItems.remove(cartItem)
                cart.totalInCents -= product.priceInCents
                cartItemRepository.delete(cartItem)
            }
        }

        cartRepository.save(cart)
        return cart.toResponseDTO()
    }
}