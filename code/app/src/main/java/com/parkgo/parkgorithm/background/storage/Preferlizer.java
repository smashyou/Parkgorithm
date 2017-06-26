/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.storage;

import com.parkgo.parkgorithm.user.properties.Preference;

/**
 *
 * @author Antony
 */
public class Preferlizer implements Serializer {
    private Preference m_Pref = null;
    
    /**
     * Default Constructor
     */
    public Preferlizer(){
        // DO NOTHING
    }
    
    /**
     * Constructor
     * @param _Preference[in] instance of preference
     */
    public Preferlizer(Preference _Preference){
        m_Pref = _Preference;
    }
    
    /**
     * Sets object to pack into buffer
     * @param _obj[in] object to pack
     * @return true if object passed can be packed else false.
     */
    @Override
    public boolean setObjectToPack(Object _obj){
        if (_obj == null) {
            return (false);
        }
        if (!Preference.class.isAssignableFrom(_obj.getClass())) {
            return (false);
        }
        
        m_Pref = (Preference)_obj;
        return (true);
    }
    
    /**
     * Packs preference information into buffer
     * @return packed preference data, if pref data not set then returns null
     */
    @Override
    public byte[] pack(){
        byte[] bData = new byte[7];
        double dValue;
        int iValue;
        byte bValue;
        
        // Ensure data is set
        if (m_Pref == null){
            return (null);
        }
        
        // Bits : [8,4,4,3,5,7,1,4|,4,2,6,1,7]
        // Distance limit
        iValue    = (int)Math.floor(dValue = m_Pref.getDistanceLimit());
        bData[0]  = (byte)(((byte)(0x0FF0 & iValue)) >> 4);
        bData[1]  = (byte)(((byte)(0x00F & iValue)) << 4);  
        bValue    = (byte)Math.floor((dValue - (double)iValue) * 100.0);
        bData[1] |= (0x78 & bValue) >> 3;
        bData[2]  = (byte)((0x07 & bValue) << 5);
        
        // Price limit
        bValue    = (byte)(Math.floor(dValue = m_Pref.getPriceLimit()));
        bData[2] |= (0x1F & bValue);
        bValue    = (byte)Math.floor((dValue - (double)bValue) * 100.0);
        bData[3]  = (byte)((0x7F & bValue) << 1);
        
        // Parking hour limit
        bValue    = (byte)(Math.floor(dValue = m_Pref.getParkTimeLimit())); 
        bData[3] |= (0x10 & bValue) >> 4;
        bData[4]  = (byte)((0x0F & bValue) << 4);
        bValue    = (byte)(Math.floor((dValue - (double)bValue) * 100.0));
        bData[4] |= (0x3C & bValue) >> 2;
        bData[5]  = (byte)((0x03 & bValue) << 6);
        
        // Rating Limit
        bValue    = (byte)(Math.floor(dValue = m_Pref.getRatingLimit()));
        bData[5] |= (0x7E & bValue) >> 1;
        bData[6]  = (byte)((0x01 & bValue) << 7);
        bData[6] |= (0x7F & (byte)Math.floor((dValue - (double)bValue) * 100.0));
        
        return (bData);
    }
    
    /**
     * Unpacks packed preferences
     * @param _data[in] packed preferences
     * @return instance of preference if successfully unpacked data else null
     */
    @Override
    public Object unpack(byte[] _data){
        Preference preference = new Preference();
        int  iValue;
        byte bValue;
        byte bRem;
        
        // Must be contaied in 7B.
        if (_data.length != 7)
            return (null);
        
        // Bits : [8,4,4,3,5,7|,1,4,4,2,6,1,7]
        // Distance limit
        iValue  = _data[0] << 4;
        iValue |= (0xF0 & _data[1]) >> 4;
        bValue  = (byte)((0x0F & _data[1]) << 3);
        bValue |= (0xE0 & _data[2]) >> 5;
        preference.setDistanceLimit((double)iValue + (double)bValue / 100.0);
        
        // Price limit
        bValue = (byte)(0x1F & _data[2]);
        bRem   = (byte)((0xFE & _data[3]) >> 1);
        preference.setPriceLimit((double)bValue + (double)bRem / 100.0);
        
        // Parking hour limit
        bValue  = (byte)((0x01 & _data[3]) << 4);
        bValue |= ((0xF0 & _data[4]) >> 4);
        bRem    = (byte)((0x0F & _data[4]) << 2);
        bRem   |= ((0xC0 & _data[5]) >> 6);
        preference.setParkTimeLimit((double)bValue + (double)bRem / 100.0);
        
        // Rating Limit
        bValue  = (byte)((0x3F & _data[5]) << 1);
        bValue |= (0x80 & _data[6]) >> 7;
        preference.setRatingLimit((double)bValue + (double)(0x7F & _data[6]) / 100.0);
        
        return (preference);
    }
}
