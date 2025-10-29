package ru.kirill.ecquizgame.fragments.stats.di

import ru.kirill.ecquizgame.di.Core
import ru.kirill.ecquizgame.IntCashe
import ru.kirill.ecquizgame.di.Module
import ru.kirill.ecquizgame.di.ProvideViewModel
import ru.kirill.ecquizgame.di.AbstractChain
import ru.kirill.ecquizgame.fragments.stats.StatsRepository
import ru.kirill.ecquizgame.fragments.stats.StatsViewModel

class StatsModule(private val core: Core) : Module<StatsViewModel> {
    override fun viewModel(): StatsViewModel {
        val correct = IntCashe.Base(core.sharedPreferences, "correct")
        val incorrect = IntCashe.Base(core.sharedPreferences, "incorrect")
        return StatsViewModel(
            core.clear,
            repository = StatsRepository.Base(
                correct = correct,
                incorrect = incorrect
            )
        )
    }

}

class ProvideStatsViewModel(private val core: Core, nextChain: ProvideViewModel) : AbstractChain(core, nextChain, StatsViewModel::class.java) {
    override fun module(): Module<*> = StatsModule(core)
}