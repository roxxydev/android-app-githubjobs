package com.githubjobs.android.fragment;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.githubjobs.android.MainActivity;
import com.githubjobs.android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class JobSearchFgmtTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void joSearchFgmtTest() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.fgmt_jobsearch_et_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.fgmt_jobsearch_et_company),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.fgmt_jobsearch_et_location),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                2),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.fgmt_jobsearch_cb_fulltime),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                5),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.fgmt_jobsearch_cb_parttime),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                6),
                        isDisplayed()));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.fgmt_joblist_btn_search),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                9),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
