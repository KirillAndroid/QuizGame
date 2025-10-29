package ru.kirill.ecquizgame.fragments.game.di

import ru.kirill.ecquizgame.di.Core
import ru.kirill.ecquizgame.IntCashe
import ru.kirill.ecquizgame.di.Module
import ru.kirill.ecquizgame.di.ProvideViewModel
import ru.kirill.ecquizgame.di.AbstractChain
import ru.kirill.ecquizgame.fragments.game.GameRepository
import ru.kirill.ecquizgame.fragments.game.GameViewModel

class GameModule(private val core: Core) : Module<GameViewModel> {
    override fun viewModel(): GameViewModel {
        val correct = IntCashe.Base(core.sharedPreferences, "correct")
        val incorrect = IntCashe.Base(core.sharedPreferences, "incorrect")
        return GameViewModel(
            core.clear,
            repository = GameRepository.Base(
                index = IntCashe.Base(core.sharedPreferences, "index"),
                userChoiceIndex = IntCashe.Base(core.sharedPreferences, "userChoiceIndex"),
                correct = correct,
                incorrect = incorrect
            ))
    }

}

class ProvideGameViewModel(private val core: Core, nextChain: ProvideViewModel) : AbstractChain(core, nextChain, GameViewModel::class.java) {
    override fun module(): Module<*> = GameModule(core)

}