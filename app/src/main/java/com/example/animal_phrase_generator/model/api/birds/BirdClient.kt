package com.example.animal_phrase_generator.model.api.birds

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BirdClient {

    companion object{

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://api.jsongpt.com/"

        private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun createBirdPhraseService(): BirdPhraseService {
            return getClientInstance().create(BirdPhraseService::class.java)
        }

    }
}