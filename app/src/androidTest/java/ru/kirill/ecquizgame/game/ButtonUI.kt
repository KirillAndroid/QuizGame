package ru.kirill.ecquizgame.game

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not

class ButtonUI(
    id: Int,
    textResId: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractButtonUi (
    interaction = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.withText(textResId),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }
}

abstract class AbstractButtonUi(
    protected val interaction: ViewInteraction
) {
    fun click() {
        interaction.perform(ViewActions.click())
    }
}
