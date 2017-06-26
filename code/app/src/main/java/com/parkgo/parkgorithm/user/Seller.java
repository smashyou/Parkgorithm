/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user;

import com.parkgo.parkgorithm.background.parking.ParkingSpot;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.properties.UserState;

/**
 * Created by John
 * 
 */
public class Seller extends User{
    private ParkingSpot m_HostedParkingspot = null;
    
    /**
     * Constructor
     * @param _info [in] account information
     * @param _Parkingspot [in] parking spot information
     */
    public Seller(Info _info, ParkingSpot _parkingspot){
        super(_info, UserState.USER_SELLER);
        m_HostedParkingspot = _parkingspot;
        m_HostedParkingspot.setSubmitter(m_Info.getUserName());
    }
    
    /**
     * 
     * @return parking spot information
     */
    final public ParkingSpot getHostedParkingspot(){
        return (m_HostedParkingspot);
    }
}
