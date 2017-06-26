/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user;

import com.parkgo.parkgorithm.background.parking.ParkingSpot;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.properties.UserState;
import com.parkgo.parkgorithm.background.base.Point;

/**
 * Created by John
 * 
 */
public class Buyer extends User{
    private Point<Double> m_ptTo = null;
    private ParkingSpot m_RequestedParkingspot;

    /**
     * Constructor
     * @param _info [in]  account information
     * @param _parkingspot [in] desired location
     */
    public Buyer(Info _info, ParkingSpot _parkingspot){
        super(_info, UserState.USER_BUYER);
        m_RequestedParkingspot = _parkingspot;
        m_RequestedParkingspot.setSubmitter(m_Info.getUserName());
    }

    /**
     *
     * @return parking spot information
     */
    final public ParkingSpot getRequestParkingspot(){
        return (m_RequestedParkingspot);
    }
    
}
