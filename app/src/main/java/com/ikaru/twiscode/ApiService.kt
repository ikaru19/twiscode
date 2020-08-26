package com.ikaru.twiscode

import com.ikaru.twiscode.models.Meal
import com.ikaru.twiscode.models.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/filter.php?c=Seafood")
    fun getCar(): Call<Meals>

    @GET("api/json/v1/1/lookup.php")
    fun getReviewByID(@Query("i") id : String): Call<Meals>;
}