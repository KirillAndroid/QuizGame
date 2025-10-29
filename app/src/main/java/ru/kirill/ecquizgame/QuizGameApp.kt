package ru.kirill.ecquizgame

import android.app.Application
import ru.kirill.ecquizgame.di.ClearViewModel
import ru.kirill.ecquizgame.di.Core
import ru.kirill.ecquizgame.di.ManageViewModel
import ru.kirill.ecquizgame.di.ProvideViewModel
import ru.kirill.ecquizgame.fragments.MyViewModel
import ru.kirill.ecquizgame.fragments.game.GameViewModel
import ru.kirill.ecquizgame.fragments.stats.StatsViewModel

class QuizGameApp : Application(), ProvideViewModel {
    lateinit var factory: ManageViewModel

    override fun onCreate() {
        super.onCreate()
      val core = Core(
          context = this,
          clear = object : ClearViewModel {
              override fun clear(claszz: Class<out MyViewModel>) {
                  factory.clear(claszz)
              }
          }
      )
        val make = ProvideViewModel.Make(
            core
        )
        factory = ManageViewModel.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T = factory.makeViewModel(claszz)
}
