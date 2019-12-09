package com.example.sanityscaleapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoActivityResumedException;
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
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
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
    public void logIn() {
        onView(withId(R.id.logInBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
    }

    @Test
    public void logInLogOut(){
        onView(withId(R.id.logInBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.homeScreen)).perform(DrawerActions.open());
        onView(withId(R.id.homeScreen)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        onView(withId(R.id.yesBtn)).perform(click());
        onView(withId(R.id.logInBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.signUpBtn)).check(matches(isDisplayed()));
//        boolean exceptionThrown = false;
//        try{
//            Espresso.pressBackUnconditionally();
//            onView(withId(R.id.logInScreen)).check(doesNotExist());
//        } catch(NoActivityResumedException e){
//            //this is expected
//            exceptionThrown=true;
//        }
//        assertTrue("NoActivityResumedException thrown, directs back to homescreen", exceptionThrown);

        //or just pressback() if different method chosen
    }

    @Test
    public void logInAlmostLogout() {
        onView(withId(R.id.logInBtn)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
        onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.homeScreen)).perform(DrawerActions.open());
        onView(withId(R.id.homeScreen)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        onView(withId(R.id.noBtn)).perform(click());
        onView(withId(R.id.homeScreen)).check(matches(isDisplayed()));
    }

//    @Test
//    public void signUp() {
//        onView(withId(R.id.signUpBtn)).check(matches(isDisplayed())).perform(click());
//        //This is where we'd look for what's on the next screen (no extra stuff bc no API calls)
//    }





}