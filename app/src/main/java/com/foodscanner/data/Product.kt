package com.foodscanner.data

data class Product(
    private val name: String?,
    private val brands: String?,
    private val allergens: List<String>?,
    private val labels: List<String>?,
    private val categories: List<String>?,
    private val nutriScore: String?,
    private val nutriments: Nutriments?,
    private val ingredients: List<Ingredient>?,
    private val imageUrl: String?,
    private val code: String?,
    private val date: String?,
) {

    fun getName(): String? {
        return name
    }
    fun getBrands(): String? {
        return brands
    }
    fun getAllergens(): List<String>? {
        return allergens
    }
    fun getLabels(): List<String>? {
        return labels
    }
    fun getCategories(): List<String>? {
        return categories
    }
    fun getNutriScore(): String? {
        return nutriScore
    }
    fun getNutriments(): Nutriments? {
        return nutriments
    }
    fun getIngredients(): List<Ingredient>? {
        return ingredients
    }
    fun getImageUrl(): String? {
        return imageUrl
    }
    fun getCode(): String? {
        return code
    }
    fun getDate(): String? {
        return date
    }
}