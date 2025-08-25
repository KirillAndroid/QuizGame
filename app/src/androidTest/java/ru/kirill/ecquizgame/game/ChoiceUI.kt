package ru.kirill.ecquizgame.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChoiceUI(
    text: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>,
    id: Int,
    colorHex: String
) : AbstractButtonUi(
    onView(
        allOf(
            withText(text),
            containerIdMatcher,
            classTypeMatcher,
            withId(id),
            isAssignableFrom(AppCompatButton::class.java),
            isDisplayed()
        )
    )
){
    fun assertChoiceAvailableState() {
        interaction.check(matches(ButtonColorMatcher("#7223D3")))
            .check(matches(isClickable()))
                .check(matches(isEnabled()))
    }

    fun assertCorrectState() {
        interaction.check(matches(ButtonColorMatcher("#62B857")))
            .check(matches(isNotClickable()))
            .check(matches(isEnabled()))
    }

    fun assertChoiceNotAvailableState() {
        interaction
            .check(matches(isNotEnabled()))
    }

    fun assertIncorrectState() {
        interaction.check(matches(ButtonColorMatcher("#CE1F1F")))
            .check(matches(isNotClickable()))
            .check(matches(isEnabled()))
    }

}
