package com.parkgo.parkgorithm.login;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.parkgo.parkgorithm.background.Parkgorithm;
import com.parkgo.parkgorithm.background.ParkgorithmServer;
import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.draw.AnimationPlayer;
import com.parkgo.parkgorithm.navigation.NavigationActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {
    private ImageView m_ImageView;
    private AnimationPlayer m_AnimationPlayer;
    private Boolean m_bCreateAccount = true;
    public static final Parkgorithm ParkgoServer = new ParkgorithmServer();
    public static final Point ScreenSize = new Point();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Run Parkgorithm animation load screen
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                ParkgoServer.setContext(SplashActivity.this);

                try {
                    ParkgoServer.connect();

                    FileInputStream fis = openFileInput("data.txt");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                    String userName = bufferedReader.readLine();
                    String password;

                    if (userName != null) {
                        m_bCreateAccount = false;
                        password = bufferedReader.readLine();
                        ParkgoServer.login(userName, password);
                    }

                    bufferedReader.close();
                    fis.close();
                } catch (Exception err){
                    System.out.println(err.getMessage());
                }

                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        WindowManager wm = SplashActivity.this.getWindowManager();
                        Display display = wm.getDefaultDisplay();
                        ViewGroup.LayoutParams lParam = null;
                        Point size     = new Point();
                        int width;

                        display.getSize(size);
                        ScreenSize.x = size.x;
                        ScreenSize.y = size.y;
                        width = ((int) (((double) size.x) * 0.85));
                        m_ImageView = (ImageView) findViewById(R.id.splashView);
                        lParam = m_ImageView.getLayoutParams();
                        lParam.width = width;
                        lParam.height = width;
                        m_ImageView.setLayoutParams(lParam);
                        m_ImageView.setBackgroundResource(R.drawable.splash_animation);

                        // Get the background, which has been compiled to an AnimationDrawable object.
                        m_AnimationPlayer = new AnimationPlayer((AnimationDrawable) m_ImageView.getBackground()) {
                            @Override
                            public void onAnimationFinish() {
                                Intent intent;

                                if (m_bCreateAccount)
                                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                                else
                                    intent = new Intent(SplashActivity.this, NavigationActivity.class);

                                startActivity(intent);
                                finish();
                            }
                        };

                        // Start the animation (looped playback by default).
                        m_AnimationPlayer.start();
                    }
                });
            }
        }).start();
    }

}
