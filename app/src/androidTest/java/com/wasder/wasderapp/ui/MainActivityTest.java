package com.wasder.wasderapp.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wasder.wasderapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/29/2017.
 */
@SuppressWarnings("Convert2Diamond")
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
	
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
	
	@Test
	public void BottomNavigationBar() throws InterruptedException {
		//mDevice.findObject(By.res(WASDER_APP_PACKAGE, "navigation_live")).click();
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withId(R.id.navigation_live)).check(matches(isDisplayed())).perform(click());
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withId(R.id.navigation_messages)).check(matches(isDisplayed())).perform(click());
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withId(R.id.main_content_include)).perform(swipeLeft());
		//Thread.sleep(2000);
		onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
		//Thread.sleep(2000);
		onView(withId(R.id.floatingActionButton)).perform(click());
		//Thread.sleep(2000);
	}
}