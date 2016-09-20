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
import com.githubjobs.android.entity.Job;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class JobListFgmtTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jobDetailsFgmtTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(ViewMatchers.withId(R.id.fgmt_jobsearch_et_title), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.fgmt_jobsearch_et_title), isDisplayed()));
        appCompatEditText2.perform(replaceText("Developer"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.fgmt_joblist_btn_search), withText("SEARCH"), isDisplayed()));
        appCompatButton.perform(click());

        // Perform click to first item in ListView
        onData(is(instanceOf(Job.class)))
                .atPosition(0)
                .perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.fgmt_jobdetails_iv),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_company_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView1 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                3),
                        isDisplayed()));
        textView1.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_type),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                4),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_location),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                5),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withText("Description:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                7),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_description),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withText("How to Apply:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                10),
                        isDisplayed()));
        textView6.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_how_to_apply),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                11),
                        isDisplayed()));
        textView7.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withText("Link:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                13),
                        isDisplayed()));
        textView8.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_link),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                14),
                        isDisplayed()));
        textView9.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.fgmt_jobdetails_tv_link),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_container),
                                        0),
                                14),
                        isDisplayed()));
        textView10.check(matches(isDisplayed()));

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
