package ru.kirill.ecquizgame.gragments.game

class GameViewModel(private val repository : GameRepository) {
    fun chooseFirst() : GameUiState {
        repository.saveUserChoice(0)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
    }

    fun chooseSecond() : GameUiState {
        repository.saveUserChoice(1)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
    }

    fun chooseThird() : GameUiState {
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
    }

    fun chooseFourth() : GameUiState {
        repository.saveUserChoice(3)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose
            )
        )
    }


    fun check(): GameUiState {
        val questionData = repository.questionAndChoices()
        val (correctIndex, userChoiceIndex) = repository.check()

        val choicesUiState = questionData.choices.mapIndexed { index, choiceText ->
            when {
                // The choice at the correct index is always marked as Correct.
                index == correctIndex -> ChoiceUiState.Correct

                // If the user chose this index and it's not the correct one, mark it as Incorrect.
                index == userChoiceIndex -> ChoiceUiState.InCorrect

                // All other choices were not selected and are now unavailable.
                else -> ChoiceUiState.NotAvailableToChoose
            }
        }

        return GameUiState.AnswerChecked(
            choices = choicesUiState
        )
    }

    fun next() : GameUiState {
        repository.next()
        if (repository.isLastQuestion()) {
            return GameUiState.Finish
        }
        return init()
    }

    fun init() : GameUiState {
        val data = repository.questionAndChoices()
        return GameUiState.AskedQuestion(
            question = data.question,
            choices = data.choices
        )
    }
}