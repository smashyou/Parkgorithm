package com.parkgo.parkgorithm.background.instruction.client;

import android.content.Context;
import android.widget.Toast;

import com.parkgo.parkgorithm.background.instruction.Instruction;

/**
 * Created by Antony Lulciuc on 8/6/2016.
 */
public class ToastInstruction implements Instruction {
    @Override
    public boolean execute(Object[] _data){
        Toast.makeText((Context)_data[0], (String)_data[1], (int)_data[2]).show();
        return (true);
    }
}
