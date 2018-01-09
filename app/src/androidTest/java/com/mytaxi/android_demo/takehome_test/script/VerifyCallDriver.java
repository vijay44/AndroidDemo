package com.mytaxi.android_demo.takehome_test.script;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.takehome_test.funtions.AuthenticationActFunctions;
import com.mytaxi.android_demo.takehome_test.funtions.MainActFunctions;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by vselvarajah on 1/8/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VerifyCallDriver {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;
    private AuthenticationActFunctions authenticationActFunctions;
    private MainActFunctions mainActFunctions;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityRule.getActivity();
    }

    @Test
    public void verifyCall() throws InterruptedException {
        authenticationActFunctions = new AuthenticationActFunctions();
        mainActFunctions = new MainActFunctions();

        // USER LOGIN 
        authenticationActFunctions.userLogin(mainActivity.getString(R.string.userName),mainActivity.getString(R.string.user_password));

        Espresso.registerIdlingResources(mainActivity.getIdlingResource());

        //Type Driver's Name First Few Letters to Search
        onView(withId(R.id.textSearch)).perform(typeText(mainActivity.getString(R.string.driverNameKey)), closeSoftKeyboard());

        onView(withText(mainActivity.getString(R.string.driverName)))
                .inRoot(withDecorView(not(is(mainActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        //Select Driver's Name
        onView(withText(mainActivity.getString(R.string.driverName)))
                .inRoot(withDecorView(not(is(mainActivity.getWindow().getDecorView()))))
                .perform(click());

        //Verify Driver Details
        onView(withId(R.id.textViewDriverName)).check(matches(withText(mainActivity.getString(R.string.driverName))));
        onView(withId(R.id.textViewDriverLocation)).check(matches(withText(mainActivity.getString(R.string.driverLocation))));

        onView(withId(R.id.fab)).check(matches(isClickable()));
//        onView(withId(R.id.fab)).perform(click());
        Espresso.pressBack();


    }

    @After
    public void tearDown() throws Exception {
        //LOGOUT
        mainActFunctions.logoutFromMainAct();
    }

}