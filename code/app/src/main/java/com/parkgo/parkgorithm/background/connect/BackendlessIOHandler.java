package com.parkgo.parkgorithm.background.connect;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;

import com.backendless.exceptions.BackendlessFault;
import com.parkgo.parkgorithm.navigation.NavigationActivity;
import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.background.bean.AccountBean;
import com.parkgo.parkgorithm.background.bean.CarBean;
import com.parkgo.parkgorithm.background.bean.ContactBean;
import com.parkgo.parkgorithm.background.bean.PreferenceBean;
import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.user.properties.Car;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.properties.Preference;
import com.parkgo.parkgorithm.user.properties.UserScore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Antony Lulciuc on 7/4/2016.
 */
public class BackendlessIOHandler extends BackendlessIO {

    private class AsyncUserLoginCallback implements AsyncCallback<BackendlessUser>{
        private Command m_LoginAction;
        private Object[] m_aData;

        public AsyncUserLoginCallback(Command _LoginAction, Object[] _data){
            m_LoginAction = _LoginAction;
            m_aData       = _data;
        }

        @Override
        public void handleResponse(BackendlessUser _backendlessUser) {
            try {
                handleLogin(_backendlessUser, m_LoginAction, m_aData);
            }catch (Exception err){
                System.out.println(err.getMessage());
                Toast.makeText(m_Context, err.getMessage(), Toast.LENGTH_LONG);
            }
        }

        @Override
        public void handleFault(BackendlessFault backendlessFault) {

        }
    }

    /**
     * Constructor
     * @param _Context [in] instance of application running
     */
    public BackendlessIOHandler(Context _Context){
        super(_Context);
    }

    /**
     *
     * @param _backendlessUser [in] user information
     * @param _gateway [in] method used to log into account
     * @return true if successfully acquired and set user information for processing
     * @throws Exception
     */
    private boolean handleLogin(BackendlessUser _backendlessUser, Command _gateway, Object[] _data)throws Exception{
        String strEmail               = _backendlessUser.getEmail();
        String strUsername            = (String)_backendlessUser.getProperty("user_name");
        String strPreferenceId        = null;
        Preference preference         = null;
        AccountBean accountBean       = null;
        PreferenceBean preferenceBean = null;
        CarBean carBean               = null;
        Car car;
        ContactBean contactBean       = null;
        String strAccountId;

        try{
            // Collect data
            if ((strAccountId = (String)_backendlessUser.getProperty("account_id")) != null) {
                accountBean = Backendless.Persistence.of(AccountBean.class).findById(strAccountId);
                contactBean = Backendless.Persistence.of(ContactBean.class).findById((accountBean.getContactId()));

                if (contactBean.getCarId() != null) {
                    carBean = Backendless.Persistence.of(CarBean.class).findById(contactBean.getCarId());
                    car = new Car(carBean);
                }
                else{
                    car = new Car("none", "none", 0000, new byte[]{0, 0, 0});
                }

                // If user preference set
                if ((strPreferenceId = accountBean.getPreferenceId()) != null) {
                    preferenceBean = Backendless.Persistence.of(PreferenceBean.class).findById(strPreferenceId);
                    preference     = new Preference(preferenceBean);
                }

                // Create User Info object
                m_UserInfo = new Info(_backendlessUser.getObjectId(),
                                      strUsername,
                                      preference,
                                      new UserScore(),
                                      car,
                                      strEmail,
                                      contactBean,
                                      accountBean);

                _data[3] = 1;
                _data[4] = m_UserInfo;

                EditText name  = (EditText) ((AppCompatActivity)m_Context).findViewById(R.id.usernameTextField);

                if (name != null) {
                    name.post(new Runnable() {
                        @Override
                        public void run() {
                            EditText username = (EditText) ((AppCompatActivity) m_Context).findViewById(R.id.usernameTextField);
                            EditText password = (EditText) ((AppCompatActivity) m_Context).findViewById(R.id.passwordTextField);
                            username.setText("");
                            password.setText("");
                        }
                    });
                }
                // Load activity associated with this login
                return (m_mAction.get(_gateway).execute(new Object[]{m_Context, NavigationActivity.class, m_UserInfo}));
            }else{
                return (true);
            }
        } catch (BackendlessException err){
            m_exErrorMessage = err;
            _data[4] = "Error : <code:" + err.getCode() + "><detail:" + err.getDetail() + "><msg:" + err.getMessage() + ">";
            _data[3] = -3;
            throw(new Exception(err.getMessage()));
        }
    }

