package com.foodscanner.data

import kotlin.math.roundToInt

object HealthScoreCalculator {

    fun calculate(product: Product): Int {
        var totalScore = 0.0
        var totalWeight = 0.0

        product.getNutriments()?.let { n ->
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

            totalScore += nutrientPoints.coerceIn(0.0, 100.0) * 4.0
            totalWeight += 4.0
        }

        product.getNutriScore()?.lowercase()?.trim()?.let { ns ->
            val nsScore = when (ns) {
                "a" -> 100.0
                "b" -> 80.0
                "c" -> 55.0
                "d" -> 30.0
                "e" -> 5.0
                else -> null
            }

            nsScore?.let {
                totalScore += it * 4.0
                totalWeight += 4.0
            }
        }

        product.getNovaGroup()?.let { nova ->
            val novaScore = when (nova) {
                1 -> 100.0
                2 -> 80.0
                3 -> 50.0
                4 -> 15.0
                else -> null
            }

            novaScore?.let {
                totalScore += it * 1.0
                totalWeight += 1.0
            }
        }

        if (totalWeight == 0.0) {
            return 50
        }

        return (totalScore / totalWeight)
            .roundToInt()
            .coerceIn(0, 100)
    }
}