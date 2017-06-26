/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user.properties;

import com.parkgo.parkgorithm.background.base.Point;
import com.parkgo.parkgorithm.background.bean.AccountBean;
import com.parkgo.parkgorithm.background.bean.ContactBean;


/**
 * Created by John
 * 
 */
public final class Info {
    private final String     m_strObjectId;         // Database object Id
    private final Preference m_Pref;                // What they want
    private final Car        m_Car;                 // What type of CarBean they drive
    private final Score      m_Score;               // Based on their commentment to ensuring the app runs smoothly
    private final Score      m_PrefScore;           // Score for their preferences 
    private final String     m_strUserName;         // Their account information
    private Point<Double>    m_ptLocation;          // If they use this application their geolocation must be known at all times
    private final String     m_strEmail;            // Their email
    private final AccountBean m_AccountBean;
    private final ContactBean m_ContactBean;

    /**
     * Used for security purposes to ensure user IS user.
     * It is used in conjunction with password verification.
     * _lID uniquely random ID assigned by account
     */
    private Integer m_iID = 0;
    
    /**
     * Constructor
     * @param _strObjectId [in] object id
     * @param _strUserName [in] user name
     * @param _Preference [in] user preference
     * @param _Score [in] user score
     * @param _Car [in] CarBean info
     *      all digits will be replaced with the * character
     *      except for the last four digits (for security/safety). 
     * @param _strEmail [in] email associated with the users account.
     */
    public Info(String _strObjectId, String _strUserName, Preference _Preference, Score _Score,
                Car _Car, String _strEmail, ContactBean _ContactBean, AccountBean _accBean){
        m_strObjectId         = _strObjectId;
        m_Pref                = _Preference == null ? new Preference() : _Preference;
        m_PrefScore           = new PreferenceScore(m_Pref);
        m_Score               = _Score;
        m_strUserName         = _strUserName;
        m_Car                 = _Car;
        m_ptLocation          = null;
        m_strEmail            = _strEmail;
        m_AccountBean         = _accBean;
        m_ContactBean         = _ContactBean;
    }
    
    /**
     * Copy Constructor
     * @param _Info [in] instance of Info
     */
    public Info(Info _Info){
        m_Pref      = new Preference(_Info.getPreference());
        m_PrefScore = new PreferenceScore(m_Pref);
        
        // DEFAULT - NOT COMPLETE
        m_Score = new UserScore();
        // DEFAULT - NOT COMPLETE

        m_strObjectId         = _Info.getObjectId();
        m_strUserName         = _Info.getUserName().substring(0);
        m_Car                 = new Car(_Info.getCar());
        m_AccountBean         = _Info.getAccountBean();
        m_strEmail            = _Info.getEmail();
        m_ContactBean         = _Info.getContectBean();
    }
    
    /**
     * Getter Methods
     */

    public String getObjectId(){
        return (m_strObjectId);
    }

    /**
     * Users real name
     * @return users name
     */
    public String getName(){
        return (m_AccountBean.getFirstName() + " " + m_AccountBean.getLastName());
    }
    
    /**
     * 
     * @return users phone number
     */
    public String getPhoneNumber(){
        return (m_ContactBean.getPhoneNumber());
    }

    public ContactBean getContectBean(){
        return (m_ContactBean);
    }

    /**
     * @return users credit card number
     */
    public String getCreditCardNumber(){
        return (m_AccountBean.getCreditCardNumber());
    }
    
    /**
     * 
     * @return user email
     */
    public String getEmail(){
        return (m_strEmail);
    }
    
    /**
     * @return location of user
     */
    public Point<Double> getLocation(){
        return (m_ptLocation);
    }
    
    /**
     * @return user preference
     */
    final public Preference getPreference(){
        return (m_Pref);
    }
    
    /**
     * @return user score
     */
    final public double getUserScore(){
        return (m_Score.getScore());
    }
    
    /**
     * @return their preference score
     */
    final public double getPreferenceScore(){
        return (m_PrefScore.getScore());
    }
    
    /**
     * @return users user name.
     */
    final public String getUserName(){
        return (m_strUserName);
    }
    
    /**
     * @return CarBean info
     */
    final public Car getCar(){
        return (m_Car);
    }

    final public AccountBean getAccountBean(){
        return (m_AccountBean);
    }

    /**
     * Setter Methods
     */
    
    /**
     * Sets the users location
     * @param _ptLocation [in] new location
     */
    public void setLocation(Point<Double> _ptLocation){
        if (m_ptLocation != null){
            m_ptLocation.setX(_ptLocation.getX());
            m_ptLocation.setY(_ptLocation.getY());
        }else{
            m_ptLocation = _ptLocation;
        }
    }
}
