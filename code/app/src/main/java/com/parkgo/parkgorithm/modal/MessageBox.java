package com.parkgo.parkgorithm.modal;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.background.instruction.Instruction;

import java.util.Map;

/**
 * Created by John
 */
public class MessageBox implements Modal{
    public static final int MESSAGE_BOX_OK    = 0x00000001;
    public static final int MESSAGE_BOX_ERROR = 0x00000002;
    public static final int MESSAGE_BOX_PLAIN = 0x00000004;
    protected Dialog m_Dialog;
    protected Map<Integer, Instruction> m_mAction;
    protected Integer m_iLayoutId;
    protected Integer m_iTitleId;
    protected Integer m_iMessageId;
    protected Integer m_iButtonId;
    private Context m_Context;
    private String m_strTitle;
    private String m_strMessage;

    /**
     * Constructor
     * @param _Context
     * @param _strTitle
     * @param _strMsg
     */
    public MessageBox(Context _Context, String _strTitle, String _strMsg, int _iMsgType){
        m_Context = _Context;
        m_strTitle = _strTitle;
        m_strMessage = _strMsg;

        // Determine type of message box
        switch (_iMsgType) {
            case MESSAGE_BOX_OK:
                break;
            case MESSAGE_BOX_PLAIN:
                break;
            case MESSAGE_BOX_ERROR:
                m_iLayoutId  = R.layout.modal_error;
                m_iTitleId   = R.id.modal_error_title;
                m_iMessageId = R.id.modal_error_msg;
                m_iButtonId  = R.id.modal_exit_btn;
                break;
        }

        // Initialize dialog box
        init();
    }

    public void show(){
        m_Dialog.show();
    }

    public void hide(){
        m_Dialog.hide();
    }

    public void setTitle(String _strTitle){
        TextView tv = (TextView)m_Dialog.findViewById(m_iTitleId);
        tv.setText(m_strTitle);
    }

    public void setMessage(String _strMessage){
        TextView tv = (TextView)m_Dialog.findViewById(m_iMessageId);
        tv.setText(m_strMessage);
    }

    private void init(){
        Window window;
        try {
            m_Dialog = new Dialog(m_Context);
            window = m_Dialog.getWindow();

            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setGravity(Gravity.CENTER);
            m_Dialog.setContentView(m_iLayoutId);

            setTitle(m_strTitle);
            setMessage(m_strMessage);
            setButtonAction();
        } catch (Exception err){
            System.out.println(err.getMessage());
        }
    }

    private void setButtonAction(){
        Button btn = (Button)m_Dialog.findViewById(R.id.modal_exit_btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    m_Dialog.cancel();
                }
            });
        }
    }
}
