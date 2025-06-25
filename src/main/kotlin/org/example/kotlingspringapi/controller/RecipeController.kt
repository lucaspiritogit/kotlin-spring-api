package org.example.kotlingspringapi.controller

import org.example.kotlingspringapi.dto.RecipeResponseDTO
import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.service.CartService
import org.example.kotlingspringapi.service.RecipeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController(val recipeService: RecipeService) {

    @GetMapping("/recipes")
    fun getAllRecipes(): List<RecipeResponseDTO> {
        return recipeService.getAllRecipes()
    }
}
