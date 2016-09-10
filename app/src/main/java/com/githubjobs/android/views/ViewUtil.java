package com.githubjobs.android.views;

import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
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

    /** Convert HTML value to spanned text representation */
    public static Spanned convertHtmlToTextFormat(String htmlValue) {
        Spanned textValue;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textValue = Html.fromHtml(htmlValue, Html.FROM_HTML_MODE_LEGACY);
        } else {
            textValue = Html.fromHtml(htmlValue);
        }
        return textValue;
    }
}
