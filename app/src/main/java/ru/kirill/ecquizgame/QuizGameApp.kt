package ru.kirill.ecquizgame

import android.app.Application

class QuizGameApp : Application(){
    private lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository.Base())
    }
}