    /**
     *
     * @param _data [in] user credentials
     * @return
     * @throws Exception
     */
    @Override
    protected boolean loginAccountWithBackendless(Object[] _data)throws Exception{
        try {
            // Login
            return (handleLogin(Backendless.UserService.login((String) _data[1], (String) _data[2], true),
                            Command.LOGIN_ACCOUNT_BACKENDLESS, _data));
        }catch (BackendlessException err) {
            Backendless.UserService.logout();
            m_exErrorMessage = err;
            _data[4] = "Error : <code:" + err.getCode() + "><detail:" + err.getDetail() + "><msg:" + err.getMessage() + ">";
            _data[3] = -2;
            throw (new Exception(err.getMessage()));
        }
    }

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected boolean loginAccountWithTwitter(Object[] _data)throws Exception{
        Map<String, String> twitterMapping = new HashMap<>();
        twitterMapping.put("name", (String)_data[1]);

        try{
            Backendless.UserService.loginWithTwitter((Activity)_data[3], twitterMapping,
                    new AsyncUserLoginCallback(Command.LOGIN_ACCOUNT_TWITTER, _data));
        }catch(BackendlessException err){
            m_exErrorMessage = err;
            System.out.println(err.getMessage());
        }

        return (false);
    }


    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected boolean loginAccountWithGooglePlus(Object[] _data)throws Exception{
        return (false);
    }

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected boolean loginAccountWithFacebook(Object[] _data)throws Exception{
        return (false);
    }


    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    @Override
    protected boolean uploadParkingSpot(Object[] _data)throws Exception{
        // Must have 5 elements
        if (_data.length == 5) {
            return (m_mAction.get(Command.UPLOAD_PARKING_SPOT).execute(new Object[]{m_Context, _data}));
        }
        throw(m_exErrorMessage = new Exception("Error : <upload> : to few arguments!"));
    }

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    @Override
    protected boolean requestParkingSpot(Object[] _data)throws Exception{
        if (_data.length == 3){
            return (m_mAction.get(Command.REQUEST_PARKING_SPOT).execute(new Object[]{m_Context, _data}));
        }
        return (false);
    }

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    @Override
    protected boolean updateAccount(Object[] _data)throws Exception{
        return (false);
    }

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    @Override
    protected boolean deleteAccount(Object[] _data)throws Exception{
        return (false);
    }

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    @Override
    protected boolean createAccount(Object[] _data)throws Exception{
        if (_data.length == 7){
            BackendlessUser user = new BackendlessUser();
            AccountBean accountBean;
            ContactBean contactBean;
            boolean bResult = true;

            user.setEmail((String)_data[1]);
            user.setPassword((String)_data[2]);

            // Attempt to create user
            user = Backendless.UserService.register(user);

            // Create account bean and update user
            if (_data[4] instanceof LinkedList) {
                if (loginAccountWithBackendless(new Object[]{Command.LOGIN_ACCOUNT_BACKENDLESS, user.getEmail(), user.getPassword(), null, null, null})) {
                    accountBean = createAccountBean((LinkedList<String>) _data[4]);
                    user.setProperty("account_id", accountBean.getObjectId());

                    // update user information
                    user = Backendless.UserService.update(user);

                    if (user != null) {
                        if (user.getObjectId() != null) {
                           // bResult = m_mAction.get(Command.CREATE_ACCOUNT).execute(new Object[]{user, _data[4]});
                        }
                    }

                    Backendless.UserService.logout();
                    return (bResult);
                }
            }
        }

        throw(m_exErrorMessage = new Exception("Error : <createaccount> : to few arguments!"));
    }

    /**
     *
     * @param _llAccountData
     * @return
     */
    private AccountBean createAccountBean(LinkedList<String> _llAccountData){
        AccountBean accountBean = new AccountBean();
        CarBean carBean = new CarBean();
        ContactBean contactBean = new ContactBean();

        // account information
        accountBean.setFirstName(_llAccountData.get(2));
        accountBean.setLastName(_llAccountData.get(3));

        contactBean.setUserName(accountBean.getFirstName());
        contactBean.setPhoneNumber(_llAccountData.get(4));
        contactBean.setUserScore(100.0);

        try{
            contactBean = Backendless.Persistence.of(ContactBean.class).save(contactBean);

            if (contactBean != null){
                accountBean.setContactId(contactBean.getObjectId());
                return (Backendless.Persistence.of(AccountBean.class).save(accountBean));
            }
        }catch (BackendlessException err){
            m_exErrorMessage = err;
            System.out.println(err.getMessage() + "<" + err.getCode() + ">");
        }

        return (null);
    }
}




















