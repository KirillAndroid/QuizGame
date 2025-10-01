package ru.kirill.ecquizgame.gragments.stats

import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.customview.stats.StatsTextView
import java.io.Serializable

interface StatsUiState : Serializable {
    fun update(statsTextViewModel: StatsTextView, newGameButton: AppCompatButton)

    data class Base(private val correct: Int, private val incorrect: Int) : StatsUiState {

        override fun update(statsTextViewModel: StatsTextView, newGameButton: AppCompatButton) {
            statsTextViewModel.update(correct, incorrect)
            newGameButton.isEnabled = true
        }
    }
}