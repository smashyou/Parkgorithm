/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user.properties;

/**
 * Created by John
 *  
 */
public class UserScore extends Score {
    
    
    public UserScore(){
        
        calcScore();
    }
    
    @Override
    final protected void calcScore(){
        m_dScore = 95.0;
    }
    
}
