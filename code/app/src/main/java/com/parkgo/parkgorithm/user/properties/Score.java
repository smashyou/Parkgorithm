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
abstract public class Score {
    protected double m_dScore = 0.0;
    
    public double getScore(){
        return (m_dScore);
    }
    
    abstract protected void calcScore();
}
