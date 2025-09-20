package ru.kirill.ecquizgame

import android.app.Application

class QuizGameApp : Application(){
    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizGameDate", MODE_PRIVATE)
        viewModel = GameViewModel(GameRepository.Base(
            IntCashe.Base(sharedPreferences,"index"),
            IntCashe.Base(sharedPreferences, "userChoiceIndex")))
    }
}