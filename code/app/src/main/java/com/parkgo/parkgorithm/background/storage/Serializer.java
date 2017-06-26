/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.storage;

/**
 *
 * @author Antony
 */
public interface Serializer {
    /**
     * Sets object to pack into buffer
     * @param _obj[in] object to pack
     * @return true if object passed can be packed else false.
     */
    public boolean setObjectToPack(Object _obj);
    
    /**
     * Transforms object into a array buffer
     * Data packed is determined by the constructor
     * @return byte array representing object passed
     */
    public byte[] pack();
    
    /**
     * Reverses pack method
     * @param _data[in] packed data
     * @return new instance of packed object
     */
    public Object unpack(byte[] _data);
}
