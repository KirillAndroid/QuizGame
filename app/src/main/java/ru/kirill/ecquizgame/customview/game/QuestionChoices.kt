package ru.kirill.ecquizgame.customview.game

data class QuestionChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
) {

}