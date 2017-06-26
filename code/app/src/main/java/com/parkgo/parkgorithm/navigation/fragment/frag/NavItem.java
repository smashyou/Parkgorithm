package com.parkgo.parkgorithm.navigation.fragment.frag;

/**
 * Created by John
 */


import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class NavItem extends Fragment {
    protected View m_vInflated;

    public void fadeIn() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(m_vInflated, "alpha", 0f, 1f);
        anim.setDuration(250);
        anim.start();
    }

    public void fadeOut() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(m_vInflated, "alpha", 1f, 0f);
        anim.setDuration(125);
        anim.start();
    }

    abstract public void setDesign();
}
