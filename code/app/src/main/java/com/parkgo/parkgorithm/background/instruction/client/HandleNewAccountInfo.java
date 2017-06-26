package com.parkgo.parkgorithm.background.instruction.client;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.regex.Regex;
import com.parkgo.parkgorithm.background.regex.RegexEmail;
import com.parkgo.parkgorithm.background.regex.RegexName;

import java.util.LinkedList;

/**
 * Created by Lony on 7/28/2016.
 */
public class HandleNewAccountInfo implements Instruction {
    private static final Regex m_REmail = new RegexEmail();
    private static final Regex m_RName  = new RegexName();
    private LinkedList<String> m_llInput;
    private LinkedList<Integer> m_llIds;
    private AppCompatActivity m_Context;
    private Integer m_iIndex;
    @Override
    public boolean execute(Object[] _data){
        if (_data.length == 4){
            if (_data[1] instanceof LinkedList && _data[2]  instanceof LinkedList) {
                Integer iRes;
                boolean pass = true;
                m_Context = (AppCompatActivity)_data[0];
                m_llInput = (LinkedList<String>) _data[1];
                m_llIds   = (LinkedList<Integer>) _data[2];
                m_iIndex  = 0;

                iRes = handleEmailInput();
                pass = checkResult(iRes, _data);

                iRes = handleNameInput();
                if (pass)
                    pass = checkResult(iRes, _data);

                iRes = handlePhoneNumberInput();
                if (pass)
                    pass = checkResult(iRes, _data);

                iRes = handlePasswordInput();
                if (pass)
                    pass = checkResult(iRes, _data);

                return (pass);
            }
        }

        return (false);
    }


    /**
     * PRIVATE : Methods
     */

    private boolean checkResult(Integer _iResult, Object[] _data){
        if (_iResult != 0) {
            _data[3] = _iResult;
            return (false);
        }

        return (true);
    }

    private Integer handleEmailInput(){
        TextView tv;
        EditText et;
        String entry;
        Integer pass = 0;

        // Check email submitted
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0){
                entry = entry.trim();

                if (m_REmail.matches(entry)){
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("Email:");
                    m_llInput.add(entry);
                }else{
                    m_llInput.add("<*(error-code)*>");
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*Email (youraccount@example.com):");
                    pass = 1;
                }
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Email (cannot be empty):");
                pass = 2;
            }
        }

        // Verify email correctly inputed
        // EMAIL VERIFICATION
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0){
                entry = entry.trim();

                if (m_llInput.get(0).equals(entry)){
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("Retype Email:");
                    m_llInput.add(entry);
                }else{
                    m_llInput.add("<*(error-code)*>");
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*Retype Email (do not match):");
                    pass = 3;
                }
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Retype Email (cannot be empty):");

                if (pass == 0)
                    pass = 2;
            }
        }

        return (pass);
    }

    private Integer handleNameInput(){
        TextView tv;
        EditText et;
        String entry;
        Integer pass = 0;

        // FIRST NAME
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0){
                if (m_RName.matches(entry = entry.trim())){
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("First Name:");
                    m_llInput.add(entry);
                }else{
                    m_llInput.add("<*(error-code)*>");
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*First Name (Billy):");
                    pass = 1;
                }
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*First Name (cannot be empty):");
                pass = 2;
            }
        }

        // LAST NAME
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0){
                if (m_RName.matches(entry = entry.trim())){
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("Last Name:");
                    m_llInput.add(entry);
                }else{
                    m_llInput.add("<*(error-code)*>");
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*Last Name (Bob):");

                    if (pass == 0)
                        pass = 1;
                }
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Last Name (cannot be empty):");

                if (pass == 0)
                    pass = 2;
            }
        }

        return (pass);
    }

    private Integer handlePhoneNumberInput(){
        TextView tv;
        EditText et;
        String entry;
        Integer pass = 0;

        // Phone number
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0){
                entry = entry.trim();

                if (entry.matches("^([0-9]*)")){
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("Phone number:");
                    m_llInput.add(entry);
                }else{
                    m_llInput.add("<*(error-code)*>");
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*Phone number (ex: 1234567890):");
                    pass = 1;
                }
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Phone number (cannot be empty):");
                pass = 2;
            }
        }

        return (pass);
    }

    private Integer handlePasswordInput(){
        TextView tv;
        EditText et;
        String entry;
        Integer pass = 0;

        // PASSWORD
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0) {
                entry = entry.trim();
                tv    = (TextView) m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(0, 0, 0));
                tv.setText("Password:");
                m_llInput.add(entry);
            }else{
                m_llInput.add("<*(error-code)*>");
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Password (cannot be empty):");
                pass = 1;
            }
        }

        // PASSWORD Verification
        if ((et = (EditText)m_Context.findViewById(m_llIds.get(m_iIndex++))) != null){
            if ((entry = et.getText().toString()) != null && entry.length() > 0) {
                entry = entry.trim();

                if (m_llInput.get(m_llInput.size() - 1).equals(entry)) {
                    tv = (TextView) m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(0, 0, 0));
                    tv.setText("Retype Password:");
                    m_llInput.add(entry);
                }else{
                    tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    tv.setText("*Retype Password (do not match):");
                    pass = 3;
                }
            }else{
                tv = (TextView)m_Context.findViewById(m_llIds.get(m_iIndex++));
                tv.setTextColor(Color.rgb(255, 0, 0));
                tv.setText("*Retype Password (cannot be empty):");
                if (pass == 0)
                    pass = 2;
            }
        }

        return (pass);
    }
}
