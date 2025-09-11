package ru.kirill.ecquizgame

class GameViewModel(private val repository : GameRepository) {
    fun chooseFirst() : GameUiState {
        repository.saveUserChoice(0)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose(text = data.choices[0]),
                ChoiceUiState.AvailableToChoose(text = data.choices[1]),
                ChoiceUiState.AvailableToChoose(text = data.choices[2]),
                ChoiceUiState.AvailableToChoose(text = data.choices[3])
            )
        )
    }

    fun chooseSecond() : GameUiState{
        repository.saveUserChoice(1)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = data.choices[0]),
                ChoiceUiState.NotAvailableToChoose(text = data.choices[1]),
                ChoiceUiState.AvailableToChoose(text = data.choices[2]),
                ChoiceUiState.AvailableToChoose(text = data.choices[3])
            )
        )
    }

    fun chooseThird() : GameUiState{
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = data.choices[0]),
                ChoiceUiState.AvailableToChoose(text = data.choices[1]),
                ChoiceUiState.NotAvailableToChoose(text = data.choices[2]),
                ChoiceUiState.AvailableToChoose(text = data.choices[3])
            )
        )
    }

    fun chooseFourth() : GameUiState{
        repository.saveUserChoice(3)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            question = data.question,
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = data.choices[0]),
                ChoiceUiState.AvailableToChoose(text = data.choices[1]),
                ChoiceUiState.AvailableToChoose(text = data.choices[2]),
                ChoiceUiState.NotAvailableToChoose(text = data.choices[3])
            )
        )
    }


    fun check(): GameUiState {
        val questionData = repository.questionAndChoices()
        val (correctIndex, userChoiceIndex) = repository.check()

        val choicesUiState = questionData.choices.mapIndexed { index, choiceText ->
            when {
                // The choice at the correct index is always marked as Correct.
                index == correctIndex -> ChoiceUiState.Correct(choiceText)

                // If the user chose this index and it's not the correct one, mark it as Incorrect.
                index == userChoiceIndex -> ChoiceUiState.InCorrect(choiceText)

                // All other choices were not selected and are now unavailable.
                else -> ChoiceUiState.NotAvailableToChoose(choiceText)
            }
        }

        return GameUiState.AnswerChecked(
            question = questionData.question,
            choices = choicesUiState
        )
    }

    fun next() : GameUiState{
        repository.next()
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
