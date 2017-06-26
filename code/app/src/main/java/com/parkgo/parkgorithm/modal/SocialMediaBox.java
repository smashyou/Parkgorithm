package com.parkgo.parkgorithm.modal;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.parkgo.parkgorithm.R;

/**
 * Created by John
 */
public class SocialMediaBox implements Modal {
    protected Dialog m_Dialog;
    protected Context m_Context;

    public SocialMediaBox(Context _Context){
        m_Context = _Context;
        init();
    }

    @Override
    public void show() {
        m_Dialog.show();
    }

    @Override
    public void hide() {
        m_Dialog.hide();
    }

    private void init(){
        Window window;
        m_Dialog = new Dialog(m_Context);
        window = m_Dialog.getWindow();

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.CENTER);
        m_Dialog.setContentView(R.layout.modal_alternate_signin);
        setButtonAction();
    }

    private void setButtonAction(){
        Button btn = (Button)m_Dialog.findViewById(R.id.social_sign_in_cancel_btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    m_Dialog.cancel();
                }
            });
        }
    }
}
