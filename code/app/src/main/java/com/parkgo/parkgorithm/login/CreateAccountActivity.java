package com.parkgo.parkgorithm.login;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.CreateAccountProcedure;
import com.parkgo.parkgorithm.background.instruction.client.HandleNewAccountInfo;
import com.parkgo.parkgorithm.runnable.RunnableAction;

import java.util.LinkedList;

import static com.parkgo.parkgorithm.login.SplashActivity.ParkgoServer;

public class CreateAccountActivity extends AppCompatActivity {
    private LinkedList<String> m_llAccountInfo;
    private Dialog m_dDialogError;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateAccount Page", // TODO: Define a title for the content shown.
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
                "CreateAccount Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.parkgo.parkgorithm/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public void submitNewAccount(View view){
        Instruction validate = new HandleNewAccountInfo();
        LinkedList<Integer> llIds = new LinkedList<>();
        Object[] data;

        m_llAccountInfo = new LinkedList<>();
        llIds.add(R.id.new_email_txt);
        llIds.add(R.id.new_email_lbl);
        llIds.add(R.id.new_retype_email_txt);
        llIds.add(R.id.new_retype_email_lbl);
        llIds.add(R.id.new_first_name_txt);
        llIds.add(R.id.new_first_name_lbl);
        llIds.add(R.id.new_last_name_txt);
        llIds.add(R.id.new_last_name_lbl);
        llIds.add(R.id.new_phone_number_txt);
        llIds.add(R.id.new_phone_number_lbl);
        llIds.add(R.id.new_password_txt);
        llIds.add(R.id.new_password_lbl);
        llIds.add(R.id.new_retype_pw_txt);
        llIds.add(R.id.new_retype_pw_lbl);

        data = new Object[]{this, m_llAccountInfo, llIds, null};

        try {
            // If No error occurred
            if (validate.execute(data)) {
                createAccount();
            } else {
                switch((Integer)data[3]){
                    case 1:
                        Toast.makeText(this, "Invalid input!", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(this, "Red fields are required!", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(this, "Fields do not match!", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(this, "Unknown error!", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception err){
            // DO NOTHING
        }

    }

    /**
     * PRIVATE : methods
     */

    /**
     * Create new account for user
     */
    @SuppressWarnings("empty-statement")
    private void createAccount() {
        RunnableAction runnableAction = new RunnableAction(new CreateAccountProcedure(), new Object[]{this, ParkgoServer, m_llAccountInfo});
        Thread action = new Thread(runnableAction);
        action.start();
    }

}
