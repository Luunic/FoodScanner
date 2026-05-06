package com.example.foodscanner.data

data class Ingredient(
    private val name: String?,
    private val percent: Double?,
    private val vegan: String?,
    private val vegetarian: String?,
    private val subIngredients: List<Ingredient> = emptyList()
) {
    fun getName(): String? {
        return name
    }
    fun getPercent(): Double? {
        return percent
    }
    fun getVegan(): String? {
        return vegan
    }
    fun getVegetarian(): String? {
        return vegetarian
    }
    fun getSubIngredients(): List<Ingredient> {
        return subIngredients
    }
}