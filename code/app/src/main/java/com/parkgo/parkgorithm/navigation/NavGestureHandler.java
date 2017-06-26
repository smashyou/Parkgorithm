package com.parkgo.parkgorithm.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;

/**
 * Created by John
 * Custom event handling for Navigation Fragment Activity
 */
public class NavGestureHandler extends RelativeLayout{
    private GestureDetector gestureDetector;

    /**
     * Constructor
     * @param context [in] fragmented activity
     * @param attrs [in] handler attributes
     */
    public NavGestureHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    /**
     * Navigation event interception
     * @param ev [in] event
     * @return false
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return false;
    }

    /**
     * Custom map event handling
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            return false;
        }

        /**
         * Scroll movement changes :
         *      1 - State
         *      2 - appearance
         *      3 - functionality
         * @param e1 [in] event one
         * @param e2 [in] event two
         * @param distanceX [in] distance in x-direction
         * @param distanceY [in] distance in y-direction
         * @return false
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            NavigationActivity activity = (NavigationActivity)ParkgoServer.getContext();
            activity.setFollowing(false);

            // HIDE ELEMENTS
            NavigationActivity.NavigationItems.hide(NavItemMediator.NAV_ITEM_UPLOAD);
            NavigationActivity.NavigationItems.hide(NavItemMediator.NAV_ITEM_USER_LOCATION);
            NavigationActivity.NavigationItems.hide(NavItemMediator.NAV_ITEM_UPLOAD);
            NavigationActivity.NavigationItems.hide(NavItemMediator.NAV_ITEM_SEARCH_BAR);
            NavigationActivity.NavigationItems.hide(NavItemMediator.NAV_ITEM_FOREIGN_CLIENT);

            // SHOW ELEMENTS
            NavigationActivity.NavigationItems.show(NavItemMediator.NAV_ITEM_MOVEMENT_MARKER);
            NavigationActivity.NavigationItems.show(NavItemMediator.NAV_ITEM_CENTER_ON_USER);
            return false;
        }
    }

}
