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

public class SignUpHomeScreenTests{
    @Rule
    public ActivityTestRule<SignUpHome> activityActivityTestRule = new IntentsTestRule<>(SignUpHome.class);
    @Test
    public void showsErrorOnLoginEmptyFields(){
        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.blankError)).check(matches(isDisplayed()));
    }
    @Before
    public void clickOnSignUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }



    @Test   //test passes!
    public void showsErrorOnSignupWithIncorrectEmails() {
        onView(withId(R.id.nameBox)).check(matches(isDisplayed()));
        onView(withId(R.id.nameBox)).perform(typeText("Davey"), closeSoftKeyboard());
        onView(withId(R.id.emailbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.emailbox_signup)).perform(typeText("davey@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.confirmemailbox)).perform(typeText("davey@gmail"),closeSoftKeyboard());

        onView(withId(R.id.passwordbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordbox_signup)).perform(typeText("daveyjones"), closeSoftKeyboard());
        onView(withId(R.id.confirmpassword)).perform(typeText("daveyjones"),closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.emailError)).check(matches(isDisplayed()));
    }

    @Test //passes
    public void showsErrorOnSignupWithIncorrectPasswords() {
        onView(withId(R.id.nameBox)).check(matches(isDisplayed()));
        onView(withId(R.id.nameBox)).perform(typeText("Davey"), closeSoftKeyboard());
        onView(withId(R.id.emailbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.emailbox_signup)).perform(typeText("davey@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.confirmemailbox)).perform(typeText("davey@gmail.com"),closeSoftKeyboard());

        onView(withId(R.id.passwordbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordbox_signup)).perform(typeText("daveyjones"), closeSoftKeyboard());
        onView(withId(R.id.confirmpassword)).perform(typeText("davey"),closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.passwordError)).check(matches(isDisplayed()));
    }

    @Test //passes
    public void showsSignUpHomeWorks() {
        onView(withId(R.id.nameBox)).check(matches(isDisplayed()));
        onView(withId(R.id.nameBox)).perform(typeText("Davey"), closeSoftKeyboard());
        onView(withId(R.id.emailbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.emailbox_signup)).perform(typeText("davey@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.confirmemailbox)).perform(typeText("davey@gmail.com"),closeSoftKeyboard());

        onView(withId(R.id.passwordbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordbox_signup)).perform(typeText("daveyjones"), closeSoftKeyboard());
        onView(withId(R.id.confirmpassword)).perform(typeText("daveyjones"),closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.intro1)).check(matches(isDisplayed()));
    }

    @Test //passes
    public void showsSignUpHomeEmailExistsError() {
        onView(withId(R.id.nameBox)).check(matches(isDisplayed()));
        onView(withId(R.id.nameBox)).perform(typeText("Davey"), closeSoftKeyboard());
        onView(withId(R.id.emailbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.emailbox_signup)).perform(typeText("david@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.confirmemailbox)).perform(typeText("david@gmail.com"),closeSoftKeyboard());

        onView(withId(R.id.passwordbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordbox_signup)).perform(typeText("daveyjones"), closeSoftKeyboard());
        onView(withId(R.id.confirmpassword)).perform(typeText("daveyjones"),closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.emailexistsError)).check(matches(isDisplayed()));
    }

    @Test //passes
    public void showsSignUpHomeEmailAndPWError() {
        onView(withId(R.id.nameBox)).check(matches(isDisplayed()));
        onView(withId(R.id.nameBox)).perform(typeText("Davey"), closeSoftKeyboard());
        onView(withId(R.id.emailbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.emailbox_signup)).perform(typeText("davey@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.confirmemailbox)).perform(typeText("davey@gmail"),closeSoftKeyboard());

        onView(withId(R.id.passwordbox_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordbox_signup)).perform(typeText("daveyjones"), closeSoftKeyboard());
        onView(withId(R.id.confirmpassword)).perform(typeText("davey"),closeSoftKeyboard());

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.emailandpassError)).check(matches(isDisplayed()));
    }






}