package ru.kirill.ecquizgame.customview.stats

import java.io.Serializable

data class StatsTextViewState(val correct: Int, val incorrect: Int) : Serializable {
    fun update(view: StatsTextView) {
        view.update(correct, incorrect)
    }
}