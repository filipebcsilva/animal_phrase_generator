package com.example.animal_phrase_generator.model.api.cats
import retrofit2.Call
import retrofit2.http.GET

interface CatPhraseService {
    @GET("fact")
    fun getCatFact(): Call<CatPhraseEntity>
}