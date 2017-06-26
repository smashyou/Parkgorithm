package com.parkgo.parkgorithm.navigation;

import android.location.Address;
import android.location.Geocoder;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.background.base.Point;
import com.parkgo.parkgorithm.background.parking.ParkingSpot;
import com.parkgo.parkgorithm.user.Buyer;
import com.parkgo.parkgorithm.user.Seller;
import com.parkgo.parkgorithm.user.User;
import com.parkgo.parkgorithm.user.properties.Info;
import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;

import java.io.IOException;
import java.util.List;


public class NavigationActivity extends NavigationMap {
    private EditText m_SearchText;

    /**
     * Created by John
     * Create Navigation activity
     * @param _savedInstanceState
     */
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        this.init();
    }

    private void init(){
        try {
            ParkgoServer.setContext(this);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_city_nav);

            // initialize google map
            this.initMap();
        }catch (Exception err){
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void searchForSpaces() {
        String search;

        m_SearchText = (EditText) findViewById(R.id.nav_search_edit_text);
        search       = m_SearchText.getText().toString();

        if (!search.equals("")) {
            this.setDestination(search);

            // Search for parking spot
            try {
                // TODO : process request

                /*
                Info info = ParkgoServer.getUserInfo();
                User user;

                info.setLocation(new Point<>(CurrentLocation.getLongitude(), CurrentLocation.getLatitude()));
                user = new Buyer(info, new ParkingSpot(new Point<>(m_Address.getLatitude(), m_Address.getLongitude()), 0.0));

                ParkgoServer.requestParkingSpot(user);
                */
            } catch (Exception err) {
                // display errpr message
            }

        }
    }

    public void searchForSpaceByLocation(View view) {
        //TODO code to search for space on server - currently loads a hard coded array to populate list in search activity
//        Intent intent = new Intent(this, SearchActivity.class);
//        startActivity(intent);
//        mDrawerLayout.closeDrawer(mDrawerLinear);
//        searchText.requestFocus();
        try {
            Toast.makeText(this, "Close drawer and search from map screen", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
