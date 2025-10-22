package ru.kirill.ecquizgame.fragments.stats

import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.customview.stats.StatsTextView
import ru.kirill.ecquizgame.customview.stats.StatsTextViewState
import java.io.Serializable

interface StatsUiState : Serializable {
    fun update(statsTextViewModel: StatsTextView, newGameButton: AppCompatButton)

    data class Base(private val correct: Int, private val incorrect: Int) : StatsUiState {

        override fun update(statsTextViewModel: StatsTextView, newGameButton: AppCompatButton) {
            statsTextViewModel.update(StatsTextViewState(correct, incorrect))
            newGameButton.isEnabled = true
        }
    }
}