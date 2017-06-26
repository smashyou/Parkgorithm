package com.parkgo.parkgorithm.background;

import android.app.Activity;
import android.content.Context;

import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.exception.AccountException;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.User;
import com.parkgo.parkgorithm.background.parking.ParkingSpot;


/**
 * Created by Antony on 6/26/2016.
 */
public interface Parkgorithm {

    void setContext(Context _Context);

    /**
     * Add a new user (must not exist in database).
     * @param _acc [in] new user account information
     * @return true if the user was added. IF the user already existed in the
     *         database then false.
     * @throws AccountException IF information in account was missing or invalid
     */
    boolean addUser(Info _acc)throws AccountException;

    /**
     * Update user account information
     * @param _user [in] old user data
     * @param _newInfo [in] new information used to update account
     * @return return true upon ssuccess else false or trows exception
     * @throws AccountException
     */
    boolean updateUser(User _user, Info _newInfo)throws AccountException;

    /**
     * Returning user needs to enter credentials in order to proceed to application
     * Returns a immutable object.
     * @param _strUserName [in] their user name
     * @param _strPassword [in] their password
     * @throws AccountException IF user was not able to login to their account, whether it
     *                      be invalid username or password.
     */
    void login(String _strUserName, String _strPassword)throws AccountException;

    /**
     * Log into Account using popular  Social media outlets.
     * @param _strUserName [in] user name
     * @param _strPassword [in] user password
     * @param _strSocialType [in] name of social media
     * @param _Activity [in] activity associated with login
     * @throws AccountException IF information provided was invalid or data corrupted
     */
    void login(String _strUserName, String _strPassword, String _strSocialType, Activity _Activity)throws AccountException;

    /**
     * Closes user account
     * @param _user [in] signs out user from application
     * @return true upon success else false
     * @throws Exception if there is an issue with the connection
     */
    boolean signout(User _user)throws Exception;

    /**
     * Handles user parking spot requests
     * @param _user [in] user who is requesting parking spot
     * @return If successful, it will return a parking spot, else null
     * @throws Exception IF there was an issue loading information which would determine
     *                      if the location was a good match.
     *                   IF there was an issue validating the user
     */
    ParkingSpot requestParkingSpot(User _user)throws AccountException;

    /**
     * Handles parking spot submissions
     * @param _user [in] user who is submitting new parking spot.
     * @return true if spot was successfully added. If the spot already existed then false.
     * @throws Exception IF there was an issue validating the user
     *                   IF there was an issue processing the new spot, or if essential
     *                      information about the parking spot was missing.
     */
    boolean submitParkingSpot(User _user)throws AccountException;


    /**
     * Connect to server
     * @return true if successfully connected to server else false or throws exception
     * @throws Exception
     */
    boolean connect()throws Exception;

    /**
     * Disconnect from server
     */
    void disconnect();

    void setInstruction(Command _Command, Instruction _Instruction);

    Info getUserInfo();

    Context getContext();

    /**
     * Returning user needs to enter credentials in order to proceed to application
     * Returns a immutable object.
     * @param _strUserName [in] their user name
     * @param _strPassword [in] their password
     * @param _extra [in] user data
     * @throws AccountException IF user was not able to login to their account, whether it
     *                      be invalid username or password.
     */
    void createAccount(String _strUserName, String _strPassword, Object _extra)throws AccountException;

    /**
     * Log into Account using popular  Social media outlets.
     * @param _strUserName [in] user name
     * @param _strPassword [in] user password
     * @param _strSocialType [in] name of social media
     * @param _extra [in] user data
     * @param _Activity [in] activity associated with login
     * @throws AccountException IF information provided was invalid or data corrupted
     */
    void createAccount(String _strUserName, String _strPassword, String _strSocialType, Object _extra, Activity _Activity)throws AccountException;

    boolean isWorking();

    /**
     *
     * @param _Command
     * @return
     */
    boolean wasSuccessful(Command _Command);

    Exception getError();

}
