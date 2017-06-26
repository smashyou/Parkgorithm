package com.parkgo.parkgorithm.background.instruction.client;

import android.os.Handler;
import android.os.Looper;

import com.parkgo.parkgorithm.login.CreateAccountActivity;
import com.parkgo.parkgorithm.background.ParkgorithmServer;
import com.parkgo.parkgorithm.background.connect.state.Command;
import com.parkgo.parkgorithm.background.exception.AccountException;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.modal.MessageBox;
import com.parkgo.parkgorithm.runnable.DisplayMessage;

import java.util.LinkedList;

/**
 * Created by Lony on 7/24/2016.
 */
public class CreateAccountProcedure implements Instruction {

    @Override
    public boolean execute(Object[] _data){
        MessageBox msgBox;
        Handler handler;
        Exception err;

        if (_data.length == 3) {
            if (_data[0] instanceof CreateAccountActivity && _data[1] instanceof ParkgorithmServer && _data[2] instanceof LinkedList) {
                CreateAccountActivity caa = (CreateAccountActivity)_data[0];
                ParkgorithmServer ParkgoServer = (ParkgorithmServer)_data[1];
                LinkedList<String> llAccountInfo = (LinkedList<String>)_data[2];

                try {
                    ParkgoServer.createAccount(llAccountInfo.get(0), llAccountInfo.get(llAccountInfo.size() - 2), llAccountInfo);

                    while (ParkgoServer.isWorking()) ;

                    // TODO : add confirmation message

                    if (ParkgoServer.wasSuccessful(Command.CREATE_ACCOUNT)) {
                        caa.finish();
                    } else {
                        err = ParkgoServer.getError();
                        handler = new Handler(Looper.getMainLooper());
                        handler.post(new DisplayMessage(caa, "ERROR - CREATE ACCOUNT", err == null ? "<unknown>" : "  " + err.getMessage() + "\n\n\n", MessageBox.MESSAGE_BOX_ERROR));
                    }
                } catch (AccountException accErr) {
                    handler = new Handler(Looper.getMainLooper());
                    handler.post(new DisplayMessage(caa, "ERROR - " + "<code:" + accErr.getErrorCode() + ">", accErr.getMessage(), MessageBox.MESSAGE_BOX_ERROR));
                }
            }
        }
        return (false);
    }
}
