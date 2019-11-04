package com.example.sanityscaleapp;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

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

public class MainActivityTests {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void logIn() {
        onView(withId(R.id.logInBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
    }
    /*
    @Test
    public void signUp() {
        onView(withId(R.id.signUpBtn)).check(matches(isDisplayed())).perform(click());
        //This is where we'd look for what's on the next screen (no extra stuff bc no API calls)
    }
     */

}