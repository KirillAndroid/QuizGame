package ru.kirill.ecquizgame

import org.junit.Assert.*;
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init() //то что из модели
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        ) //то чему будет равно
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf(ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"))
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            question = "q1",
            choices = listOf(ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"))
        )
        assertEquals(expected, actual)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init() //то что из модели
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        ) //то чему будет равно
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf(ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"))
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf(ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"))
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            question = "q1",
            choices = listOf(ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.InCorrect(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"))
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected  = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        ) //то чему будет равно
        assertEquals(expected, actual)
    }
}

private class FakeRepository: GameRepository {
    private val questionChoices = listOf<QuestionChoices>(
        QuestionChoices(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4"),
            correctIndex = 0
        ),
        QuestionChoices(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4"),
            correctIndex = 0
        )
    )
    private var index: Int = 0
    private var userChoiceIndex = -1

    override fun questionAndChoices() : QuestionChoices {
        return questionChoices[index]
    }

    override fun saveUserChoice(index: Int) {
        userChoiceIndex = index
    }

    override fun check() : CorrectAndUserChoiceIndexes {
        val correctIndex : Int = questionAndChoices().correctIndex
        val userChoiceIndex: Int = userChoiceIndex
        return CorrectAndUserChoiceIndexes(
            correctIndex = correctIndex,
            userChoiceIndex = userChoiceIndex
        )
    }

    override fun next() {
        userChoiceIndex = -1
        index++
        if (index == questionChoices.size) index = 0
    }
}