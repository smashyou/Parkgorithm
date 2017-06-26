package com.parkgo.parkgorithm.background;

import android.app.Activity;
import android.content.Context;

import com.backendless.Backendless;
import com.parkgo.parkgorithm.background.connect.runnable.ServerIOHandler;
import com.parkgo.parkgorithm.background.connect.BackendlessIOHandler;
import com.parkgo.parkgorithm.background.connect.Connect;
import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.exception.AccountException;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.parking.ParkingHours;
import com.parkgo.parkgorithm.background.parking.ParkingSpot;
import com.parkgo.parkgorithm.user.Buyer;
import com.parkgo.parkgorithm.user.Seller;
import com.parkgo.parkgorithm.user.User;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.background.bean.AddressBean;
import com.parkgo.parkgorithm.background.bean.ParkinghoursBean;
import com.parkgo.parkgorithm.background.bean.ParkingspotBean;

/**
 * Created by Antony on 6/26/2016.
 */
public class ParkgorithmServer implements Parkgorithm{
    /**
     * Defines how to connect to server
     */
    private Connect m_BackendlessConnection;
    private Thread m_thLogin;
    private Thread m_thProcess;
    private ServerIOHandler m_ConnectionRead  = null;
    private ServerIOHandler m_ConnectionWrite = null;
    private Context m_Context = null;

    /**
     * This is a reference and access code to use the database ONLY
     * Does NOT send requests to server for processing.
     */
    private static final String m_strBackendlessConnection = "AB92601F-5BB9-F0A9-FF54-031EFC017A00" +
                                                             "&9A7CEE54-79CF-8F4D-FFAF-B56F61989600" +
                                                             "&v1";

    /**
     * Constructor
     */
    public ParkgorithmServer(){
        // Initialize Backendless
        m_BackendlessConnection = null;
    }

    /**
     * Constructor
     * @param _Context [in] device context
     */
    public ParkgorithmServer(Context _Context){
        // Initialize Backendless
        m_BackendlessConnection = new BackendlessIOHandler(_Context);
    }

    public void setContext(Context _Context){
        // Initialize Backendless
        if (m_BackendlessConnection == null)
            m_BackendlessConnection = new BackendlessIOHandler(_Context);
        m_Context = _Context;
    }

    /**
     * Add a new user (must not exist in database).
     * @param _acc [in] new user account information
     * @return true if the user was added. IF the user already existed in the
     *         database then false.
     * @throws AccountException IF information in account was missing or invalid
     */
    @Override
    public boolean addUser(Info _acc)throws AccountException {
        // TODO : add code to add user to database
        // TODO : handle account exception
        return (false);
    }

    /**
     * Update user account information
     * @param _user [in] old user data
     * @param _NewInfo [in] new information used to update account
     * @return return true upon success else false or throws exception
     * @throws AccountException
     */
    @Override
    public boolean updateUser(User _user, Info _NewInfo)throws AccountException{
        // TODO : add code to update backendless server
        // TODO : handle account exceptions
        return (false);
    }

    /**
     * Returning user needs to enter credentials in order to proceed to application
     * Returns a immutable object.
     * @param _strUserName [in] their user name
     * @param _strPassword [in] their password
     * @throws AccountException IF user was not able to login to their account, whether it
     *                      be invalid username or password.
     */
    @Override
    public void login(String _strUserName, String _strPassword)throws AccountException{
        login(_strUserName, _strPassword, "backendless", null);
    }

    /**
     * Login to Account using popular  Social media outlets.
     * @param _strUserName [in] user name
     * @param _strPassword [in] user password
     * @param _strSocialType [in] name of social media
     * @param _Activity [in] activity associated with login
     * @throws AccountException IF information provided was invalid or data corrupted
     */
    public void login(String _strUserName, String _strPassword, String _strSocialType, Activity _Activity)throws AccountException{
        Object[] data = null;
        Info info     = null;
        Command gateway;

        // Ensure that username passed is not null
        if (_strUserName == null || _strUserName.length() < 1){
            throw(new AccountException("Must enter account name!", AccountException.ARGUMENT_PASSED_IS_NULL));
        }

        // Ensure that password passed is not null
        if (_strPassword == null || _strPassword.length() < 1){
            throw(new AccountException("Must enter in a password!", AccountException.ARGUMENT_PASSED_IS_NULL));
        }

        // Determine how to login to account
        switch (_strSocialType){
            case "backendless":
                gateway = Command.LOGIN_ACCOUNT_BACKENDLESS;
                break;
            case "twitter":
                gateway = Command.LOGIN_ACCOUNT_TWITTER;
                break;
            case "google+":
                gateway = Command.LOGIN_ACCOUNT_GOOGLE_PLUS;
                break;
            case "facebook":
                gateway = Command.LOGIN_ACCOUNT_FACEBOOK;
                break;
            default:
                throw(new AccountException("Error : <" + _strSocialType + "> is unknown media credential validator!",
                                           AccountException.ARGUMENT_PASSED_IS_INVALID));
        }

        // Set data to process
        data = new Object[]{gateway, _strUserName, _strPassword, _Activity, null, null};
        m_ConnectionRead = new ServerIOHandler(m_BackendlessConnection, data);

        // initialize worker thread
        m_thLogin = new Thread(m_ConnectionRead);

        // Begin attempt login thread
        m_thLogin.start();
    }


