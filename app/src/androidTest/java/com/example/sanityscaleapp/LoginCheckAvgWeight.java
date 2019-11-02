//package com.example.sanityscaleapp;
//
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class LoginCheckAvgWeight {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void loginCheckAvgWeight() {
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.logInBtn), withText("Log In"),
//                        childAtPosition(
//                                allOf(withId(R.id.mainScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.emailBox),
//                        childAtPosition(
//                                allOf(withId(R.id.logInScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatEditText.perform(replaceText("david@gmail.com"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.passwordBox),
//                        childAtPosition(
//                                allOf(withId(R.id.logInScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                2),
//                        isDisplayed()));
//        appCompatEditText2.perform(replaceText("davidmayes"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.nextBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.logInScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                3),
//                        isDisplayed()));
//        appCompatButton2.perform(click());
//
//        ViewInteraction button = onView(
//                allOf(withId(R.id.weeklyAvgBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.homeScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                3),
//                        isDisplayed()));
//        button.check(matches(isDisplayed()));
//
//        ViewInteraction appCompatButton3 = onView(
//                allOf(withId(R.id.weeklyAvgBtn), withText("View Weekly Average"),
//                        childAtPosition(
//                                allOf(withId(R.id.homeScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                2),
//                        isDisplayed()));
//        appCompatButton3.perform(click());
//
//        ViewInteraction button2 = onView(
//                allOf(withId(R.id.graphImage),
//                        childAtPosition(
//                                allOf(withId(R.id.graphScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                4),
//                        isDisplayed()));
//        button2.check(matches(isDisplayed()));
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.AvgWeightTextView), withText("Weekly Average Weight: 124.0"),
//                        childAtPosition(
//                                allOf(withId(R.id.graphScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                3),
//                        isDisplayed()));
//        textView.check(matches(withText("Weekly Average Weight: 124.0")));
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
