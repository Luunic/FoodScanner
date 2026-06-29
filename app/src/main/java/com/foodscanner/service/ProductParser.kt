package com.foodscanner.service

import com.foodscanner.data.Ingredient
import com.foodscanner.data.Nutriments
import com.foodscanner.data.Product
import kotlinx.serialization.json.*

// singelton
object ProductParser {

    fun parse(json: JsonElement?): Product {

        val jsonObject = json?.jsonObject
        val product = jsonObject?.get("product")?.jsonObject

        val allergensString = product?.get("allergens")?.jsonPrimitive?.content
        val allergens = parseAllergens(allergensString)

        val labelsList = product?.get("labels_tags")?.jsonArray?.map { it.jsonPrimitive.content }
        val labels = parseLabels(labelsList)

        val categoriesList = product?.get("categories_tags")?.jsonArray?.map { it.jsonPrimitive.content }
        val categories = parseCategories(categoriesList)

        val nutrimentsJson = product?.get("nutriments")?.jsonObject
        val nutriments = parseNutriments(nutrimentsJson)

        val ingredientsList = product?.get("ingredients")?.jsonArray
        val ingredients = getIngredients(ingredientsList)

        return Product(
            name = product?.get("product_name")?.jsonPrimitive?.contentOrNull,
            brands = product?.get("brands")?.jsonPrimitive?.contentOrNull,
            allergens = allergens,
            labels = labels,
            categories = categories, // Could need a rework
            nutriScore = product?.get("nutriscore_grade")?.jsonPrimitive?.contentOrNull,
            nutriments = nutriments,
            ingredients = ingredients,
            imageUrl = product?.get("image_url")?.jsonPrimitive?.contentOrNull,
            code = product?.get("code")?.jsonPrimitive?.contentOrNull,
            date = jsonObject?.get("date")?.jsonPrimitive?.contentOrNull,
            novaGroup = product?.get("nova_group")?.jsonPrimitive?.intOrNull
        )
    }


    // need to refactor / redo this func
    fun parseAllergens(allergenString: String?): List<String> {
        if (allergenString.isNullOrBlank()) {
            return emptyList()
        }

        val validKeys = allergenTranslations["de"]?.keys ?: emptySet()
        return allergenString
            .split(",")
            .map { it.trim() }
            .filter { validKeys.contains(it) }

    }

    // Only works if you use "labels_tags" as an field in the api call
    fun parseLabels(labelsList: List<String>?): List<String> {
        if (labelsList.isNullOrEmpty()) {
            return emptyList()
        }
        val validKeys = labelTranslations["de"]?.keys ?: emptySet()

        return labelsList
            .map { it.trim() }
            .filter { validKeys.contains(it) }
    }

    // Only works if you use "categories_tags"
    fun parseCategories(categoriesList:List<String>?): List<String> {
        if (categoriesList.isNullOrEmpty()) {
            return emptyList()
        }
        val secondList = mutableListOf<String>()
        for (element in categoriesList) {
            secondList.add(element.split(":").last().trim())
        }
        return secondList
    }

    fun parseNutriments(nutrimentsJson: JsonObject?): Nutriments {
        return Nutriments(
            energyKcals = nutrimentsJson?.get("energy-kcal_100g")?.jsonPrimitive?.doubleOrNull,
            fat = nutrimentsJson?.get("fat_100g")?.jsonPrimitive?.doubleOrNull,
            saturatedFat = nutrimentsJson?.get("saturated-fat_100g")?.jsonPrimitive?.doubleOrNull,
            carbohydrates = nutrimentsJson?.get("carbohydrates_100g")?.jsonPrimitive?.doubleOrNull,
            sugars = nutrimentsJson?.get("sugars_100g")?.jsonPrimitive?.doubleOrNull,
            proteins = nutrimentsJson?.get("proteins_100g")?.jsonPrimitive?.doubleOrNull,
            salt = nutrimentsJson?.get("salt_100g")?.jsonPrimitive?.doubleOrNull,
            fiber = nutrimentsJson?.get("fiber_100g")?.jsonPrimitive?.doubleOrNull
        )
    }

    fun mapIngredients(ingredientJson: JsonObject?): Ingredient {
        return Ingredient(
            name = ingredientJson?.get("text")?.jsonPrimitive?.contentOrNull,
            percent = ingredientJson?.get("percent_estimate")?.jsonPrimitive?.doubleOrNull,
            vegan = ingredientJson?.get("vegan")?.jsonPrimitive?.contentOrNull,
            vegetarian = ingredientJson?.get("vegetarian")?.jsonPrimitive?.contentOrNull,
            subIngredients = getIngredients(ingredientJson?.get("ingredients")?.jsonArray)
        )
    }

    fun getIngredients(ingredientsJson: JsonArray?): List<Ingredient> {
        if (ingredientsJson == null) {
            return emptyList()
        }
        val list = mutableListOf<Ingredient>()

        for(element in ingredientsJson) {
            // 1.0 is the minimum percentage that the ingredient needs to have
            element.jsonObject.get("percent_estimate")?.jsonPrimitive?.doubleOrNull?.let { if(it > 1.0) {
                list.add(mapIngredients(element.jsonObject))
            } }
        }
        return list
    }
}