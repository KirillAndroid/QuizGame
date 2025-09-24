package ru.kirill.ecquizgame.game

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.kirill.ecquizgame.R

class GameOverPage(correct: Int, incorrect: Int) {


    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.game_over_container))
    private val classTypeMatcher: Matcher<View> = withParent(isAssignableFrom(FrameLayout::class.java))

    private val statistics: StatisticsUi = StatisticsUi(correct = correct, incorrect = incorrect, containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)
    private val newGame: ButtonUI = ButtonUI(id = R.id.newGameButton, R.string.newgame, containerIdMatcher, classTypeMatcher)

    fun assertGameOverState() {
        statistics.assertTextIsDisplayed()
        newGame.assertVisible()
    }

    fun clickNewGameButton() {
        newGame.click()
    }

    fun assertNotVisible() {
        statistics.assertDoesNotExists()
    }
}