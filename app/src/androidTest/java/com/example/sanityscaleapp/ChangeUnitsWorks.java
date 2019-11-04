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
//public class ChangeUnitsWorks {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void changeUnitsWorks() {
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
//                allOf(withId(R.id.menuBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.homeScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        appCompatButton3.perform(click());
//
//        ViewInteraction appCompatButton4 = onView(
//                allOf(withId(R.id.settingsBtn), withText("Settings"),
//                        childAtPosition(
//                                allOf(withId(R.id.homeScreen),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        appCompatButton4.perform(click());
//
//        ViewInteraction appCompatButton5 = onView(
//                allOf(withId(R.id.changeUnitsBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.settingsScreen2),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatButton5.perform(click());
//
//        ViewInteraction appCompatButton6 = onView(
//                allOf(withId(R.id.kgsBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.settingsScreen2),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatButton6.perform(click());
//
//        ViewInteraction imageView = onView(
//                allOf(withId(R.id.bluebuttonkg),
//                        childAtPosition(
//                                allOf(withId(R.id.settingsScreen2),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                5),
//                        isDisplayed()));
//        imageView.check(matches(isDisplayed()));
//
//        ViewInteraction appCompatButton7 = onView(
//                allOf(withId(R.id.lbsBtn),
//                        childAtPosition(
//                                allOf(withId(R.id.settingsScreen2),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                5),
//                        isDisplayed()));
//        appCompatButton7.perform(click());
//
//        ViewInteraction imageView2 = onView(
//                allOf(withId(R.id.bluebuttonlb),
//                        childAtPosition(
//                                allOf(withId(R.id.settingsScreen2),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                4),
//                        isDisplayed()));
//        imageView2.check(matches(isDisplayed()));
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
