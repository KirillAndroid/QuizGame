package ru.kirill.ecquizgame

data class QuestionChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
) {

}
