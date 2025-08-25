package ru.kirill.ecquizgame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.kirill.ecquizgame.R

class GamePage(question: String, choices: List<String>) {
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.game_container))
    private val classTypeMatcher: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val questionUi = QuestionUi(text = question, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)

    private val choicesUiListIds = listOf<Int>(
        R.id.firstChoiceButton,
        R.id.secondChoiceButton,
        R.id.thirdChoiceButton,
        R.id.forthChoiceButton
    )
    private val choicesUiList = choices.mapIndexed {index, string ->
        ChoiceUI(id = choicesUiListIds[index], colorHex = "#7223D3", text = string, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)
    }
    private val checkUI: ButtonUI = ButtonUI(id = R.id.checkButton, textResId = R.string.check, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)
    private val nextUI: ButtonUI = ButtonUI(id = R.id.nextButton, textResId = R.string.next, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)

    fun assertAskedQuestionState() {
        questionUi.assertTextIsDisplayed()
        choicesUiList.forEach {
            it.assertChoiceAvailableState()
        }
        checkUI.assertNotVisible()
        nextUI.assertNotVisible()
    }

    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMadeState() {
        questionUi.assertTextIsDisplayed()
        choicesUiList.first().assertChoiceNotAvailableState()
        choicesUiList.drop(1).forEach {
            it.assertChoiceAvailableState()
        }
        checkUI.assertVisible()
        nextUI.assertNotVisible()
    }

    fun clickCheckButton() {
        checkUI.click()
    }

    fun assertAnswerCheckedStateFirstIsCorrect() {
        questionUi.assertTextIsDisplayed()
        choicesUiList.first().assertCorrectState()
        choicesUiList.drop(1).forEach {
            it.assertChoiceNotAvailableState()
        }
        checkUI.assertNotVisible()
        nextUI.assertVisible()
    }

    fun clickSecondChoice() {
        choicesUiList[1].click()
    }

    fun assertSecondChoiceMadeState() {
        questionUi.assertTextIsDisplayed()
        choicesUiList.first().assertChoiceAvailableState()
        choicesUiList[1].assertChoiceNotAvailableState()
        choicesUiList.drop(2).forEach {
            it.assertChoiceAvailableState()
        }
        checkUI.assertVisible()
        nextUI.assertNotVisible()
    }

    fun assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect() {
        questionUi.assertTextIsDisplayed()
        choicesUiList.first().assertCorrectState()
        choicesUiList[1].assertIncorrectState()
        choicesUiList.drop(2).forEach {
            it.assertChoiceNotAvailableState()
        }
        checkUI.assertNotVisible()
        nextUI.assertVisible()
    }

    fun clickNextButton() {
        nextUI.click()
    }
}