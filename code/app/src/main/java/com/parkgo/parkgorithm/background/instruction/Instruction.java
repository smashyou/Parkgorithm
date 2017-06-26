package com.parkgo.parkgorithm.background.instruction;

/**
 * Created by Lony on 7/4/2016.
 */
public interface Instruction {
    int viewId = 0;

    /**
     * Execute instruction on data set
     * @param _data [in, out] what to process
     * @return instance of type boolean
     */
    public boolean execute(Object[] _data);
}
