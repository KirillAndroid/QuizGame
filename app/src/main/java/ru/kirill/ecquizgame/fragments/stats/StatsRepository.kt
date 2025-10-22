package ru.kirill.ecquizgame.fragments.stats

import ru.kirill.ecquizgame.IntCashe

interface StatsRepository {
    fun stats() : Pair<Int, Int>
    fun reset()

    class Base(private val correct: IntCashe, private val incorrect: IntCashe) : StatsRepository {
        override fun stats(): Pair<Int, Int> {
            return Pair(correct.read(0), incorrect.read(0))
        }

        override fun reset() {
            correct.save(0)
            incorrect.save(0)
        }

    }
}