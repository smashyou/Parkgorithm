package com.parkgo.parkgorithm.background.connect;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.instruction.client.ToastInstruction;
import com.parkgo.parkgorithm.runnable.RunnableAction;
import com.parkgo.parkgorithm.user.properties.Info;

/**
 * Created by Antony on 6/26/2016.
 */
public abstract class BackendlessIO extends BackendlessConnector{
    protected Info m_UserInfo = null;

    /**
     * Constructor
     * @param _Context [in] application context
     */
    public BackendlessIO(Context _Context){
        super(_Context);
    }

    /**
     * Write to server
     * @param _data [in] what to write
     * @return true on success else false
     * @throws Exception
     */
    @Override
    public boolean write(Object _data)throws Exception{
        Object[] data   = (Object[])_data;
        Command cmd     = (Command)data[0];

        try {
            // Write methods
            switch (cmd) {
                case CREATE_ACCOUNT:
                    return (createAccount(data));
                case UPDATE_ACCOUNT:
                    return (updateAccount(data));
                case UPLOAD_PARKING_SPOT:
                    return (uploadParkingSpot(data));
                case DELETE_ACCOUNT:
                    return (deleteAccount(data));
                default:
                    return (false);
            }
        }catch (Exception err){
            m_exErrorMessage = err;
            throw (err);
        }
    }

    /**
     * Read from server
     * @param _data [in] what to store data into
     * @return true on success else throws exception.
     * @throws Exception
     */
    @Override
    public boolean read(Object _data)throws Exception {
        Object[] data = (Object[]) _data;
        Command cmd = (Command) data[0];

        try {
            switch (cmd) {
                case LOGIN_ACCOUNT_BACKENDLESS:
                    return (loginAccountWithBackendless(data));
                case LOGIN_ACCOUNT_FACEBOOK:
                    return (loginAccountWithFacebook(data));
                case LOGIN_ACCOUNT_TWITTER:
                    return (loginAccountWithTwitter(data));
                case LOGIN_ACCOUNT_GOOGLE_PLUS:
                    return (loginAccountWithGooglePlus(data));
                case REQUEST_PARKING_SPOT:
                    return (requestParkingSpot(data));
                default:
                    return (false);
            }
        } catch (Exception err) {
            m_exErrorMessage = err;
            Looper looper = m_Context.getMainLooper();
            Handler handler = new Handler(looper);
            handler.post(new RunnableAction(new ToastInstruction(), new Object[]{m_Context, err.getMessage(), Toast.LENGTH_LONG}));
            throw (err);
        }
    }

    /**
     * PROTECTED : Abstract Methods
     */

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected abstract boolean loginAccountWithBackendless(Object[] _data)throws Exception;

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected abstract boolean loginAccountWithTwitter(Object[] _data)throws Exception;

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected abstract boolean loginAccountWithGooglePlus(Object[] _data)throws Exception;

    /**
     * Login user
     * @param _data [in] user credentials
     * @return true on success else false
     * @throws Exception IF error processing account
     */
    protected abstract boolean loginAccountWithFacebook(Object[] _data)throws Exception;


    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    protected abstract boolean uploadParkingSpot(Object[] _data)throws Exception;

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    protected abstract boolean requestParkingSpot(Object[] _data)throws Exception;

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    protected abstract boolean updateAccount(Object[] _data)throws Exception;

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    protected abstract boolean deleteAccount(Object[] _data)throws Exception;

    /**
     *
     * @param _data
     * @return
     * @throws Exception
     */
    protected abstract boolean createAccount(Object[] _data)throws Exception;
}
