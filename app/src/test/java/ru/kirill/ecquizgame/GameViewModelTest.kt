package ru.kirill.ecquizgame

import org.junit.Assert.*;
import org.junit.Before
import org.junit.Test
import ru.kirill.ecquizgame.customview.game.CorrectAndUserChoiceIndexes
import ru.kirill.ecquizgame.customview.game.QuestionChoices
import ru.kirill.ecquizgame.di.ProvideViewModel
import ru.kirill.ecquizgame.fragments.game.ChoiceUiState
import ru.kirill.ecquizgame.fragments.game.GameRepository
import ru.kirill.ecquizgame.fragments.game.GameUiState
import ru.kirill.ecquizgame.fragments.game.GameViewModel
import ru.kirill.ecquizgame.fragments.game.di.ProvideGameViewModel

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        val clear = object : ru.kirill.ecquizgame.di.ClearViewModel {
            override fun clear(viewModelClass: Class<out ru.kirill.ecquizgame.fragments.MyViewModel>) {
                //nothing to do here
            }
        }
        viewModel = GameViewModel(clear = clear, repository = FakeRepository())
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
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose)
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose)
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected  = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        ) //то чему будет равно
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose))
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct,
                ChoiceUiState.InCorrect,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose)
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Finish
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
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose)
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose)
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct,
                ChoiceUiState.InCorrect,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose)
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

    var correct: Int = 0
    var incorrect: Int = 0
    override fun check() : CorrectAndUserChoiceIndexes {
        val correctIndex : Int = questionAndChoices().correctIndex
        if (userChoiceIndex == correctIndex) {
            correct++
        } else {
            incorrect++
        }
        return CorrectAndUserChoiceIndexes(
            correctIndex = correctIndex,
            userChoiceIndex = userChoiceIndex
        )
    }

    override fun next() {
        userChoiceIndex = -1
        index++
//        if (isLastQuestion()) index = 0
    }

    override fun isLastQuestion() : Boolean {
        return index == questionChoices.size
    }

    override fun resetIndex() {
        index = 0
    }
}