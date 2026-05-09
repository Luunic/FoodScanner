package com.foodscanner.service


import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL =
    "https://world.openfoodfacts.org"




private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()



interface OpenFoodFactsApiService {
    @GET("api/v2/product/{barcode}")
    suspend fun getData(
        @Path("barcode") barcode: String,
        @Query("fields") fields: String = "product_name,brands,image_url,ingredients,ingredients_text,allergens,labels_tags,nutriments,nutriscore_grade,categories_tags,code"
    ): String
}


object OpenFoodFactsApi{
    val retrofitService : OpenFoodFactsApiService by lazy {
        retrofit.create(OpenFoodFactsApiService::class.java)
    }
    // Singleton
}