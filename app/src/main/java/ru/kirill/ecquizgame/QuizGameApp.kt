package ru.kirill.ecquizgame

import android.app.Application
import ru.kirill.ecquizgame.gragments.game.GameRepository
import ru.kirill.ecquizgame.gragments.game.GameViewModel
import ru.kirill.ecquizgame.gragments.stats.StatsViewModel

class QuizGameApp : Application(){
    lateinit var viewModel: GameViewModel
    lateinit var statsViewModel: StatsViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizGameDate", MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                index = IntCashe.Base(sharedPreferences, "index"),
                userChoiceIndex = IntCashe.Base(sharedPreferences, "userChoiceIndex")
            )
        )
//        statsViewModel = GameViewModel(GameRepository.Base(
//            correct = IntCashe.Base(sharedPreferences, "correct"),
//            incorrect = IntCashe.Base(sharedPreferences, "incorrect"),
//            index = IntCashe.Base(sharedPreferences,"index"),
//            userChoiceIndex = IntCashe.Base(sharedPreferences, "userChoiceIndex")))
//        statsViewModel = StatsViewModel()
    }
}

