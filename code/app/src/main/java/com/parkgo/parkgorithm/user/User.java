/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user;

import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.properties.UserState;

/**
 * Created by John
 * 
 */
public class User{
    protected Info     m_Info;
    private UserState  m_usState;

   /**
    * Constructor
    * @param _info [in] user account information
    * @param _state [in] type of user
    */
    public User(Info _info, UserState _state){
        m_Info    = _info;
        m_usState = _state;
    }
    
    /**
     * 
     * @return non-mutable user account information 
     */
    final public Info getInfo(){
        return (m_Info);
    }
    
    /**
     * @return type of user
     */
    final public UserState getState(){
        return (m_usState);
    }
}
