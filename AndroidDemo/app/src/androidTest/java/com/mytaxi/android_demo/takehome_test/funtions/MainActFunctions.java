package com.mytaxi.android_demo.takehome_test.funtions;

import android.view.Gravity;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by vselvarajah on 1/8/2018.
 */

public class MainActFunctions {
    MainActivity mainActivity;

    public void openLeftNav(){
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
    }

    public void logout(){
        onView(withText("Logout")).perform(click());
    }

    public void logoutFromMainAct(){
        openLeftNav();
        logout();
    }

}