    /**
     * Closes user account
     * @param _user [in] signs out user from application
     * @return true upon success else false
     * @throws Exception if there is an issue with the connection
     */
    @Override
    public boolean signout(User _user)throws Exception{
        // TODO : add code to sign off user
        // TODO : handle exceptions
        try {
            if (Backendless.UserService.isValidLogin() == true) {
                Backendless.UserService.logout();
                return (true);
            }
        }catch (Exception err){
            // do nothing
        }

        return (false);
    }

    /**
     * Handles user parking spot requests
     * @param _user [in] user who is requesting parking spot
     * @return If successful, it will return a parking spot, else null
     * @throws AccountException IF there was an issue loading information which would determine
     *                      if the location was a good match.
     *                   IF there was an issue validating the user
     */
    @Override
    public ParkingSpot requestParkingSpot(User _user)throws AccountException{
        // TODO : handle parking spot requests

        // If buyer only
        if (_user instanceof Buyer){
            Buyer buyer = (Buyer)_user;
            ParkingSpot parkingspot         = buyer.getRequestParkingspot();
            ParkingspotBean parkingspotBean = (ParkingspotBean)parkingspot.newBeanInstance();
            AddressBean addressBean         = parkingspot.getAddressBean();
            Object[] request = new Object[]{Command.REQUEST_PARKING_SPOT,
                                            buyer,
                                            addressBean};

            // runnable read instance
            m_ConnectionRead = new ServerIOHandler(m_BackendlessConnection, request);

            // If another thread existed
            if (m_thProcess != null) {
                try {
                    m_thProcess.join();
                }catch (Exception err){
                    // DO NOTHING
                }
            }

            m_thProcess = new Thread(m_ConnectionRead);
            m_thProcess.start();
        }

        return (null);
    }

    public Context getContext(){
        return (m_Context);
    }

    /**
     * Handles parking spot submissions
     * @param _user [in] user who is submitting new parking spot.
     * @return true if spot was successfully added. If the spot already existed then false.
     * @throws AccountException IF there was an issue validating the user
     *                   IF there was an issue processing the new spot, or if essential
     *                      information about the parking spot was missing.
     */
    @Override
    public boolean submitParkingSpot(User _user)throws AccountException{
        // If seller only
        if (_user instanceof Seller) {
            Seller seller = (Seller)_user;
            ParkingSpot parkingspot           = seller.getHostedParkingspot();
            ParkingspotBean parkingspotBean   = (ParkingspotBean)parkingspot.newBeanInstance();
            AddressBean addressBean           = parkingspot.getAddressBean();
            ParkingHours parkingHours         = parkingspot.getParkingHours();
            ParkinghoursBean parkinghoursBean = (ParkinghoursBean)parkingHours.newBeanInstance();
            Object[] submit                   = new Object[]{Command.UPLOAD_PARKING_SPOT,
                                                             seller,
                                                             parkingspotBean,
                                                             addressBean,
                                                             parkinghoursBean};

            // Intialize Runnable instance of server io Handler
            m_ConnectionWrite = new ServerIOHandler(m_BackendlessConnection, submit);

            // If another thread existed
            if (m_thProcess != null) {
                try {
                    m_thProcess.join();
                }catch (Exception err){
                    // DO NOTHING
                }
            }

            m_thProcess = new Thread(m_ConnectionWrite);
            m_thProcess.start();
            return (true);
        }
        return (false);
    }

