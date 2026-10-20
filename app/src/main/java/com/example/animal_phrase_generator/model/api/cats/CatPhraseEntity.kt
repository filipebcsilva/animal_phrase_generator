package com.example.animal_phrase_generator.model.api.cats

import com.google.gson.annotations.SerializedName

data class CatPhraseEntity(
    @SerializedName("fact") val fact: String
)