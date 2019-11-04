package com.example.sanityscaleapp;

import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.ViewAction.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)

public class LogInChangeGoal {
    @Rule
    public ActivityTestRule<LogInScreen> activityActivityTestRule = new IntentsTestRule<>(LogInScreen.class);
//    @Rule
//    public IntentsTestRule<LogInScreen> intentsTestRule = new IntentsTestRule<>(LogInScreen.class);
    @Before
    public void registerIdlingResource() {
        // let espresso know to synchronize with background tasks
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
        @Test
        public void logInChangeGoal() {
            //this is not working
            //CountingIdlingResource mainActivityIdlingResource = activityActivityTestRule.getActivity().getEspressoIdlingResourceForMainActivity();
            //IdlingRegistry.getInstance().register(mainActivityIdlingResource);


            onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
            onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

            onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
            onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

            onView(withId(R.id.nextBtn)).perform(click());

            onView(withId(R.id.weeklyAvgBtn)).check(matches(isDisplayed()));

            onView(withId(R.id.menuBtn)).check(matches(isDisplayed())).perform(click());
            onView(withId(R.id.settingsBtn)).check(matches(isDisplayed())).perform(click());
            onView(withId(R.id.goalsBtn)).perform(click());

            onView(withId(R.id.gainBtn)).perform(click());
            onView(withId(R.id.bluebuttong)).check(matches(isDisplayed()));
            onView(withId(R.id.loseBtn)).perform(click());
            onView(withId(R.id.bluebuttonl)).check(matches(isDisplayed()));
            onView(withId(R.id.maintainBtn)).perform(click());
            onView(withId(R.id.bluebuttonm)).check(matches(isDisplayed()));

            //     intended(hasComponent(new ComponentName(InstrumentationRegistry.getInstrumentation().getTargetContext(), HomeScreen.class)));

        }

}