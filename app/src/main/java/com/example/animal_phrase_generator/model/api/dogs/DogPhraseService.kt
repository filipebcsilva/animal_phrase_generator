package com.example.animal_phrase_generator.model.api.dogs

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DogPhraseService {

    @GET("api/facts")
    fun getDogFact(@Query("number") number: Int = 1): Call<DogPhraseEntity>
}