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

public class SignUpGoalsTests {
    @Rule
    public ActivityTestRule<SignUpGoals> activityActivityTestRule = new IntentsTestRule<>(SignUpGoals.class);

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
        onView(withId(R.id.maintainBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.gainBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.loseBtn)).check(matches(isDisplayed()));
    }
    @Test  //does not pass
    public void showsNextScreenFromMaintain(){
        onView(withId(R.id.maintainBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.signupmessage)).check(matches(isDisplayed()));
    }
    @Test  //does not pass...for some reason it clicks on the lose btn!
    public void showsNextScreenFromGain(){
        onView(withId(R.id.gainBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.signupmessage)).check(matches(isDisplayed()));
    }
    @Test  //does not pass
    public void showsNextScreenFromLose(){
        onView(withId(R.id.loseBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.signupmessage)).check(matches(isDisplayed()));
    }

}
