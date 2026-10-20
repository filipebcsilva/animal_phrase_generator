package com.example.animal_phrase_generator.model.api.dogs

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogClient {

    companion object{

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://dog-api.kinduff.com/"

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

        fun createDogPhraseService(): DogPhraseService {
            return getClientInstance().create(DogPhraseService::class.java)
        }

    }
}