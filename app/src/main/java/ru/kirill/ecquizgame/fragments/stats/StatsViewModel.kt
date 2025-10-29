package ru.kirill.ecquizgame.fragments.stats

import ru.kirill.ecquizgame.di.ClearViewModel
import ru.kirill.ecquizgame.fragments.MyViewModel

class StatsViewModel(private val clearViewModel: ClearViewModel, private val repository: StatsRepository) : MyViewModel {
//    fun statsUiState() = StatsUiState.Base(1,2)
    fun statsUiState() : StatsUiState {
        return StatsUiState.Base(repository.stats().first, repository.stats().second)
    }

    fun reset() {
        repository.reset()
    }

    fun clear() {
        clearViewModel.clear(StatsViewModel::class.java)
    }

    fun init(isFirstRun: Boolean): StatsUiState {
        if (isFirstRun) {
            val stats = repository.stats()
            repository.reset()
            return StatsUiState.Base(stats.first, stats.second)
        } else {
            return StatsUiState.Empty
        }
    }
}

