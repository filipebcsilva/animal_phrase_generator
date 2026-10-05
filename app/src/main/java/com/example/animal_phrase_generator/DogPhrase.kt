package com.example.animal_phrase_generator

object DogPhrase {

    private val phrases = mutableListOf(
        "Um cachorro faz um dia comum se tornar extraordinário.",
        "Não há nenhum psiquiatra no mundo como um cachorro lambendo o seu rosto.",
        "Todos os homens são deuses para seu cão. Por isso, existem mais pessoas que amam mais os seus cães que os homens.",
        "A vida dos cães é muito curta. Sua única falha, realmente."
    )

    fun getRandomPhrase(): String = phrases.random()

    fun deletePhrase(phrase: String) = phrases.remove(phrase)
}