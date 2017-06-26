package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import static com.parkgo.parkgorithm.navigation.NavigationActivity.NavigationItems;
import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;

/**
 * Created by John
 */
public class NavMoveMarker extends NavItem implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        if (m_vInflated == null)
            m_vInflated = inflater.inflate(R.layout.content_nav_item_movement_marker, null);

        Button btn = (Button)m_vInflated.findViewById(R.id.nav_marker_button);
        btn.setOnClickListener(this);
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        View v = m_vInflated.findViewById(R.id.nav_item_movement_marker_layout);
        View vMovementMarker = m_vInflated.findViewById(R.id.nav_marker_button);
        ViewGroup.LayoutParams lpMovementMarker = vMovementMarker.getLayoutParams();

        v.setPadding(ScreenSize.x / 2 - lpMovementMarker.width / 2, ScreenSize.y / 2 - (lpMovementMarker.height  + lpMovementMarker.height / 3), 0, 0);
    }

    @Override
    public void onClick(View _view){
        if (_view.getId() == R.id.nav_marker_button){
            NavigationItems.hide(NavItemMediator.NAV_ITEM_MOVEMENT_MARKER);
            NavigationItems.show(NavItemMediator.NAV_ITEM_UPLOAD);
        }
    }
}
