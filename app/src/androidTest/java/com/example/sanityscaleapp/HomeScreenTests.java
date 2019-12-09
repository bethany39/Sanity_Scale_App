//package com.example.sanityscaleapp;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//
//import android.app.Instrumentation.ActivityResult;
//
//import androidx.test.espresso.IdlingRegistry;
//import androidx.test.espresso.intent.rule.IntentsTestRule;
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.rule.ActivityTestRule;
//import static androidx.test.espresso.intent.Intents.intended;
//import static androidx.test.espresso.intent.Intents.intending;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.core.AllOf.allOf;
//
//import static org.junit.Assert.*;
//
//@RunWith(AndroidJUnit4.class)
//public class HomeScreenTests {
//    //    @Rule
////    public IntentsTestRule<LogInScreen> intentsTestRule = new IntentsTestRule<>(LogInScreen.class);
//    @Rule
//    public ActivityTestRule<HomeScreen> activityActivityTestRule = new IntentsTestRule<HomeScreen>(HomeScreen.class){
//        @Override
//        protected Intent getActivityIntent() {
//            Context targetContext = InstrumentationRegistry.getInstrumentation()
//                    .getTargetContext();
//            Intent result = new Intent(targetContext, HomeScreen.class);
//            result.putExtra("USERID", 1);
//            return result;
//        }
//    };
//
//    @Before
//    public void registerIdlingResource() {
//        // let espresso know to synchronize with background tasks
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
//    }
//    @After
//    public void unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
//    }
//    @Test //no longer works--session id not user id - moved to a login test (so sessionid can be used properly)
//    public void weeklyAvgBtnGoesToGraphScreen() {
//        //IdlingRegistry.getInstance().register(mainActivityIdlingResource);
//        onView(withId(R.id.weeklyAvgBtn)).check(matches(isDisplayed())).perform(click());
//        onView(withId(R.id.graphScreen)).check(matches(isDisplayed()));
//    }
//}