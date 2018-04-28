package com.example.maciek.godt;

import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.widget.AutoCompleteTextView;

import com.example.maciek.godt.ui.RecipesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class UiTest {

    private long WAITING_TIME = DateUtils.SECOND_IN_MILLIS * 5;


    @Rule
    public ActivityTestRule<RecipesActivity> recipesActivityRule =
            new ActivityTestRule<>(RecipesActivity.class);

    @Test
    public void mainMenuIsDisplayed() {
        onView(withText(recipesActivityRule.getActivity().getString(R.string.app_name))).check(matches(isDisplayed()));
    }

    @Test
    public void searchFiltersResults() {
        android.os.SystemClock.sleep(WAITING_TIME);
        onView(isRoot()).check(matches(anything()));
        RecyclerView recyclerView = recipesActivityRule.getActivity().findViewById(R.id.recipes_recycler_view);
        assertTrue(recyclerView.getAdapter().getItemCount() > 0);
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText("NoWayThatThisStringIsInSomeRecipes"));
        assertTrue(recyclerView.getAdapter().getItemCount() == 0);
    }

    @Test
    public void isDataDisplayed() {
        android.os.SystemClock.sleep(WAITING_TIME);
        RecyclerView recyclerView = recipesActivityRule.getActivity().findViewById(R.id.recipes_recycler_view);
        assertTrue(recyclerView.getAdapter().getItemCount() > 0);
    }
}
