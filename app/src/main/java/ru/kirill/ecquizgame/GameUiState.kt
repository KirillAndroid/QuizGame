package ru.kirill.ecquizgame

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt
import java.io.Serializable

interface GameUiState : Serializable{

    abstract class Abstract(val questionText: String,
                            val choiceUiStates : List<ChoiceUiState>,
                            val checkVisibility: Int,
                            val nextVisibility: Int) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            with(binding) {
                questionTextview.text = questionText
                choiceUiStates[0].update(firstChoiceButton)
                choiceUiStates[1].update(secondChoiceButton)
                choiceUiStates[2].update(thirdChoiceButton)
                choiceUiStates[3].update(forthChoiceButton)
                checkButton.visibility = checkVisibility
                nextButton.visibility = nextVisibility
            }

        }
    }
    fun update(binding: ActivityMainBinding)

    data class AskedQuestion(val question: String, val choices: List<String>) : Abstract(
        questionText = question,
        choiceUiStates = choices.map { ChoiceUiState.AvailableToChoose(text = it) },
        checkVisibility = View.INVISIBLE,
        nextVisibility = View.INVISIBLE
    ) {


    }

    data class ChoiceMade(val question: String, val choices: List<ChoiceUiState>) : Abstract(
        questionText = question,
        choiceUiStates = choices,
        checkVisibility = View.VISIBLE,
        nextVisibility = View.INVISIBLE
    ) {

    }

    data class AnswerChecked(val question: String, val choices: List<ChoiceUiState>) : Abstract(
        questionText = question,
        choiceUiStates = choices,
        checkVisibility = View.INVISIBLE,
        nextVisibility = View.VISIBLE
    ) {

    }


}

interface ChoiceUiState : Serializable {
    fun update(appButton: AppCompatButton)

    abstract class Abstract(
        private val text: String,
        private val color: String,
        private val clickable: Boolean,
        private val isEnabled: Boolean
    ) : ChoiceUiState {
        override fun update(button: AppCompatButton) {
            button.text = text
            button.setBackgroundColor(Color.parseColor(color))
            button.isClickable = clickable
            button.isEnabled = isEnabled
        }
    }

    data class NotAvailableToChoose(val text: String) : Abstract(text, "#888888", false, false) {

    }

    data class AvailableToChoose(val text: String) : Abstract(text, "#7223D3", true, true) {

    }

    data class Correct(val text: String) : Abstract(text, "#62B857", false, true) {

    }

    data class InCorrect(val text: String) : Abstract(text, "#CE1F1F", false, true) {

    }

}
