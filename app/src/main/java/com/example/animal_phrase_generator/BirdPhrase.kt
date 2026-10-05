package com.example.animal_phrase_generator

object BirdPhrase {
    private val phrases = mutableListOf(
        "Um pássaro que repousa em uma árvore nunca teme que o galho quebre, porque a sua confiança não é o galho, mas nas suas próprias asas.",
        "Os pássaros cantam mesmo depois da tempestade.",
        "Um pássaro não canta porque tem uma resposta, canta porque tem uma canção.",
        "A voz de uma passarinho me recita."
    )

    fun getRandomPhrase(): String = phrases.random()

    fun deletePhrase(phrase: String) = phrases.remove(phrase)
}