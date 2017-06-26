package com.parkgo.parkgorithm.navigation.fragment.frag;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.user.User;

import java.util.List;

import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;
import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;
import static com.parkgo.parkgorithm.navigation.NavigationActivity.CurrentLocation;

/**
 * Created by John
 */
public class NavUpload extends NavItem {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        try {
            m_vInflated = inflater.inflate(R.layout.content_nav_item_upload, null);
        }
        catch (Exception err ){
            System.out.println(err.getMessage());
        }
        return m_vInflated;
    }

    @Override
    public void setDesign() {
        View v = m_vInflated.findViewById(R.id.nav_item_upload_layout);
        ViewGroup.LayoutParams lParam = v.getLayoutParams();
        TextView textView;
        int width = ScreenSize.x - ScreenSize.x / 4;
        lParam.width = width;
        lParam.height = ScreenSize.y - ScreenSize.y / 4;

        v.setLayoutParams(lParam);
        v.setPadding(0, ScreenSize.y / 5, 0, 0);


        v = m_vInflated.findViewById(R.id.nav_upload_price);
        lParam = v.getLayoutParams();
        lParam.width = width;
        lParam.height = ScreenSize.y / 6;
        v.setLayoutParams(lParam);

        v = m_vInflated.findViewById(R.id.nav_upload_price_symbol);
        v.setPadding(width / 3, 0, 0, 0);

        v = m_vInflated.findViewById(R.id.nav_host_button);
        lParam = v.getLayoutParams();
        lParam.width = width / 2;
        v.setLayoutParams(lParam);
        v = m_vInflated.findViewById(R.id.nav_post_button);
        v.setLayoutParams(lParam);

        textView = (TextView)m_vInflated.findViewById(R.id.nav_upload_location_address);
        Geocoder geocoder = new Geocoder(ParkgoServer.getContext());
        List<Address> addresses;
        User user;

        try {
            addresses = geocoder.getFromLocation(CurrentLocation.getLatitude(), CurrentLocation.getLongitude(), 1);
            textView.setText(addresses.get(0).getAddressLine(0));
        }catch (Exception err){
            // do nothing
        }
    }

}
