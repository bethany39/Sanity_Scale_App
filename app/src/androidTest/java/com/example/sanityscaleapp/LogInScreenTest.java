package com.example.sanityscaleapp;

import android.util.Log;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.ViewAction.*;
import static androidx.test.espresso.action.ViewActions.click;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)

public class LogInScreenTest {

    @Rule
    public ActivityTestRule<LogInScreen> activityActivityTestRule = new IntentsTestRule<>(LogInScreen.class);

    @Before
    public void clickOnLogin(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }


    @Test
    public void showsErrorOnLoginEmptyFields(){
        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.logInError)).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorOnLoginWithIncorrectEmail() {
        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.co"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.logInError)).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorOnLoginWithIncorrectPassword() {
        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.co"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmaye"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.logInError)).check(matches(isDisplayed()));
    }


    @Test
    public void changesSelectedUnits() {
//        onView(withId(R.id.nav_view)).check(matches(isDisplayed())).perform(click());
//        onView(withId(R.id.nav_settings)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.homeScreen)).perform(DrawerActions.open());
        onView(withId(R.id.homeScreen)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));
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

        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.homeScreen)).perform(DrawerActions.open());
        onView(withId(R.id.homeScreen)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));
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

    @Test //no longer works--session id not user id - moved to a login test (so sessionid can be used properly)
    public void weeklyAvgBtnGoesToGraphScreen() {
        //IdlingRegistry.getInstance().register(mainActivityIdlingResource);
        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weeklyAvgBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.graphScreen)).check(matches(isDisplayed()));
    }

}