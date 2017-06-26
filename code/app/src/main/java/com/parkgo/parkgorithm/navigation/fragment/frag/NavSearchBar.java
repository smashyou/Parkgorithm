package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.NavigationActivity;
import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;
import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;
import static com.parkgo.parkgorithm.navigation.NavigationMap.NavigationItems;

/**
 * Created by John
 */
public class NavSearchBar extends NavItem implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        m_vInflated = inflater.inflate(R.layout.content_nav_item_search, null);
        Button button = (Button)m_vInflated.findViewById(R.id.nav_search_button);
        button.setOnClickListener(this);
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        View v = m_vInflated.findViewById(R.id.nav_item_search_layout);
        View text = m_vInflated.findViewById(R.id.nav_search_edit_text);
        View button = m_vInflated.findViewById(R.id.nav_search_button);
        ViewGroup.LayoutParams lParamText = text.getLayoutParams();
        ViewGroup.LayoutParams lParamButton = button.getLayoutParams();

        lParamText.width = ScreenSize.x - (lParamButton.width + 40);

        // Fragment padding
        v.setPadding(20, 20, 0, 0);
        text.setLayoutParams(lParamText);
    }

    @Override
    public void onClick(View _view){
        NavigationActivity navigationActivity = (NavigationActivity)ParkgoServer.getContext();
        navigationActivity.setFollowing(false);
        navigationActivity.searchForSpaces();
        NavigationItems.hide(NavItemMediator.NAV_ITEM_USER_LOCATION);
        NavigationItems.show(NavItemMediator.NAV_ITEM_MOVEMENT_MARKER);
        NavigationItems.show(NavItemMediator.NAV_ITEM_CENTER_ON_USER);
    }

}
