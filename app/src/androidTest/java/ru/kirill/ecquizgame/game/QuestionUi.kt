package ru.kirill.ecquizgame.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.kirill.ecquizgame.R

class QuestionUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction = onView(
        allOf(
            withText(text),
            containerIdMatcher,
            classTypeMatcher,
            withId(R.id.question_textview),
            isAssignableFrom(TextView::class.java)
        )
    )
    fun assertTextIsDisplayed() {
        interaction.check(matches(isDisplayed()))
    }

}
