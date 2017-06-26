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

/**
 * Created by John
 */
public class TransactionModal implements Modal {
    protected Dialog  m_Dialog;
    protected Context m_Context;
    protected boolean m_bActive;
    protected int     m_iResult;

    public TransactionModal(Context _Context){
        m_Context = _Context;
        m_bActive = false;
        m_iResult = 0;
    }

    @Override
    public void show() {
        init();
        m_bActive = true;
        m_Dialog.show();
    }

    @Override
    public void hide() {
        m_bActive = false;
        m_Dialog.hide();
    }

    public void setForeignName(String _strForeignName){
        TextView tv = (TextView)m_Dialog.findViewById(R.id.client_name_lbl);
        tv.setText(_strForeignName);
    }

    public void setUserInfo(String _strInfo){
        TextView tv = (TextView)m_Dialog.findViewById(R.id.user_info_lbl);
        tv.setText(_strInfo);
    }

    public void setParkingInfo(String _strParkingInfo){
        TextView tv = (TextView)m_Dialog.findViewById(R.id.parking_spot_ingo_lbl);
        tv.setText(_strParkingInfo);
    }

    protected void init(){
        Window window;
        m_Dialog = new Dialog(m_Context);
        window = m_Dialog.getWindow();

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.CENTER);
        m_Dialog.setContentView(R.layout.modal_transaction);
        setButtonAction();
    }

    protected void setButtonAction(){
        Button btn = (Button)m_Dialog.findViewById(R.id.accept_btn);

        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    m_iResult = 1;
                    m_bActive = false;
                    m_Dialog.cancel();
                }
            });
        }

        btn = (Button)m_Dialog.findViewById(R.id.decline_btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    m_iResult = 1;
                    m_bActive = false;
                    m_Dialog.cancel();
                }
            });
        }
    }

    public boolean isActive(){
        return (m_bActive);
    }

    public int getResult(){
        return (m_iResult);
    }

}
