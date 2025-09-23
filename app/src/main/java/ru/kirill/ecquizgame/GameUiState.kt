package ru.kirill.ecquizgame

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt
import java.io.Serializable

interface GameUiState : Serializable{

//    fun update(binding: ActivityMainBinding)
    fun update(questionTextView: UpdateText,
               firstChoiceButton: UpdateChoiceButton,
               secondChoiceButton: UpdateChoiceButton,
               thirdChoiceButton: UpdateChoiceButton,
               fourthChoiceButton: UpdateChoiceButton,
               checkButton: UpdateVisibility,
               nextButton: UpdateVisibility
               )

    data class AskedQuestion(val question: String, val choices: List<String>) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            questionTextView.updateText(question)
            firstChoiceButton.update(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.update(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.update(ChoiceUiState.Initial(choices[2]))
            fourthChoiceButton.update(ChoiceUiState.Initial(choices[3]))
            checkButton.update(VisibilityState(View.GONE))
            nextButton.update(VisibilityState(View.GONE ))
        }

    }

    data class ChoiceMade(val choices: List<ChoiceUiState>) : GameUiState  {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            checkButton.update(VisibilityState(View.VISIBLE))
            nextButton.update(VisibilityState(View.GONE))
        }

    }

    data class AnswerChecked(val choices: List<ChoiceUiState>) : GameUiState  {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            nextButton.update(VisibilityState(View.VISIBLE))
            checkButton.update(VisibilityState(View.GONE))
        }
    }

    object Empty : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) = Unit
    }


}

interface ChoiceUiState : Serializable {
//    fun update(appButton: AppCompatButton)
    fun update(updateChoiceButton: UpdateChoiceButton)

    abstract class Abstract(
        private val color: String,
        private val clickable: Boolean,
        private val isEnabled: Boolean
    ) : ChoiceUiState {
//        override fun update(button: AppCompatButton) {
//            button.text = text
//            button.setBackgroundColor(Color.parseColor(color))
//            button.isClickable = clickable
//            button.isEnabled = isEnabled
//        }
        override fun update(updateChoiceButton: UpdateChoiceButton) {
            Log.d("dd33", "update abstract inside choiceUiState")
            updateChoiceButton.update(color, clickable, isEnabled)
        }
    }

    data class Initial(val text: String) : Abstract( "#7223D3", true, true) {
        override fun update(updateChoiceButton: UpdateChoiceButton) {
            Log.d("dd33", "update initial inside choiceUiState")
            super.update(updateChoiceButton)
            updateChoiceButton.updateText(text)
        }
    }

    object NotAvailableToChoose : Abstract( "#888888", false, false) {

    }

    object AvailableToChoose : Abstract( "#7223D3", true, true) {

    }

    object Correct : Abstract( "#62B857", false, true) {

    }

    object InCorrect : Abstract( "#CE1F1F", false, true) {

    }

}
