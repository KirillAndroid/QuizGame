package ru.kirill.ecquizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import ru.kirill.ecquizgame.game.GamePage

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setUp() {
        gamePage = GamePage(question = "What is the capital of France?",
            choices = listOf("Paris", "Madrid", "Berlin", "Rome"))
    }

    @Test
    fun caseNumber1() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    @Test
    fun caseNumber2() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickSecondChoice()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()

        gamePage.clickNextButton()

        gamePage = GamePage(question = "What is the capital of Germany?",
            choices = listOf("Berlin", "Madrid", "Paris", "Rome"))
        activityScenarioRule.scenario.recreate()

        gamePage.assertAskedQuestionState()
    }
}