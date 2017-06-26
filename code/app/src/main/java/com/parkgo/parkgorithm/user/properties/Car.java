/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user.properties;


import com.parkgo.parkgorithm.background.factory.BeanFactory;
import java.util.Arrays;

import com.parkgo.parkgorithm.background.bean.Bean;
import com.parkgo.parkgorithm.background.bean.CarBean;

/**
 * Created by John
 * 
 */
public final class Car implements BeanFactory{
   private String m_strMake;
   private String m_strModel;
   private byte[] m_bColor;
   private int    m_iYear;
   private String m_strObjectId;

   /**
    * Constructor
    * @param _strMake [in] make
    * @param _strModel [in] model
    * @param _iYear [in] year
    * @param _bColor [in] color (rgb values)
    */
   public Car(String _strMake, String _strModel, int _iYear, byte[] _bColor){
       m_strMake  = _strMake;
       m_strModel = _strModel;
       m_iYear    = _iYear;
       
       try{
            m_bColor = new byte[] {_bColor[0], _bColor[1], _bColor[2]};
       }catch (Exception err){
           System.out.println("Error (FOR CAR COLOR): " + err.getMessage());
       }
   }
   
   /**
    * Copy Constructor
    * @param _Car [in] instance of Car
    */
   public Car(Car _Car){
       m_strMake  = _Car.getMake().substring(0);
       m_strModel = _Car.getModel().substring(0);
       m_iYear    = _Car.getYear();
       m_bColor   = Arrays.copyOfRange(_Car.getColor(), 0, 3);
   }

    /**
     * Constructor
     * @param _CarBean [in] bean object
     */
    public Car(CarBean _CarBean){
        m_strMake   = _CarBean.getMake();
        m_strModel  = _CarBean.getModel();
        m_iYear     = new Integer(_CarBean.getYear());
        m_bColor    = _CarBean.getColor().getBytes();
        m_strObjectId = _CarBean.getObjectId();
    }

    /**
     * Create new bean object
     * @return instance of a new PreferenceBean
     */
    @Override
    public Bean newBeanInstance(){
        CarBean carBean = new CarBean();
        carBean.setMake(m_strMake);
        carBean.setModel(m_strModel);
        carBean.setYear("" + m_iYear);
        carBean.setColor(new String(m_bColor));
        return (carBean);
    }

   /**
    * 
    * @return CarBean make
    */
   final public String getMake(){
       return (m_strMake);
   }
   
   /**
    * 
    * @return CarBean model
    */
   final public String getModel(){
       return (m_strModel);
   }
   
   /**
    * 
    * @return CarBean year
    */
   final public int getYear(){
       return (m_iYear);
   }
   
   /**
    * 
    * @return CarBean color (rgb values).
    */
   final public byte[] getColor(){
       return (m_bColor);
   }
   
   /**
     * Every field in a Car object must match another Car object for them to be equal because there are many
     * of the same CarBean. Ideally VIN would be used but most user's will not be able to find this.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Car.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        
        final Car other = (Car) obj;
        
        // Check make use XOR
        if ((m_strMake == null) ^ (other.m_strMake == null)){
                return (false);
        }else{
            // If this make is null so is the second based on prev comp
            if (m_strMake != null){
                // Check make
                if (!m_strMake.equalsIgnoreCase(other.m_strMake)) 
                    return (false);
            }
        }
        
        // Check model use XOR
        if ((m_strModel == null) ^ (other.m_strModel == null)){
                return (false);
        }else{
            // If this model is null so is the second based on prev comp
            if (m_strModel != null){
                // Check model
                if (!m_strModel.equalsIgnoreCase(other.m_strModel)) 
                    return (false);
            }
        }
        
        // Check color use XOR
        if ((m_bColor == null) ^ (other.m_bColor == null)){
                return (false);
        }else{
            // If this color is null so is the second based on prev comp
            if (m_bColor != null){
                // Check color
                for (int i = 0; i < 3; i++){
                   if (m_bColor[i] != other.m_bColor[i])
                       return (false);
                }
            }
        }
        
        // check CarBean year
        return (m_iYear != other.m_iYear);
    }
}
