package com.example.foodscanner.data

// All in 100g
data class Nutriments(
    private val energyKcals: Double?,
    private val fat: Double?,
    private val saturatedFat: Double?,
    private val carbohydrates: Double?,
    private val sugars: Double?,
    private val proteins: Double?,
    private val salt: Double?,
    private val fiber: Double?,
) {
    fun getEnergyKcals() : Double? {
        return energyKcals
    }
    fun getFat(): Double? {
        return fat
    }
    fun getSaturatedFat(): Double? {
        return saturatedFat
    }
    fun getCarbohydrates(): Double? {
        return carbohydrates
    }
    fun getSugars(): Double? {
        return sugars
    }
    fun getProteins(): Double? {
        return proteins
    }
    fun getSalt(): Double? {
        return salt
    }
    fun getFiber(): Double? {
        return fiber
    }
}