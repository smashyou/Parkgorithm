package com.parkgo.parkgorithm.background.connect;

import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.instruction.Instruction;

/**
 * Created by Antony on 6/25/2016.
 */
public interface Connect {
    /**
     * Connect to server
     * @param _url[in] what to connect to
     * @throws Exception
     */
    public void connect(String _url)throws Exception;

    /**
     * Write to server
     * @param _data[in] what to write
     * @return true on success else false
     * @throws Exception
     */
    public boolean write(Object _data)throws Exception;

    /**
     * Read from server
     * @param _data[in] what to store data into
     * @return true on success else false.
     * @throws Exception
     */
    public boolean read(Object _data)throws Exception;

    /**
     * @return true if connected to server else false
     */
    public boolean isConnected();

    /**
     * Closes opened connection
     */
    public void close();

    /**
     * Set what to execute on specific command
     * @param _Instruction [in] what to execute
     * @param _Command [in] trigger
     */
    public void setInstruction(Command _Command, Instruction _Instruction);

    Exception getError();
}
