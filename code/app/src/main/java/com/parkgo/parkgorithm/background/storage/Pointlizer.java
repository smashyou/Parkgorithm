/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.storage;

import com.parkgo.parkgorithm.background.base.Point;

/**
 *
 * @author Antony
 */
public class Pointlizer implements Serializer {
    private Point<Double> m_ptLoc = null;
    
    /**
     * Default Constructor
     */
    public Pointlizer(){
        // DO NOTHING
    }
    
    /**
     * Constructor
     * @param _ptLoc[in] point to pack
     */
    public Pointlizer(Point<Double> _ptLoc){
        m_ptLoc = _ptLoc;
    }
    
    /**
     * Sets object to pack into buffer
     * @param _obj[in] object to pack
     * @return true if object passed can be packed else false.
     */
    @Override
    public boolean setObjectToPack(Object _obj) {
       if (_obj == null)
           return (false);
       
       if (!Point.class.isAssignableFrom(_obj.getClass())){
           return (false);
       }
       
       m_ptLoc = (Point<Double>)_obj;
       return (true);
    }

    /**
     * Transforms object into a array buffer
     * Data packed is determined by the constructor
     * @return byte array representing object passed
     */
    @Override
    public byte[] pack() {
        // If data not set
        if (m_ptLoc == null)
            return (null);
        
        byte[] bData = new byte[16];
        double dValue;  
        int iValue;
        
        // X-coordinate
        iValue   = (int)(Math.floor(dValue = m_ptLoc.getX()));
        bData[0] = (byte)((0xFF000000 & iValue) >> 24);
        bData[1] = (byte)((0x00FF0000 & iValue) >> 16);
        bData[2] = (byte)((0x0000FF00 & iValue) >> 8);
        bData[3] = (byte)((0x000000FF & iValue));
        iValue   = (int)(Math.floor((dValue - (double)iValue) * 10e8));
        bData[4] = (byte)((0xFF000000 & iValue) >> 24);
        bData[5] = (byte)((0x00FF0000 & iValue) >> 16);
        bData[6] = (byte)((0x0000FF00 & iValue) >> 8);
        bData[7] = (byte)((0x000000FF & iValue));
        
        // Y-coordinate
        iValue   = (int)(Math.floor(dValue = m_ptLoc.getY()));
        bData[8] = (byte)((0xFF000000 & iValue) >> 24);
        bData[9] = (byte)((0x00FF0000 & iValue) >> 16);
        bData[10] = (byte)((0x0000FF00 & iValue) >> 8);
        bData[11] = (byte)((0x000000FF & iValue));
        iValue   = (int)(Math.floor((dValue - (double)iValue) * 10e8));
        bData[12] = (byte)((0xFF000000 & iValue) >> 24);
        bData[13] = (byte)((0x00FF0000 & iValue) >> 16);
        bData[14] = (byte)((0x0000FF00 & iValue) >> 8);
        bData[15] = (byte)((0x000000FF & iValue));
        
        return (bData);
    }

    /**
     * Reverses pack method
     * @param _data[in] packed data
     * @return new instance of packed object
     */
    @Override
    public Object unpack(byte[] _data) {
        // Ensure right buffer length
        if (_data.length != 16)
            return (null);
        
        Point<Double> ptCoord = new Point<>(0.0, 0.0);
        double dValue;
        int iValue;
        
         // X-coordinate
        iValue  = ((0xFF & _data[0]) << 24);
        iValue |= ((0xFF & _data[1]) << 16);
        iValue |= ((0xFF & _data[2]) <<  8);
        iValue |= (0xFF & _data[3]);
        dValue  = (double)iValue;
        iValue  = ((0xFF & _data[4]) << 24);
        iValue |= ((0xFF & _data[5]) << 16);
        iValue |= ((0xFF & _data[6]) <<  8);
        iValue |=  (0xFF & _data[7]);
        dValue += (double)iValue / (double)10e8;
        ptCoord.setX(dValue);
        
        // Y-coordinate
        iValue  = ((0xFF & _data[8]) << 24);
        iValue |= ((0xFF & _data[9]) << 16);
        iValue |= ((0xFF & _data[10]) <<  8);
        iValue |=  (0xFF & _data[11]);
        dValue  = (double)iValue;
        iValue  = ((0xFF & _data[12]) << 24);
        iValue |= ((0xFF & _data[13]) << 16);
        iValue |= ((0xFF & _data[14]) <<  8);
        iValue |=  (0xFF & _data[15]);
        dValue += (double)iValue / (double)10e8;
        ptCoord.setY(dValue);
        
        return (ptCoord);
    }
    
}
