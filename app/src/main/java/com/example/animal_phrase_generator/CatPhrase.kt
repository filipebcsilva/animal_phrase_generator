package com.example.animal_phrase_generator

import kotlin.random.Random

object CatPhrase {

    private val phrases = mutableListOf(
        "O olhar penetrante de um gato diz milhões de palavras, sem nada dizer.",
        "Um gato transforma qualquer casa em um lar.",
        "O ronronar de um gato é a música mais doce que um amante dos felinos pode ouvir.",
        "Gatos são pequenas obras de arte vivas."
    )

    fun getRandomPhrase(): String = phrases.random()

    fun deletePhrase(phrase: String) = phrases.remove(phrase)

}