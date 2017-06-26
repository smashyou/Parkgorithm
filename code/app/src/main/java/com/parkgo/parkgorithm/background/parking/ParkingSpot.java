/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.parking;

import com.backendless.geo.GeoPoint;
import com.parkgo.parkgorithm.background.base.Point;
import com.parkgo.parkgorithm.user.properties.Preference;
import com.parkgo.parkgorithm.background.factory.BeanFactory;
import com.parkgo.parkgorithm.background.bean.AddressBean;
import com.parkgo.parkgorithm.background.bean.Bean;
import com.parkgo.parkgorithm.background.bean.ParkingspotBean;

/**
 *
 * @author Antony
 */
final public class ParkingSpot implements BeanFactory{
    private Point<Double> m_ptAt    = null;
    private double m_dCost          = 0.0;
    private String m_strObjectId    = null;
    private String m_strWhoSubmitted;
    private String m_strStreetName;   
    private String m_strAddress;
    private ParkingHours  m_pkHours;

    /**
     * 
     * @param _ptAtLocation [in] location of parking spot
     * @param _dCost [in] price for location specified.
     */
    public ParkingSpot(Point<Double> _ptAtLocation, double _dCost){
        m_ptAt    = _ptAtLocation;
        m_pkHours = new ParkingHours();
        setCost(_dCost);
    }

    /**
     * Create new bean object
     * @return instance of a new bean object
     */
    @Override
    public Bean newBeanInstance(){
        ParkingspotBean parkingspotBean = new ParkingspotBean();
        parkingspotBean.setAddressId(m_strAddress);
        parkingspotBean.setCost(m_dCost);
        return (parkingspotBean);
    }

    /**
     * Getter Methods
     */
    
    /**
     * @return geographical location of parking spot
     */
    public Point<Double> getLocation(){
        return (m_ptAt);
    }
    
    /**
     * @return parking hours for this location.
     */
    public ParkingHours getParkingHours(){
        return (m_pkHours);
    }
    
    /**
     * @return price for parking spot
     */
    public double getPrice(){
        return (m_dCost);
    }
    
    /**
     * @return persons who submitted parking information 
     */
    public String getSubmitter(){
        return (m_strWhoSubmitted);
    }
    
    /**
     * @return address associated with this parking spot
     */
    public String getAddress(){
        return (m_strAddress);
    }

    /**
     * @return Name of street where parking spot resides
     */
    public String getStreetName(){
        return (m_strStreetName);
    }

    public AddressBean getAddressBean(){
        AddressBean addressBean = new AddressBean();
        addressBean.setAddress(m_strAddress);
        addressBean.setStreetName(m_strStreetName);
        addressBean.setGeographicalLocation(new GeoPoint(m_ptAt.getX(), m_ptAt.getY()));
        addressBean.setParkingSpotId(m_strObjectId);
        return (addressBean);
    }

    /**
     * Setter methods
     */
    
    /**
     * @param _pkHours [in] sets parking hours for this location
     */
    public void setParkingHours(ParkingHours _pkHours){
        m_pkHours = _pkHours;
    }
    
    /**
     * @param _dCost [in] sets the price for this location
     */
    public void setCost(double _dCost){
        if (_dCost >= 0.0){
            m_dCost = _dCost < Preference.MAX_PRICE ? _dCost : Preference.MAX_PRICE;
        }
    }
    
    /**
     * @param _strSubmitter [in] name of submitter.
     */
    public void setSubmitter(String _strSubmitter){
        m_strWhoSubmitted = _strSubmitter;
    }

    /**
     * @param _strAddress [in] address associated with this parking
     * spot
     */
    public void setAddress(String _strAddress){
        m_strAddress = _strAddress;
    }
    
    /**
     * @param _strStreeName [in] street name where this parking spot resides.
     */
    public void setStreetName(String _strStreeName){
        m_strStreetName = _strStreeName;
    }
}
