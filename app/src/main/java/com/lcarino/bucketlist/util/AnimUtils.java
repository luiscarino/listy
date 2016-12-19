package com.lcarino.bucketlist.util;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * @author Luis Carino.
 */

public class AnimUtils {

    private AnimUtils(){
    }

    /**
     * Creates a circular reveal animation.
     * @param view - The view to apply animation.
     * @param cx    - the center coordinate in x for the clipping circle.
     * @param cy    - the center coordinate in y for the clipping circle.
     * @param finalRadius - the final radius for the clipping circle.
     * @param listener - Optional animator listener
     */
    public static void startReveal(View view, int cx, int cy, int startRadius, int finalRadius, @Nullable Animator.AnimatorListener listener) {

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);

        if(listener != null){
            anim.addListener(listener);
        }

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();

    }

}
