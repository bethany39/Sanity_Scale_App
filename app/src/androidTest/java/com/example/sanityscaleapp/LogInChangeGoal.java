package com.example.sanityscaleapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.ViewAction.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


public class LogInChangeGoal {
    @Rule
    public ActivityTestRule<LogInScreen> activityActivityTestRule = new IntentsTestRule<>(LogInScreen.class);


        @Test
        public void logInChangeGoal() {
            //this is not working
            CountingIdlingResource mainActivityIdlingResource = activityActivityTestRule.getActivity().getEspressoIdlingResourceForMainActivity();
            IdlingRegistry.getInstance().register(mainActivityIdlingResource);
            //onView(withId(R.id.logInBtn)).check(matches(isDisplayed()));
            //onView(withId(R.id.logInBtn)).perform(click());

            onView(withId(R.id.emailBox)).check(matches(isDisplayed()));
            onView(withId(R.id.emailBox)).perform(typeText("david@gmail.com"), closeSoftKeyboard());

            onView(withId(R.id.passwordBox)).check(matches(isDisplayed()));
            onView(withId(R.id.passwordBox)).perform(typeText("davidmayes"), closeSoftKeyboard());

            onView(withId(R.id.nextBtn)).perform(click());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                //idc
//            }
            onView(withId(R.id.weeklyAvgBtn)).check(matches(isDisplayed()));
            onView(withId(R.id.menuBtn)).check(matches(isDisplayed())).perform(click());
            onView(withId(R.id.settingsBtn)).check(matches(isDisplayed())).perform(click());
            onView(withId(R.id.goalsBtn)).perform(click());
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                //idc
//            }

            onView(withId(R.id.gainBtn)).perform(click());
            onView(withId(R.id.bluebuttong)).check(matches(isDisplayed()));

            //     intended(hasComponent(new ComponentName(InstrumentationRegistry.getInstrumentation().getTargetContext(), HomeScreen.class)));

        }

}