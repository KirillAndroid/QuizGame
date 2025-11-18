package ru.kirill.ecquizgame.load

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import ru.kirill.ecquizgame.R
import java.util.concurrent.TimeoutException

class ErrorUi(
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {


    val errorUi = R.id.errorUi
    private val interaction = Espresso.onView(
        Matchers.allOf(
            withId(errorUi),
            withText(R.string.noInternetText),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun waitTillVisible() {
        onView(isRoot()).perform(waitTillDisplayed(errorUi, 4000))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun waitTillDoesNotExists() {
        onView(isRoot()).perform(waitTillDoesNotExists(errorUi, 4000))
    }
}


