package com.example.firebaseauth20230307

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.firebaseauth20230307", appContext.packageName)

        Espresso.onView(ViewMatchers.withText("Authentication"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.edittext_email))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("anbo@secret6.dk"))
        Espresso.onView(withId(R.id.edittext_password))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("secret6"))
            .perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click())
        pause(2000) // to wait for Firebase response to arrive
        Espresso.onView(withId(R.id.textview_hello))
            .check(ViewAssertions.matches(ViewMatchers.withText(("Welcome anbo@secret6.dk"))))
    }

    private fun pause(millis: Long) {
        try {
            Thread.sleep(millis)
            // https://www.repeato.app/android-espresso-why-to-avoid-thread-sleep/
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}