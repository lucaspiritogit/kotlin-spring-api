package org.example.kotlingspringapi.controller

import org.example.kotlingspringapi.dto.CartResponseDTO
import org.example.kotlingspringapi.service.CartService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController(val cartService: CartService) {

    @GetMapping("/carts/{id}")
    fun findCartById(@PathVariable id: Long): CartResponseDTO {
        return this.cartService.findCartById(id)
    }

    @PostMapping("/carts/{cartId}/add_recipe/{recipeId}")
    fun addRecipeToCart(@PathVariable cartId: Long, @PathVariable recipeId: Long): CartResponseDTO {
        return cartService.addRecipeToCart(cartId, recipeId)
    }

    @DeleteMapping("/carts/{cartId}/recipes/{recipeId}")
    fun deleteRecipeFromCart(
        @PathVariable cartId: Long,
        @PathVariable recipeId: Long
    ): CartResponseDTO {
        return cartService.removeRecipeFromCart(cartId, recipeId)
    }
}