package com.foodscanner.data

import kotlin.math.roundToInt

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
    private val novaGroup: Int?,
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
    fun getNovaGroup(): Int? {
        return novaGroup
    }

    fun calculateHealthScore(): Int {
        val scoresToAverage = mutableListOf<Double>()

        nutriments?.let { n ->
            var nutrientPoints = 100.0

            val energy = n.getEnergyKcals() ?: 0.0
            val sugar = n.getSugars() ?: 0.0
            val satFat = n.getSaturatedFat() ?: 0.0
            val salt = n.getSalt() ?: 0.0

            nutrientPoints -= (energy.coerceIn(0.0, 600.0) / 600.0) * 15.0
            nutrientPoints -= (sugar.coerceIn(0.0, 45.0) / 45.0) * 30.0
            nutrientPoints -= (satFat.coerceIn(0.0, 20.0) / 20.0) * 20.0
            nutrientPoints -= (salt.coerceIn(0.0, 3.0) / 3.0) * 20.0

            val fiber = n.getFiber() ?: 0.0
            val protein = n.getProteins() ?: 0.0

            nutrientPoints += (fiber.coerceIn(0.0, 8.0) / 8.0) * 12.0
            nutrientPoints += (protein.coerceIn(0.0, 15.0) / 15.0) * 8.0

            scoresToAverage.add(
                nutrientPoints.coerceIn(0.0, 100.0)
            )
        }

        nutriScore?.lowercase()?.trim()?.let { ns ->
            val nsScore = when (ns) {
                "a" -> 100.0
                "b" -> 80.0
                "c" -> 55.0
                "d" -> 30.0
                "e" -> 5.0
                else -> null
            }

            nsScore?.let {
                scoresToAverage.add(it)
            }
        }

        getNovaGroup()?.let { nova ->
            val novaScore = when (nova) {
                1 -> 100.0
                2 -> 80.0
                3 -> 50.0
                4 -> 15.0
                else -> null
            }

            novaScore?.let {
                scoresToAverage.add(it)
            }
        }

        if (scoresToAverage.isEmpty()) {
            return 50
        }

        return scoresToAverage
            .average()
            .roundToInt()
            .coerceIn(0, 100)
    }

}