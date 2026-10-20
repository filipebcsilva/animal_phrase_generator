package com.example.animal_phrase_generator.model.api.birds

import com.google.gson.annotations.SerializedName

data class BirdPhraseEntity(
    @SerializedName("facts") val facts: List<String>
)
