package ru.kirill.ecquizgame.fragments.stats

import org.junit.Test
import org.junit.Assert.*;

class StatsViewModelTest {


    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = StatsViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.statsUiState())

        viewModel.reset()
        assertEquals(true, repository.resetCalled())
    }
}

class FakeRepository : StatsRepository {

    override fun stats() : Pair<Int, Int> {
        return Pair(2, 3)
    }

    val resetCalled = mutableListOf<Boolean>()
    override fun reset() {
        resetCalled.add(true)
        //nothing to do here
    }

    fun resetCalled() : Boolean {
        return resetCalled.last()
    }
}
