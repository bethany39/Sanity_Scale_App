package com.example.sanityscaleapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.contrib.NavigationViewActions.navigateTo;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;

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
//        @Override
//        protected Intent getActivityIntent() {
//            Context targetContext = InstrumentationRegistry.getInstrumentation()
//                    .getTargetContext();
//            Intent result = new Intent(targetContext, HomeScreen.class);
//            result.putExtra("USERID", 1);
//            return result;
//        }
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
        onView(withId(R.id.homeScreen)).perform(DrawerActions.open());
        onView(withId(R.id.homeScreen)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));

        onView(withId(R.id.changeUnitsBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.goalsBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.profileBtn)).check(matches(isDisplayed()));
    }




}