package com.example.animal_phrase_generator.model.api.birds

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdPhraseService {
    @GET("json")
    fun getBirdPhrase(@Query("prompt") prompt: String = "Generate 1 bird fact",
                      @Query("facts") facts: String = "array of bird facts") : Call<BirdPhraseEntity>
}