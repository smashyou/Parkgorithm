package com.parkgo.parkgorithm.draw;

/**
 * Created by John
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;

import java.io.InputStream;

public abstract class AnimationPlayer extends AnimationDrawable {
    Handler m_AnimationHandler;
    AnimationDrawable m_AnimationDrawable;

    public AnimationPlayer(AnimationDrawable _animationDrawable) {
        super();
        m_AnimationDrawable = _animationDrawable;
    }

    @Override
    public void start() {
        m_AnimationDrawable.start();

        /*
         * Call super.start() to call the base class start animation method.
         * Then add a handler to call onAnimationFinish() when the total
         * duration for the animation has passed
         */
        m_AnimationHandler = new Handler(Looper.getMainLooper());
        m_AnimationHandler.postDelayed(new Runnable() {

            public void run() {
                onAnimationFinish();
            }
        }, getTotalDuration());

    }

    /**
     * Gets the total duration of all frames.
     *
     * @return The total duration.
     */
    public int getTotalDuration() {

        int iDuration = 0;

        for (int i = 0; i < m_AnimationDrawable.getNumberOfFrames(); i++) {
            iDuration += m_AnimationDrawable.getDuration(i);
        }

        return iDuration;
    }

    /**
     * Called when the animation finishes.
     */
    abstract public void onAnimationFinish();
}
