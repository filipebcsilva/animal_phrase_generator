package com.example.animal_phrase_generator.model.api.dogs

import com.google.gson.annotations.SerializedName

data class DogPhraseEntity(
    @SerializedName("facts") val fact: List<String>
)
