package ru.kirill.ecquizgame

import android.content.SharedPreferences

interface GameRepository {
    fun questionAndChoices(): QuestionChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

    fun correct() : Int

    fun incorrect() : Int

    fun saveCorrect()

    fun saveIncorrect()

    class Base(
        private val index: IntCashe,
        private val correct: IntCashe,
        private val incorrect: IntCashe,
        private val userChoiceIndex: IntCashe,
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



//        private var index = IntCashe.Base(sharedPreferences, "index")
//        private var userChoiceIndex = IntCashe.Base(sharedPreferences, "userChoiceIndex")

        override fun questionAndChoices() : QuestionChoices {
            return questionChoices[index.read(0)]
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex.save(index)
        }

        override fun check() : CorrectAndUserChoiceIndexes {
            val correctIndex : Int = questionAndChoices().correctIndex
            val userChoiceIndex: Int = userChoiceIndex.read(-1)
            return CorrectAndUserChoiceIndexes(
                correctIndex = correctIndex,
                userChoiceIndex = userChoiceIndex
            )
        }

        override fun next() {
            userChoiceIndex.save(-1)
            index.save(index.read(0) + 1)
            if (index.read(0) == questionChoices.size) index.save(0)
        }

        override fun correct(): Int {
            return correct.read(0)
        }

        override fun incorrect(): Int {
            return incorrect.read(0)
        }

        override fun saveCorrect() {
            correct.save(correct.read(0) + 1)
        }

        override fun saveIncorrect() {
            incorrect.save(incorrect.read(0) + 1)
        }

    }
}


