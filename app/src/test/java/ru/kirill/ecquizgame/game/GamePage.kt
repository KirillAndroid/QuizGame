package ru.kirill.ecquizgame.game

import android.view.View
import android.widget.LinearLayout
import org.hamcrest.Matcher

class GamePage(question: String, choices: List<String>) {
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.game_container))
    private val classTypeMatcher: Matcher<View> = withParent(isAssinableFrom(LinearLayout::class.java))

    private val questionUi: QuestionUI = QuestionUi(text = question, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)
    private val choicesUiList = choices.map { ChoiceUI(text = it, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher) }
    private val checkUI: ButtonUI = ButtonUI(textResId = R.string.check, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)
    private val nextUI: ButtonUI = ButtonUI(textResId = R.string.next, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)

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
        choicesUiList.first().assertNotAvailableState()
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