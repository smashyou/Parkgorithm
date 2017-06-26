package com.parkgo.parkgorithm.background.connect;

import android.content.Context;

import com.backendless.Backendless;
import com.backendless.exceptions.BackendlessException;
import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.CreateAccountProcedure;
import com.parkgo.parkgorithm.background.instruction.server.LoginInstruction;
import com.parkgo.parkgorithm.background.instruction.server.RequestParkingInstruction;
import com.parkgo.parkgorithm.background.instruction.server.UploadParkingInstruction;

import java.util.TreeMap;

/**
 * Establishes connection, get/put requests are defined
 * by derived classes.
 * Verification and closing of connection defined here.
 * Created by Antony on 6/26/2016.
 */
public abstract class BackendlessConnector implements Connect{
    protected final Context m_Context;
    protected TreeMap<Command, Instruction> m_mAction;
    protected Exception m_exErrorMessage = null;
    /**
     * Constructor
     * @param _Context [in] application context
     */
    public BackendlessConnector(Context _Context){
        // Ensure context is not null
        if ((m_Context = _Context) == null){
            System.out.println("Error - application context cannot be null!");
            System.exit(1);
        }

        m_mAction = new TreeMap<>();

        /**
         * WRITE Instructions go HERE
         */
        m_mAction.put(Command.CREATE_ACCOUNT, new CreateAccountProcedure());
        m_mAction.put(Command.DELETE_ACCOUNT, null);
        m_mAction.put(Command.UPDATE_ACCOUNT, null);
        m_mAction.put(Command.UPLOAD_PARKING_SPOT, new UploadParkingInstruction());

        /**
         * READ Instructions go HERE
         */

        m_mAction.put(Command.REQUEST_PARKING_SPOT, new RequestParkingInstruction());

        // Methods used to login to account
        m_mAction.put(Command.LOGIN_ACCOUNT_TWITTER,     new LoginInstruction());
        m_mAction.put(Command.LOGIN_ACCOUNT_FACEBOOK,    new LoginInstruction());
        m_mAction.put(Command.LOGIN_ACCOUNT_GOOGLE_PLUS, new LoginInstruction());
        m_mAction.put(Command.LOGIN_ACCOUNT_BACKENDLESS, new LoginInstruction());
    }

    /**
     * Connect to server
     * @param _url [in] what to connect to
     * @throws Exception
     */
    @Override
    public void connect(String _url)throws Exception{
        String[] strData = _url.split("\\&");

        // Ensure data matches length
        if (strData.length != 3)
            throw(m_exErrorMessage = new Exception("Error - invalid connection protocols!"));

        try {
            // Connect to Backendless server
            Backendless.initApp(m_Context, strData[0], strData[1], strData[2]);
        } catch (BackendlessException err){
            throw(m_exErrorMessage = new Exception("Error : code \'" + err.getCode() + "\' :"+
                                " msg \'"+ err.getDetail() + "|"+ err.getMessage() +"\'"));
        }
    }

    /**
     * @return true if connected to server else false
     */
    @Override
    public boolean isConnected(){
        // TODO : add code to verify connection with backendless server
        //        default return false

        return (Backendless.isAndroid());
    }

    /**
     * Closes opened connection
     */
    @Override
    public void close(){
        try{
            Backendless.UserService.logout();
        }catch (Exception err){
            m_exErrorMessage = err;
            System.out.println(err.getMessage());
        }
    }

    /**
     * Set instruction to execute for a particular command
     * @param _Instruction [in] instruction to execute
     * @param _Command [in] execute instruction with this command
     *                 IF command IS Command.LOGIN_ACCOUNT then applies to all
     *                 login instructions
     */
    public void setInstruction(Command _Command, Instruction _Instruction){
        // Only for commands defined
        if (m_mAction.containsKey(_Command)){
            m_mAction.put(_Command, _Instruction);
        }else{
            if (_Command == Command.LOGIN_ACCOUNT){
                m_mAction.put(Command.LOGIN_ACCOUNT_TWITTER,     _Instruction);
                m_mAction.put(Command.LOGIN_ACCOUNT_FACEBOOK,    _Instruction);
                m_mAction.put(Command.LOGIN_ACCOUNT_GOOGLE_PLUS, _Instruction);
                m_mAction.put(Command.LOGIN_ACCOUNT_BACKENDLESS, _Instruction);
            }
        }
    }

    public Exception getError(){
        return (m_exErrorMessage);
    }

    public Context getContext(){ return (m_Context); }

    /**
     * Abstract methods
     */

    /**
     * Write to server
     * @param _data [in] what to write
     * @return true on success else false
     * @throws Exception
     */
    abstract public boolean write(Object _data)throws Exception;

    /**
     * Read from server
     * @param _data [in] what to store data into
     * @return true on success else false.
     * @throws Exception
     */
    abstract public boolean read(Object _data)throws Exception;
}
