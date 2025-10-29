package ru.kirill.ecquizgame.di

import ru.kirill.ecquizgame.fragments.MyViewModel
import ru.kirill.ecquizgame.fragments.game.GameViewModel
import ru.kirill.ecquizgame.fragments.game.di.GameModule
import ru.kirill.ecquizgame.fragments.game.di.ProvideGameViewModel
import ru.kirill.ecquizgame.fragments.stats.StatsViewModel
import ru.kirill.ecquizgame.fragments.stats.di.ProvideStatsViewModel
import ru.kirill.ecquizgame.fragments.stats.di.StatsModule

interface ProvideViewModel {
    fun <T : MyViewModel> makeViewModel(claszz: Class<T>) : T


    class Make(private val core: Core) : ProvideViewModel{
        private lateinit var chain: ProvideViewModel

        init {
            chain = ProvideViewModel.Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideStatsViewModel(core, chain)
        }
        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            return chain.makeViewModel(claszz)
        }
    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            throw IllegalStateException("no chain found")
        }
    }
}