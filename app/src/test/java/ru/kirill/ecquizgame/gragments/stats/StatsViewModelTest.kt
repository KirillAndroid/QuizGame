package ru.kirill.ecquizgame.gragments.stats

import org.junit.Test
import org.junit.Assert.*;
import ru.kirill.ecquizgame.gragments.game.GameRepository
import ru.kirill.ecquizgame.customview.stats.StatsTextViewState

class StatsViewModelTest {


    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = StatsViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.statsUiState())
    }
}

class FakeRepository : StatsRepository {

    override fun stats() : Pair<Int, Int> {
        return Pair(2, 3)
    }
}
