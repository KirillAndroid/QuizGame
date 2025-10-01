package ru.kirill.ecquizgame.gragments.stats

import ru.kirill.ecquizgame.IntCashe

interface StatsRepository {
    fun stats() : Pair<Int, Int>

    class Base(private val correct: IntCashe, private val incorrect: IntCashe) : StatsRepository {
        override fun stats(): Pair<Int, Int> {
            return Pair(correct.read(0), incorrect.read(0))
        }

    }
}