package com.parkgo.parkgorithm.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.background.exception.AccountException;
import com.parkgo.parkgorithm.user.properties.Info;

import java.io.FileOutputStream;

import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;


public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.parkgo.parkgorithm.MESSAGE";
    public static String CREATE_MESSAGE = "";
    private boolean m_bSetEmail = false;
    private boolean m_bSetPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initDesign();
        setContentView(R.layout.activity_login);

        ParkgoServer.setContext(this);

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO : change dialog to match app
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            return;
        }
    }

    public void presentSignedInPage(View view) {
        // Load Navigation Activity
        Info userInfo;
        Intent intent;
        EditText username = (EditText) findViewById(R.id.usernameTextField);
        EditText password = (EditText) findViewById(R.id.passwordTextField);
        StringBuilder cred = new StringBuilder("Username: ");
        String name;
        String pw;
        // Load user from database
        try {
            ParkgoServer.login(name = username.getText().toString().trim(), pw =password.getText().toString().trim());
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
                outputStream.write(name.getBytes());
                outputStream.write('\n');
                outputStream.write(pw.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (AccountException err){
            // TODO : handle exception
            // TODO : display error message
            System.out.println(err.getMessage() + "<code:" + err.getErrorCode() + ">");
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void presentSocialMediaSignIn(View _view){
        //SocialMediaBox smb = new SocialMediaBox(this);
       // smb.show();
    }

    private void initDesign() {
        EditText username                = (EditText) findViewById(R.id.usernameTextField);
        EditText password                = (EditText) findViewById(R.id.passwordTextField);
        Button button                    = (Button) findViewById(R.id.signInButton);
        ViewGroup.LayoutParams lParam    = null;
        WindowManager wm                 = getWindowManager();
        Display display                  = wm.getDefaultDisplay();
        Point size                       = new Point();
        int width                        = 0;

        display.getSize(size);
        width           = ((int)(((double)size.x) * 0.85));

        // Sign in button
        if (button != null) {
            if ((lParam = button.getLayoutParams()) != null) {
                lParam.width = width;
                button.setLayoutParams(lParam);
            }
        }

        // Social media button
        if ((button = (Button) findViewById(R.id.login_google)) != null){
            if ((lParam = button.getLayoutParams()) != null) {
                lParam.width = width / 2 - width / 4;
                button.setLayoutParams(lParam);
            }
        }

        // Social media button
        if ((button = (Button) findViewById(R.id.login_facebook)) != null){
            if ((lParam = button.getLayoutParams()) != null) {
                lParam.width = width / 2 - width / 4;
                button.setLayoutParams(lParam);
            }
        }

        // Create Account button
        if ((button = (Button) findViewById(R.id.createNewAccount)) != null){
            if ((lParam = button.getLayoutParams()) != null) {
                lParam.width = width;
                button.setLayoutParams(lParam);
            }
        }

        // User name input
        if (username != null){
            if ((lParam = username.getLayoutParams()) != null) {
                lParam.width = width;
                username.setLayoutParams(lParam);
            }
        }

        // Password name input
        if (password != null){
            if ((lParam = password.getLayoutParams()) != null) {
                lParam.width = width;
                password.setLayoutParams(lParam);
            }
        }
    }

    /**
     *
     * @param view
     */
    public void presentCreateAccountPage(View view) {
        Intent createIntent;
        createIntent = new Intent(this, CreateAccountActivity.class);
        startActivity(createIntent);
    }

    public void onEmailClick(View _view){
        if (!m_bSetEmail) {
            TextView tv = (TextView) _view;
            tv.setText("");
            m_bSetEmail = true;
        }
    }

    public void onPasswordClick(View _view){
        if (!m_bSetPassword) {
            TextView tv = (TextView) _view;
            tv.setText("");
            tv.setTransformationMethod(PasswordTransformationMethod.getInstance());
            m_bSetPassword = true;
        }
    }

}
