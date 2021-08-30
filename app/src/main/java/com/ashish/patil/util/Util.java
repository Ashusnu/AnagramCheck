package com.ashish.patil.util;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.ashish.patil.R;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleObserver;

public class Util implements LifecycleObserver {


    public static void hideSoftKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public static void runAnimation(View view, boolean isAnagram, Context context) {
        if (isAnagram)
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_100));
        else
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.deep_orange));

        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;
        float finalRadius = (float) Math.hypot(cx, cy);
        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius);
        animator.setDuration(1000);
        view.setVisibility(View.VISIBLE);
        animator.start();
    }

}
