package ru.kirill.ecquizgame.gragments.game

import ru.kirill.ecquizgame.customview.game.CorrectAndUserChoiceIndexes
import ru.kirill.ecquizgame.IntCashe
import ru.kirill.ecquizgame.customview.game.QuestionChoices

interface GameRepository {
    fun questionAndChoices(): QuestionChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
    fun isLastQuestion(): Boolean

    fun resetIndex()


    class Base(
        private val index: IntCashe,
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
        }

        override fun isLastQuestion(): Boolean {
            return index.read(0) == questionChoices.size
        }

        override fun resetIndex() {
            index.save(0)
        }
    }


}