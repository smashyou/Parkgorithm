package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by John
 */
public class NavForeignClient extends NavItem {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        //m_vInflated = inflater.inflate(R.layout.content_nav_item_state, null);
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        /*View v = m_vInflated.findViewById(R.id.nav_item_host_button);
        ViewGroup.LayoutParams lParam = v.getLayoutParams();
        int width = ScreenSize.x / 3;
        lParam.width = width;

        v.setLayoutParams(lParam);
        v = m_vInflated.findViewById(R.id.nav_item_search_button);
        v.setLayoutParams(lParam);
        v = m_vInflated.findViewById(R.id.nav_item_post_button);
        v.setLayoutParams(lParam);*/
    }

}
