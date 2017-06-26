package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parkgo.parkgorithm.navigation.NavigationActivity;
import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import static com.parkgo.parkgorithm.navigation.NavigationActivity.CurrentLocation;
import static com.parkgo.parkgorithm.navigation.NavigationActivity.NavigationItems;
import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;
import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;

/**
 * Created by John
 */
public class NavCenter extends NavItem implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        if (m_vInflated == null)
            m_vInflated = inflater.inflate(R.layout.content_nav_item_center_on_user, null);

        Button btn = (Button)m_vInflated.findViewById(R.id.nav_center_on_user_button);
        btn.setOnClickListener(this);
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        View v = m_vInflated.findViewById(R.id.nav_center_on_user);
        View vCenter = m_vInflated.findViewById(R.id.nav_center_on_user_button);
        ViewGroup.LayoutParams lpCenter = vCenter.getLayoutParams();

        v.setPadding(ScreenSize.x - (lpCenter.width + 20), 0, 0, 20);
    }

    @Override
    public void onClick(View _view){
        if (_view.getId() == R.id.nav_center_on_user_button){
            NavigationActivity navigationActivity = (NavigationActivity)ParkgoServer.getContext();
            NavigationItems.hide(NavItemMediator.NAV_ITEM_ALL);
            NavigationItems.show(NavItemMediator.NAV_ITEM_USER_LOCATION);
            NavigationItems.show(NavItemMediator.NAV_ITEM_SEARCH_BAR);
            navigationActivity.setFollowing(true);
            navigationActivity.setLocation(CurrentLocation);
        }
    }
}
