package com.example.sanityscaleapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SettingsScreenTests {

    @Rule
    public ActivityTestRule<HomeScreen> activityActivityTestRule = new IntentsTestRule<HomeScreen>(HomeScreen.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, HomeScreen.class);
            result.putExtra("USERID", 1);
            return result;
        }
    };

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
    public void allTabsAreShown() {
        onView(withId(R.id.menuBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.settingsBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.changeUnitsBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.goalsBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.profileBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void changesSelectedUnits() {
        onView(withId(R.id.menuBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.settingsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.changeUnitsBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.kgsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonkg)).check(matches(isDisplayed()));

        onView(withId(R.id.lbsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonlb)).check(matches(isDisplayed()));
        //doing the kgs a second time in case it was already selected the first time
        onView(withId(R.id.kgsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonkg)).check(matches(isDisplayed()));
    }

    @Test
    public void changesSelectedGoal(){
        onView(withId(R.id.menuBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.settingsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.goalsBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.maintainBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonm)).check(matches(isDisplayed()));

        onView(withId(R.id.loseBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonl)).check(matches(isDisplayed()));

        onView(withId(R.id.gainBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttong)).check(matches(isDisplayed()));
        //repeating this for same reason as units
        onView(withId(R.id.maintainBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.bluebuttonm)).check(matches(isDisplayed()));

    }


}