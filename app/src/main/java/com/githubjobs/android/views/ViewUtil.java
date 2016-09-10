package com.githubjobs.android.views;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewUtil {

    /** Hide soft input keyboard */
    public static void hideKeybord(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}
