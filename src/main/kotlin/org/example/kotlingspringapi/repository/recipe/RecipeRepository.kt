package org.example.kotlingspringapi.repository.recipe

import org.example.kotlingspringapi.entity.Recipe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RecipeRepository : JpaRepository<Recipe, Long> {
    override fun findAll(): MutableList<Recipe>

    override fun findById(id: Long): Optional<Recipe>

}