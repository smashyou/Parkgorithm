package com.parkgo.parkgorithm.runnable;

import android.content.Context;

import com.parkgo.parkgorithm.modal.MessageBox;

/**
 * Created by John
 */
public class DisplayMessage implements Runnable {
    private MessageBox m_MessageBox;
    private Context m_Context;
    private String  m_strTitle;
    private String  m_strMessage;
    private Integer m_iMsgType;

    public DisplayMessage(Context _Context, String _strTitle, String _strMsg, int _iMsgType){
        m_Context    = _Context;
        m_strTitle   = _strTitle;
        m_strMessage = _strMsg;
        m_iMsgType   = _iMsgType;
    }

    @Override
    public void run(){
        m_MessageBox = new MessageBox(m_Context, m_strTitle, m_strMessage, m_iMsgType);
        m_MessageBox.show();
    }
}
