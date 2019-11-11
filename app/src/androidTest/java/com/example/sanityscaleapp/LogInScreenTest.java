package com.example.sanityscaleapp;

import android.util.Log;

import androidx.test.espresso.IdlingRegistry;
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

}