    /**
     * Connect to Google and Backendless servers
     * @return true if successfully connected to server else false or throws exception
     * @throws Exception
     */
    @Override
    public boolean connect()throws Exception{
        m_BackendlessConnection.connect(m_strBackendlessConnection);
        return (true);
    }

    /**
     * Disconnect from Google and Backendless servers
     */
    @Override
    public void disconnect() {
        m_BackendlessConnection.close();
    }

    /**
     * Acquires directions from location requested, to desired address.
     * @param _user [in] user making the request.
     * @param _address [in] address of desired location
     * @return directions to location from location requested, else null.
     */
    public Object getDirections(User _user, String _address){
        // TODO : change return type to type specified by google
        // TODO : add code to request directions from google maps api
        // TODO : handle exceptions
        return (null);
    }

    /**
     *
     * @param _Command
     * @param _Instruction
     */
    @Override
    public void setInstruction(Command _Command, Instruction _Instruction){
        m_BackendlessConnection.setInstruction(_Command, _Instruction);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public Info getUserInfo(){
        if (m_ConnectionRead != null) {
            while (m_ConnectionRead.isRunning());

            if (((Integer) m_ConnectionRead.get(3)) == 1){
                return ((Info)m_ConnectionRead.get(4));
            }
        }

        return (null);
    }

    /**
     * User who wants to create an account can enter username and pw and they will be transferred to
     * the next activity.
     * Returns a immutable object.
     * @param _strUserName [in] their user name
     * @param _strPassword [in] their password
     * @param _extra [in] user data
     * @throws AccountException IF user was not able to login to their account, whether it
     *                      be invalid username or password.
     */
    @Override
    public void createAccount(String _strUserName, String _strPassword, Object _extra)throws AccountException{
        createAccount(_strUserName, _strPassword, "backendless", _extra, null);
    }

    /**
     * Presents activity allowing user to create an account.
     * @param _strUserName [in] user name
     * @param _strPassword [in] user password
     * @param _strSocialType [in] name of social media
     * @param _extra [in] user data
     * @param _Activity [in] activity associated with login
     * @throws AccountException IF information provided was invalid or data corrupted
     */
    public void createAccount(String _strUserName, String _strPassword, String _strSocialType, Object _extra, Activity _Activity)throws AccountException{
        Object[] data = null;
        Info info     = null;
        Command gateway;

        // Ensure that username passed is not null
        if (_strUserName == null){
            _strUserName = "";
        }

        // Ensure that password passed is not null
        if (_strPassword == null){
            _strPassword = "";
        }

        // Determine how to create account
        switch (_strSocialType){
            case "backendless":
                gateway = Command.CREATE_ACCOUNT;
                break;
            default:
                throw(new AccountException("Error : <" + _strSocialType + "> is unknown media credential validator!",
                        AccountException.ARGUMENT_PASSED_IS_INVALID));
        }

        // Set data to process
        data = new Object[]{gateway, _strUserName, _strPassword, _Activity, _extra, null, null};
        m_ConnectionWrite = new ServerIOHandler(m_BackendlessConnection, data);

        // initialize worker thread
        m_thLogin = new Thread(m_ConnectionWrite);

        // Begin attempt login thread
        m_thLogin.start();
    }

    @Override
    public boolean isWorking(){
        boolean bWorking = false;

        if (m_ConnectionRead != null){
            bWorking = m_ConnectionRead.isRunning();
        }

        if (bWorking == false && m_ConnectionWrite != null){
            return (m_ConnectionWrite.isRunning());
        }

        return (bWorking);
    }

    /**
     * Determine if request was successfull or not
     * @param _Command
     * @return
     */
    @Override
    public boolean wasSuccessful(Command _Command){
        switch (_Command) {
            case LOGIN_ACCOUNT:
            case LOGIN_ACCOUNT_BACKENDLESS:
            case LOGIN_ACCOUNT_FACEBOOK:
            case LOGIN_ACCOUNT_GOOGLE_PLUS:
            case LOGIN_ACCOUNT_TWITTER:
            case REQUEST_PARKING_SPOT:
                return (m_ConnectionRead == null ? false : m_ConnectionRead.wasSuccessful());
            case CREATE_ACCOUNT:
            case UPDATE_ACCOUNT:
            case UPLOAD_PARKING_SPOT:
            case DELETE_ACCOUNT:
                return (m_ConnectionWrite == null ? false : m_ConnectionWrite.wasSuccessful());
            default:
                return (false);
        }
    }

    @Override
    public Exception getError(){
        return (m_BackendlessConnection.getError());
    }
}
