package com.example.foodscanner

import com.foodscanner.service.ProductParser
import org.junit.Assert.assertEquals
import org.junit.Test
class test_ProductParser {
    @Test
    fun testParseAllergens() {
        val allergensString = "en:soybeans,en:milk"
        val list = listOf("Soja", "Milch")

        val response = ProductParser.parseAllergens(allergensString)

        assertEquals(list, response)

    }

    @Test
    fun testParseAllergensReturnsEmptyList() {
        val allergensString = null

        val response = ProductParser.parseAllergens(allergensString)

        assertEquals(emptyList<String>(), response)
    }

    @Test
    fun testParseLabels() {
        val labelsList = listOf(
            "en:sustainable",
            "en:vegetarian",
            "en:sustainable-palm-oil",
            "en:vegan",
            "en:european-vegetarian-union",
            "en:no-colorings",
            "en:no-flavour-enhancer",
            "en:rainforest-alliance",
            "en:roundtable-on-sustainable-palm-oil")

        val list = listOf("Vegetarisch", "Vegan", "Rainforest Alliance")

        val response = ProductParser.parseLabels(labelsList)

        assertEquals(list, response)
    }

    @Test
    fun testParseLabelsReturnsEmptyList() {
        val labelsList = null

        val response = ProductParser.parseLabels(labelsList)

        assertEquals(emptyList<String>(), response)
    }

    @Test
    fun testParseCategories() {
        val categoriesList = listOf(
            "en:biscuits-and-crackers",
            "en:imbiss",
            "en:kakao-und-kakaoprodukte",
            "en:kekse",
            "en:kekse-und-kuchen"
        )
        val list = listOf(
            "biscuits-and-crackers",
            "imbiss",
            "kakao-und-kakaoprodukte",
            "kekse",
            "kekse-und-kuchen"
        )

        val response = ProductParser.parseCategories(categoriesList)

        assertEquals(list, response)
    }

    @Test
    fun testParseCategoriesReturnsEmptyList() {
        val categoriesList = null

        val response = ProductParser.parseCategories(categoriesList)

        assertEquals(emptyList<String>(), response)
    }
}