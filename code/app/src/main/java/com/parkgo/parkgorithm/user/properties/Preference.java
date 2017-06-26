/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user.properties;

import com.parkgo.parkgorithm.background.factory.BeanFactory;
import com.parkgo.parkgorithm.background.bean.Bean;
import com.parkgo.parkgorithm.background.bean.PreferenceBean;


/**
 * Created by John
 * 
 */
public class Preference implements BeanFactory{
    static public final double MAX_DIST    = 100.0;
    static public final double MAX_PRICE   = 10.0;
    static public final double MAX_TIME    = 12.0;
    static public final double MAX_RATING  = 100.0;
    private double m_dDistanceLim   = MAX_DIST;
    private double m_dPriceLim      = MAX_PRICE;
    private double m_dParkLim       = MAX_TIME;
    private double m_dRatingLim     = MAX_RATING;
    private String m_strObjectId    = null;
    /**
     * Constructor
     * Initializes to default values 
     */
    public Preference(){
        // DO NOTHING
    }

    /**
     *
     * @param _preferenceBean [in] contains user preference data
     */
    public Preference(PreferenceBean _preferenceBean){
        setDistanceLimit(_preferenceBean.getDistanceLimit());
        setPriceLimit(_preferenceBean.getPriceLimit());
        setParkTimeLimit(_preferenceBean.getIdealParkingDurationLimit());
        setRatingLimit(_preferenceBean.getMinimalUserRatingScore());
        m_strObjectId = _preferenceBean.getObjectId();
    }
    
    /**
     * Copy Constructor
     * @param _Preference [in] instance of Preference
     */
    public Preference(Preference _Preference){
        setDistanceLimit(_Preference.getDistanceLimit());
        setPriceLimit(_Preference.getPriceLimit());
        setParkTimeLimit(_Preference.getParkTimeLimit());
        setRatingLimit(_Preference.getRatingLimit());
    }

    /**
     * Create new bean object
     * @return instance of a new PreferenceBean
     */
    @Override
    public Bean newBeanInstance(){
        PreferenceBean preferenceBean = new PreferenceBean();
        preferenceBean.setDistanceLimit(m_dDistanceLim);
        preferenceBean.setIdealParkingDurationLimit(m_dParkLim);
        preferenceBean.setMinimalUserRatingScore(m_dRatingLim);
        preferenceBean.setPriceLimit(m_dPriceLim);
        preferenceBean.setObjectId(m_strObjectId);
        return (preferenceBean);
    }

    /**
     * @return max distance from location
     */
    final public double getDistanceLimit(){
        return (m_dDistanceLim);
    }
    
    /**
     * 
     * @return max price for location
     */
    final public double getPriceLimit(){
        return (m_dPriceLim);
    }
    
    /**
     * @return max parking time for location
     */
    final public double getParkTimeLimit(){
        return (m_dParkLim);
    }
    
    /**
     * 
     * @return min rating of supplier/buyer 
     */
    final public double getRatingLimit(){
        return (m_dRatingLim);
    }
    
    /**
     * Setter Methods
     */
    
    /**
     * 
     * @param _dDist [in] max distance from location
     */
    final public void setDistanceLimit(double _dDist){
        if (_dDist > 10.0)
            m_dDistanceLim = _dDist < MAX_DIST ? _dDist : MAX_DIST;
    }
    
    /**
     * 
     * @param _dPrice [in] max price for location
     */
    final public void setPriceLimit(double _dPrice){
        if (_dPrice >= 0.0){
            m_dPriceLim = _dPrice < MAX_PRICE ? _dPrice : MAX_PRICE;
        }
    }
    
    /**
     * 
     * @param _dParkLimit [in] max time to use location
     */
    final public void setParkTimeLimit(double _dParkLimit){
        if (_dParkLimit > 0.0){
            m_dParkLim = _dParkLimit < MAX_TIME ? _dParkLimit : MAX_TIME;
        }
    }
    
    /**
     * 
     * @param _dRatingLimit [in] min rating of supplier/buyer
     */
    final public void setRatingLimit(double _dRatingLimit){
        if (_dRatingLimit >= 0.0){
            m_dRatingLim = _dRatingLimit < MAX_RATING ? _dRatingLimit : MAX_RATING;
        }
    }
    
    /**
     * All fields (distanceLimit, parkingTimeNeeded, ratingLimit, voucherPriceLimit) must be equal for two
     * objects of this class to be equal.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Preference.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        
        final Preference other = (Preference) obj;
        
        // Test difference to handle floating point arithmetic error
        if (Math.abs(m_dDistanceLim - other.m_dDistanceLim) > 0.01) {
            return false;
        }
        
        // Test difference to handle floating point arithmetic error
        if (Math.abs(m_dParkLim - other.m_dParkLim) > 0.1) {
            return false;
        }
        
        // Test difference to handle floating point arithmetic error
        if (Math.abs(m_dRatingLim - other.m_dRatingLim) > 0.01) {
            return false;
        }
        
        // Test difference to handle floating point arithmetic error
        return (Math.abs(m_dPriceLim - other.m_dPriceLim) <= 0.01);
    }
}
