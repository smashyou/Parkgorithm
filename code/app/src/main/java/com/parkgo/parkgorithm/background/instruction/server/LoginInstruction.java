package com.parkgo.parkgorithm.background.instruction.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.user.properties.Info;

/**
 * Created by Lony on 7/4/2016.
 */
public class LoginInstruction implements Instruction {
    private int m_id = 0;

    /**
     * Loads requested activity page for logged in user.
     * @param _onWhat [in] must be a three element array
     *                ELM 1 := of type Context (instance of application)
     *                ELM 2 := of type Class (activity to load)
     *                ELM 3 := of type Info (data acquired from login)
     * @return true on success, else false. IF false array size invalid,
     *      invalid type passed, or corrupted data.
     */
    @Override
    public boolean execute(Object[] _onWhat){
        if (_onWhat.length != 3)
            return (false);

        try{
            AppCompatActivity context = (AppCompatActivity)_onWhat[0];
            Intent intent             = new Intent(context, (Class)_onWhat[1]);
            Info info                 = (Info)_onWhat[2];

            context.startActivity(intent);
        }catch (Exception err){
            System.out.println(err.getMessage());
            return (false);
        }

        return (true);
    }
}
