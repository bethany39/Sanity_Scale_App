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

public class SignUpUnitsTests {
    @Rule
    public ActivityTestRule<SignUpUnits> activityActivityTestRule = new IntentsTestRule<>(SignUpUnits.class);

    @Before
    public void clickOnLogin(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @Test  //passes!
    public void showsEverythingOnScreen(){
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.kgsBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.lbsBtn)).check(matches(isDisplayed()));
    }
    @Test  //passes!
    public void showsNextScreenFromKgs(){
        onView(withId(R.id.kgsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.signupgoals)).check(matches(isDisplayed()));
}
    @Test  //passes!
    public void showsNextScreenFromLbs(){
        onView(withId(R.id.lbsBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.signupgoals)).check(matches(isDisplayed()));
    }

}
