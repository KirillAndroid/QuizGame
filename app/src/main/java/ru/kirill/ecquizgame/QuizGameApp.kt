package ru.kirill.ecquizgame

import android.app.Application
import ru.kirill.ecquizgame.fragments.game.GameRepository
import ru.kirill.ecquizgame.fragments.game.GameViewModel
import ru.kirill.ecquizgame.fragments.stats.StatsRepository
import ru.kirill.ecquizgame.fragments.stats.StatsViewModel

class QuizGameApp : Application(){
    lateinit var viewModel: GameViewModel
    lateinit var statsViewModel: StatsViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizGameDate", MODE_PRIVATE)
        val correct = IntCashe.Base(sharedPreferences, "correct")
        val incorrect = IntCashe.Base(sharedPreferences, "incorrect")
        viewModel = GameViewModel(
            GameRepository.Base(
                index = IntCashe.Base(sharedPreferences, "index"),
                userChoiceIndex = IntCashe.Base(sharedPreferences, "userChoiceIndex"),
                correct = correct,
                incorrect = incorrect
            )
        )

        statsViewModel = StatsViewModel(StatsRepository.Base(
            correct = correct,
            incorrect = incorrect
        ))
    }
}

