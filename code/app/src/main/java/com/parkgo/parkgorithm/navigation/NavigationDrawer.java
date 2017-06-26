package com.parkgo.parkgorithm.navigation;

import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parkgo.parkgorithm.R;
import static com.parkgo.parkgorithm.login.SplashActivity.ScreenSize;

/**
 * Created by John
 */
public abstract class NavigationDrawer extends FragmentActivity implements DrawerLayout.DrawerListener {
    private DrawerLayout m_DrawerLayout;
    private LinearLayout m_llDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        m_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        m_llDrawer = (LinearLayout) findViewById(R.id.left_drawer);
        setLeftDrawer();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }

    /**
     * DESIGN
     * Set drawer dimensions based on screen dimensions
     */
    private void setLeftDrawer() {
        View leftView = findViewById(R.id.left_drawer);
        ViewGroup.LayoutParams lParam;
        View view;
        int width;
        int height;

        // Left Drawer parameters
        lParam        = leftView.getLayoutParams();
        width         = (int) ((double) ScreenSize.x * 0.65);
        lParam.width  = width;
        lParam.height = ScreenSize.y;
        leftView.setLayoutParams(lParam);

        // Profile Height
        height = (ScreenSize.y - width) / 6;

        // Profile info layout
        view   = leftView.findViewById(R.id.profile_layout);
        lParam = view.getLayoutParams();
        lParam.height = (int) ((double) height * 1.25);
        view.setLayoutParams(lParam);
        view.setPadding(12, 0, 0, 0);

        // Profile picture height
        view   = leftView.findViewById(R.id.profile_picture);
        lParam = view.getLayoutParams();
        lParam.width  = height;
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Set button height
        height = (int) ((double) ScreenSize.y / 8.75);

        // Payment
        view          = leftView.findViewById(R.id.payment_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // History
        view          = leftView.findViewById(R.id.history_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);


        // Preferences
        view          = leftView.findViewById(R.id.preference_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Rewards
        view          = leftView.findViewById(R.id.rewards_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Account
        view          = leftView.findViewById(R.id.account_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Help
        view          = leftView.findViewById(R.id.help_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Settings
        view          = leftView.findViewById(R.id.settings_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);

        // Sign out
        view          = leftView.findViewById(R.id.sign_out_button);
        lParam        = view.getLayoutParams();
        lParam.height = height;
        view.setLayoutParams(lParam);
    }

    // EVENTS

    /**
     * Method opens left DrawerLayout
     * @param _view [in] view associated with specified event
     */
    public void presentListActivity(View _view) {
        m_DrawerLayout.openDrawer(Gravity.LEFT);
    }

    /**
     * Opens Settings Activity with Rewards information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentPaymentActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with Payment information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity with History information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentHistoryActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with History information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity with Preference information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentPreferenceActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with Preference information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity with Rewards information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentRewardsActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with Rewards information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity with Account information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentAccountActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with Account information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity with Help information being displayed
     * @param _view [in] view associated with specified event
     */
    public void presentHelpActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity with Help information displayed
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Settings Activity
     * @param _view [in] view associated with specified event
     */
    public void presentSettingsActivity(View _view) {
        try {
            // TODO : Create SettingsActivity
            // TODO : Initialize SettingsActivity
            Toast.makeText(this, "<not_complete>", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Signs user out of application and returns them to login screen
     * @param _view [in] view associated with specified event
     */
    public void signOutApp(View _view) {
        try {
            deleteFile("data.txt");
            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
