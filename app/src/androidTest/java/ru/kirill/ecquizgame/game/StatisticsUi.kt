package ru.kirill.ecquizgame.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.kirill.ecquizgame.R

class StatisticsUi(
    correct: Int,
    incorrect: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    private val text = "Statistics: \n Correct = $correct, Incorrect = $incorrect"
    private val interaction: ViewInteraction = onView(
        allOf(
            withText(text),
            withId(R.id.statisticsTextView),
            containerIdMatcher,
            classTypeMatcher
        )
    )

    fun assertTextIsDisplayed() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExists() {
        interaction.check(doesNotExist())
    }
}
