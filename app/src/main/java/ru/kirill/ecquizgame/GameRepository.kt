package ru.kirill.ecquizgame

interface GameRepository {
    fun questionAndChoices(): QuestionChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
}
