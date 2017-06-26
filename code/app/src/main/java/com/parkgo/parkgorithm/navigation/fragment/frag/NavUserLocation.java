package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;
import static com.parkgo.parkgorithm.navigation.NavigationActivity.NavigationItems;
/**
 * Created by John
 */
public class NavUserLocation extends NavItem implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        if (m_vInflated == null)
            m_vInflated = inflater.inflate(R.layout.content_nav_item_location, null);

        Button btn = (Button)m_vInflated.findViewById(R.id.nav_location_button);
        btn.setOnClickListener(this);
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        View v = m_vInflated.findViewById(R.id.nav_item_location_layout);
        View vLocationView = m_vInflated.findViewById(R.id.nav_location_button);
        ViewGroup.LayoutParams lpLocationParams = vLocationView.getLayoutParams();

        v.setPadding(ScreenSize.x / 2 - lpLocationParams.width / 2, ScreenSize.y / 2 - (lpLocationParams.height + lpLocationParams.height / 3), 0, 0);
    }

    @Override
    public void onClick(View _view){
        if (_view.getId() == R.id.nav_location_button){
            NavigationItems.hide(NavItemMediator.NAV_ITEM_USER_LOCATION);
            NavigationItems.show(NavItemMediator.NAV_ITEM_UPLOAD);
        }
    }
}
