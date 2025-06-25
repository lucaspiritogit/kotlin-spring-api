package org.example.kotlingspringapi.service

import org.example.kotlingspringapi.dto.RecipeResponseDTO
import org.example.kotlingspringapi.dto.toResponseDTO
import org.springframework.stereotype.Service

import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

@Service
class RecipeService(private val recipeRepository: RecipeRepository) {

    fun getAllRecipes(): List<RecipeResponseDTO> {
        return recipeRepository.findAll().map { it.toResponseDTO() }
    }

}
