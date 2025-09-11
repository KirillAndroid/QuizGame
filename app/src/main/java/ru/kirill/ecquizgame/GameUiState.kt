package ru.kirill.ecquizgame

import android.view.View
import ru.kirill.ecquizgame.databinding.ActivityMainBinding

interface GameUiState {
    data class AskedQuestion(val question: String, val choices: List<String>) : GameUiState {


    }

    data class ChoiceMade(val question: String, val choices: List<ChoiceUiState>) : GameUiState {

    }

    data class AnswerChecked(val question: String, val choices: List<ChoiceUiState>) : GameUiState {

    }

    fun update(binding: ActivityMainBinding) : Unit = throw IllegalStateException("") //todo

}

interface ChoiceUiState {
    data class NotAvailableToChoose(val text: String) : ChoiceUiState {

    }

    data class AvailableToChoose(val text: String) : ChoiceUiState {

    }

    data class Correct(val text: String) : ChoiceUiState {

    }

    data class InCorrect(val text: String) : ChoiceUiState {

    }

}
