/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.base;

/**
 *
 * @author Antony
 */
public final class Pair<F, S> {
    private final F m_fFirst;
    private final S m_sSecond;
    
    /**
     * Constructor
     * @param _fFirst[in] object to store
     * @param _sSecond[in] object to store
     */
    public Pair(F _fFirst, S _sSecond){
        m_fFirst  = _fFirst;
        m_sSecond = _sSecond;
    }
    
    /**
     * @return first object contained in Pair<F,S>
     */
    public F getFirst(){
        return (m_fFirst);
    }
    
    /**
     * @return second object contained in Pair<F,S>
     */
    public S getSecond(){
        return (m_sSecond);
    }
}
