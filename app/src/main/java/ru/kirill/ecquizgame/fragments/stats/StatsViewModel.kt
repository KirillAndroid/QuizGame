package ru.kirill.ecquizgame.fragments.stats

class StatsViewModel(private val repository: StatsRepository) {
//    fun statsUiState() = StatsUiState.Base(1,2)
    fun statsUiState() : StatsUiState {
        return StatsUiState.Base(repository.stats().first, repository.stats().second)
    }

    fun reset() {
        repository.reset()
    }
}

