package ru.kirill.ecquizgame.load

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.kirill.ecquizgame.R
import ru.kirill.ecquizgame.game.ButtonUI

class LoadPage {

    private val containerIdMatcher: Matcher<View> =
        ViewMatchers.withParent(withId(R.id.loadContainer))
    private val classTypeMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))

    private val progressUi = ProgressUI(containerIdMatcher, classTypeMatcher)

    private val errorUI = ErrorUi(containerIdMatcher = containerIdMatcher, classTypeMatcher = classTypeMatcher)

    private val retryUI = ButtonUI(
        R.id.retryButton,
        R.string.retry,
        containerIdMatcher,
        classTypeMatcher
    )


    fun assertProgressState() {
        progressUi.assertVisible()
        errorUI.assertNotVisible()
        retryUI.assertNotVisible()
    }

    fun waitTillError() {
        errorUI.waitTillVisible()
    }

    fun assertErrorState() {
        errorUI.assertVisible()
        retryUI.assertVisible()
        progressUi.assertNotVisible()
    }

    fun clickRetryButton() {
        retryUI.click()
    }

    fun waitTillGone() {
        errorUI.waitTillDoesNotExists()
    }

}