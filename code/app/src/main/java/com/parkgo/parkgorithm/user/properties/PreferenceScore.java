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
public class PreferenceScore extends Score {
    Preference m_Pref;
    
    public PreferenceScore(Preference _Preferences){
        m_Pref = _Preferences;
        calcScore();
    }
    
    @Override
    final protected void calcScore(){
        double dist  =  5.0 * (m_Pref.getDistanceLimit() / Preference.MAX_DIST);
        double price = 65.0 * (m_Pref.getPriceLimit()    / Preference.MAX_PRICE);
        double time  = 20.0 * (m_Pref.getParkTimeLimit()     / Preference.MAX_TIME);
        double rate  = 10.0 * (m_Pref.getRatingLimit()   / Preference.MAX_RATING);
        m_dScore = dist + price + time + rate;
    }
}
