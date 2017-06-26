package com.parkgo.parkgorithm.navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.fragment.NavItemMediator;

import java.io.IOException;
import java.util.List;

import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;

/**
 * Created by John
 *
 * Manages Map UI and Events
 */
public abstract class NavigationMap extends NavigationDrawer implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    /**
     * Manages navigation elements
     */
    public static NavItemMediator NavigationItems;

    /**
     * Stores current user location accessible for all
     */
    public static Location CurrentLocation = null;

    /**
     * Marker created for location CurrentLocation
     */
    public static Marker CurrentLocationMarker = null;
    public static Marker DestinationMarker = null;
    protected List<Address> m_lAddress;
    protected Address m_Address;


    /**
     * Handles Map Gesture Events
     */
    private NavGestureHandler NavigationGestureHandler;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Google Classes used for map navigation
     */
    protected GoogleMap m_GoogleMap;
    private GoogleApiClient m_MapApi = null;
    private GoogleApiClient m_LocationServicesApi = null;
    private LocationRequest m_LocationRequest;


    /**
     * Flag used to determine if app follows user location
     * or to ignore.
     */
    private boolean m_bFollow = true;

    /**
     * Create Navigation activity
     * @param _savedInstanceState
     */
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        // Initialize map services
        m_LocationServicesApi.connect();
        m_MapApi.connect();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Navigation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.parkgo.parkgorithm/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Navigation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.parkgo.parkgorithm/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();

        try {
            ParkgoServer.signout(null);
        }catch (Exception err){

        }

        super.onPause();

        // Disable connection
        if (m_MapApi.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(m_MapApi, this);
            m_LocationServicesApi.disconnect();
            m_MapApi.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConnectionSuspended(int _value){
    }

    /**
     * Acquire users current location
     * @param _bundle
     */
    @Override
    public void onConnected(Bundle _bundle){
        try {
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED,  PackageManager.PERMISSION_GRANTED);
            Location location = LocationServices.FusedLocationApi.getLastLocation(m_MapApi);
            m_bFollow = true;
            LocationServices.FusedLocationApi.requestLocationUpdates(m_MapApi, m_LocationRequest, this);
            setLocation(location);
        } catch(Exception SecurityException){

        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult _result){
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings ui = googleMap.getUiSettings();
        m_GoogleMap = googleMap;
        ui.setRotateGesturesEnabled(false);
        ui.setCompassEnabled(false);
        ui.setZoomGesturesEnabled(false);
        ui.setMyLocationButtonEnabled(true);

        NavigationItems.show(NavItemMediator.NAV_ITEM_SEARCH_BAR);
        NavigationItems.show(NavItemMediator.NAV_ITEM_USER_LOCATION);

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO : change dialog to match app
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            return;
        }
    }

    /**
     * Set user location when it has changed
     * @param _location [in] users new location
     */
    @Override
    public void onLocationChanged(Location _location) {
        setLocation(_location);
    }

    /**
     * Process user input of app premissions
     * @param _requestCode [in] request made
     * @param _permissions [in] presmissions requested
     * @param _grantResults [in] user responce
     */
    @Override
    @SuppressWarnings("all")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions.length == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setLocation(null);
        } else {
            // Permission was denied. Display an error message.
        }
    }

    /**
     * Sets where to center map
     * @param _location [in] location to center on
     */
    @SuppressWarnings("all")
    public void setLocation(Location _location) {
        try {
            // If marker already existed then remove
            if (CurrentLocationMarker != null)
                CurrentLocationMarker.remove();

            /**
             * If location provided ws set to null attempt to find
             * with second approach
             */
            if (_location == null) {
                CurrentLocation = LocationServices.FusedLocationApi.getLastLocation(m_MapApi);
                if (CurrentLocation == null) {
                    return;
                }
            } else {
                CurrentLocation = _location;
            }

            // set new location
            LatLng latLng = new LatLng(CurrentLocation.getLatitude(), CurrentLocation.getLongitude());
            CurrentLocationMarker = m_GoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_tag))
                    .title("Your Here"));

            // Only if we are following then move map location
            if (m_bFollow)
                m_GoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
        }catch (Exception err){
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Set destination marker for user
     * @param _search [in] users destination query
     */
    public void setDestination(String _search){
        Geocoder mGeocoder = new Geocoder(this);

        try {
            m_lAddress = mGeocoder.getFromLocationName(_search, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (DestinationMarker != null)
            DestinationMarker.remove();

        //go to first result
        m_Address      = m_lAddress.get(0);
        LatLng mLatLng = new LatLng(m_Address.getLatitude(), m_Address.getLongitude());
        DestinationMarker = m_GoogleMap.addMarker(new MarkerOptions().position(mLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.destination_tag)).title(m_Address.getFeatureName()));
        m_GoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));
    }

    /**
     * Initialization of:
     *      1 - map
     *      2 - google api
     *      3 - google services
     */
    protected void initMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        NavigationItems = new NavItemMediator(this);
        NavigationGestureHandler = new NavGestureHandler(this, null);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        if (m_MapApi == null) {
            m_MapApi = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Initialize google location services
        if (m_LocationServicesApi == null) {
            m_LocationServicesApi = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        /**
         * Create type of requests to be made:
         *      1 - priority
         *      2 - normal frequency
         *      3 - max frequency
         */
        m_LocationRequest = LocationRequest.create()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10 * 1000)         // 10 seconds, in milliseconds
                                .setFastestInterval(1 * 1000);
    }

    /**
     * Sets value for follow flag. Determines if:
     *      1 - true  -> map centers on users location
     *      2 - false -> moves map according to Gestures or searched location
     * @param _bFollow
     */
    public void setFollowing(boolean _bFollow){
        m_bFollow = _bFollow;
    }
}
