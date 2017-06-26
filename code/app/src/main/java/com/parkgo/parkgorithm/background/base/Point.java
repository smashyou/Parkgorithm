/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.base;

/**
 * Defines a 2D-Point
 * @author Antony
 * @param <T>[in] type
 */
public final class Point<T> {
    private T m_iX;
    private T m_iY;
    
    /**
     * Constructor
     * @param _iX[in] x-coordinate
     * @param _iY[in] y-coordinate
     */
    public Point(T _iX, T _iY){
        m_iX = _iX;
        m_iY = _iY;
    }
    
    /**
     * Getter methods
     */
    
    /**
     * 
     * @return Value represents x-coordinate
     */
    public T getX(){
        return (m_iX);
    }
    
    /**
     * 
     * @return value represents y-coordinate
     */
    public T getY(){
        return (m_iY);
    }
    
    /**
     * Setter Methods
     */
    
    /**
     * 
     * @param _iX[in] new x-coordinate
     */
    public void setX(T _iX){
        m_iX = _iX;
    }
    
    /**
     * 
     * @param _iY[in] new y-coordinate 
     */
    public void setY(T _iY){
        m_iY = _iY;
    }
}
