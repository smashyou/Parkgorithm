package com.parkgo.parkgorithm.runnable;

import com.parkgo.parkgorithm.background.instruction.Instruction;

/**
 * Created by John
 */
public class RunnableAction implements Runnable {
    private Instruction m_Action;
    private Object[] m_Data;
    /**
     * Constructor
     * @param _action
     * @param _data
     */
    public RunnableAction(Instruction _action, Object[] _data){
        m_Action = _action;
        m_Data   = _data;
    }

    @Override
    public void run() {
        if (m_Action != null){
            m_Action.execute(m_Data);
        }
    }
}
