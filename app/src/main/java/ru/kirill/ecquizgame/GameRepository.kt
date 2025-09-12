package ru.kirill.ecquizgame

interface GameRepository {
    fun questionAndChoices(): QuestionChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

    class Base(
        private val questionChoices: List<QuestionChoices> = listOf<QuestionChoices>(
            QuestionChoices(
                question = "What is the capital of France?",
                choices = listOf("Paris", "Madrid", "Berlin", "Rome"),
                correctIndex = 0
            ),
            QuestionChoices(
                question = "What is the capital of Germany?",
                choices = listOf("Berlin", "Madrid", "Paris", "Rome"),
                correctIndex = 0
            )
        )
    ) : GameRepository {



        private var index: Int = 0
        private var userChoiceIndex = -1

        override fun questionAndChoices() : QuestionChoices {
            return questionChoices[index]
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex = index
        }

        override fun check() : CorrectAndUserChoiceIndexes {
            val correctIndex : Int = questionAndChoices().correctIndex
            val userChoiceIndex: Int = userChoiceIndex
            return CorrectAndUserChoiceIndexes(
                correctIndex = correctIndex,
                userChoiceIndex = userChoiceIndex
            )
        }

        override fun next() {
            userChoiceIndex = -1
            index++
            if (index == questionChoices.size) index = 0
        }

    }
}


