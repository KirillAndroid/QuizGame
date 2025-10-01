package ru.kirill.ecquizgame.gragments.stats

import ru.kirill.ecquizgame.customview.stats.StatsTextViewState

class StatsViewModel(private val repository: StatsRepository) {
//    fun statsUiState() = StatsUiState.Base(1,2)
    fun statsUiState() : StatsUiState {
        return StatsUiState.Base(repository.stats().first, repository.stats().second)
    }
}

