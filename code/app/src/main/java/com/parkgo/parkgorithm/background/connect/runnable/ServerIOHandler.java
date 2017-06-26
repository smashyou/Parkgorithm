package com.parkgo.parkgorithm.background.connect.runnable;

import com.parkgo.parkgorithm.background.connect.Connect;
import com.parkgo.parkgorithm.background.connect.state.Command;

/**
 * Created by Antony Lulciuc on 7/17/2016.
 */
public class ServerIOHandler implements Runnable{
    // Connection to server
    Connect m_Connect;

    // Data
    Object[] m_aData;

    // Thread is working
    boolean m_bRunning;
    boolean m_bSuccessful;

    /**
     * Constructor
     * @param _data [in]
     */
    public ServerIOHandler(Connect _Connect, Object[] _data){
        m_Connect  = _Connect;
        m_aData    = _data;
        m_bRunning = true;
        m_bSuccessful = true;
    }

    @Override
    public void run() {
        m_bRunning = true;

        try {
            if (m_aData.length > 1) {
                Command cmd = (Command)m_aData[0];

                switch (cmd){
                    case LOGIN_ACCOUNT:
                    case LOGIN_ACCOUNT_BACKENDLESS:
                    case LOGIN_ACCOUNT_FACEBOOK:
                    case LOGIN_ACCOUNT_GOOGLE_PLUS:
                    case LOGIN_ACCOUNT_TWITTER:
                    case REQUEST_PARKING_SPOT:
                        if (m_Connect.read(m_aData) == false) {
                            m_bRunning = false;
                            m_bSuccessful = false;
                            throw (new Exception("Error : <serveriohandler> : read error!"));
                        }
                        break;
                    case CREATE_ACCOUNT:
                    case UPDATE_ACCOUNT:
                    case UPLOAD_PARKING_SPOT:
                    case DELETE_ACCOUNT:
                        if (m_Connect.write(m_aData) == false) {
                            m_bRunning = false;
                            m_bSuccessful = false;
                            throw (new Exception("Error : <serveriohandler> : read error!"));
                        }
                        break;
                    default:
                        m_bRunning = false;
                        m_bSuccessful = false;
                        throw(new Exception("Error : <serveriohandler> : unknown command!"));
                }

                m_bRunning = false;
                m_bSuccessful = true;
            }else{
                m_bRunning = false;
                m_bSuccessful = false;
                throw(new Exception("Error : <serveriohandler> : to few arguments!"));
            }
        }catch(Exception err){
            // TODO : handle read exception
            m_bRunning = false;
            m_bSuccessful = false;
            System.out.println(err.getMessage());
        }
    }

    public Object get(int _iIndex){
        if (m_aData != null && _iIndex >= 0 && _iIndex < m_aData.length)
            return (m_aData[_iIndex]);
        return (null);
    }

    public boolean isRunning(){
        return (m_bRunning);
    }

    public boolean wasSuccessful(){
        return (m_bSuccessful);
    }

}