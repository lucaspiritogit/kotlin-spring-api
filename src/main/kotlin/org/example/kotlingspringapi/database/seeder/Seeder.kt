package org.example.kotlingspringapi.database.seeder

import org.example.kotlingspringapi.entity.Product
import org.example.kotlingspringapi.entity.Recipe
import org.example.kotlingspringapi.repository.cart.CartRepository
import org.example.kotlingspringapi.repository.product.ProductRepository
import org.example.kotlingspringapi.repository.recipe.RecipeRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Seeder(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (recipeRepository.count() == 0L) {
            val mozzarellaCheese = Product(name = "Mozzarella Cheese", priceInCents = 100)
            val tomatoSauce = Product(name = "Tomato Sauce", priceInCents = 200)

            val salmon = Product(name = "Salmon", priceInCents = 500)
            val sushiRice = Product( name = "Sushi Rice",priceInCents = 50)
            val avocado = Product(name = "Avocado", priceInCents = 100)
            val seaweed = Product( name = "Seaweed", priceInCents = 100)

            productRepository.saveAll(listOf(mozzarellaCheese, tomatoSauce, salmon, sushiRice, avocado, seaweed))


            val pizza = Recipe(
                name = "Pizza",
                mutableSetOf(mozzarellaCheese, tomatoSauce)
            )
            val sushiRoll = Recipe(
                name = "Sushi Roll",
                mutableSetOf(salmon, sushiRice, avocado, seaweed)
                )

            recipeRepository.saveAll(listOf(pizza, sushiRoll))
        }
    }
}