package ru.kirill.ecquizgame.fragments.stats

import org.junit.Test
import org.junit.Assert.*;

class StatsViewModelTest {


    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = StatsViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.init(isFirstRun = true))

        assertEquals(true, repository.resetCalled())

        assertEquals(StatsUiState.Empty, viewModel.init(isFirstRun = false))
        assertEquals(true, repository.resetCalled())
    }
}

class FakeRepository : StatsRepository {

    override fun stats() : Pair<Int, Int> {
        return Pair(2, 3)
    }

    var resetCalled = 0
    override fun reset() {
        resetCalled++
        //nothing to do here
    }

    fun resetCalled() : Boolean {
        return resetCalled != 0
    }
}
