package ru.kirill.ecquizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.kirill.ecquizgame.game.GameOverPage
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
        gamePage = GamePage(
            question = "What is the capital of France?",
            choices = listOf("Paris", "Madrid", "Berlin", "Rome")
        )
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

        gamePage = GamePage(
            question = "What is the capital of Germany?",
            choices = listOf("Berlin", "Madrid", "Paris", "Rome")
        )
        activityScenarioRule.scenario.recreate()

        gamePage.assertAskedQuestionState()
    }

    @Test
    fun caseNumber3() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()

        gamePage.clickNextButton()

        gamePage = GamePage(
            question = "What is the capital of Germany?",
            choices = listOf("Berlin", "Madrid", "Paris", "Rome")
        )
        activityScenarioRule.scenario.recreate()

        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()

        gamePage.clickNextButton()
        gamePage.assertNotVisible()
        var gameOverPage = GameOverPage(
            correct = 2, incorrect = 0
        )
        gameOverPage.assertGameOverState()
        activityScenarioRule.scenario.recreate()
        gameOverPage.clickNewGameButton()
        gameOverPage.assertNotVisible()

        setUp()

//        gamePage = GamePage(
//            question = "What color is the blood",
//            choices = listOf("red", "green", "blue", "yellow")
//        )


        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNextButton()

        gamePage = GamePage(
            question = "What is the capital of Germany?",
            choices = listOf("Berlin", "Madrid", "Paris", "Rome")
        )

        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNextButton()
        gamePage.assertNotVisible()
        gameOverPage = GameOverPage(
            correct = 0, incorrect = 2
        )
        gameOverPage.assertGameOverState()
        activityScenarioRule.scenario.recreate()
        gameOverPage.clickNewGameButton()
        gameOverPage.assertNotVisible()

        setUp()

//        gamePage = GamePage(
//            question = "What color is the white house",
//            choices = listOf("white", "green", "blue", "yellow")
//        )
        activityScenarioRule.scenario.recreate()

        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()


        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()

        gamePage.clickNextButton()

        gamePage = GamePage(
            question = "What is the capital of Germany?",
            choices = listOf("Berlin", "Madrid", "Paris", "Rome")
        )
        activityScenarioRule.scenario.recreate()

        gamePage.assertAskedQuestionState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNextButton()
        gamePage.assertNotVisible()
        gameOverPage = GameOverPage(
            correct = 1, incorrect = 1
        )
        gameOverPage.assertGameOverState()




    }

    @Test
    fun testCase4() {
        val loadPage = LoadPage()

        activityScenarioRule.scenario.recreate()
        loadPage.assertProgressState()
        activityScenarioRule.scenario.recreate()

        loadPage.waitTillError()

        loadPage.assertErrorState()
        activityScenarioRule.scenario.recreate()
        loadPage.assertErrorState()
        loadPage.clickRetryButton()
        loadPage.assertProgressState()
        loadPage.waitTillGone()
    }
}