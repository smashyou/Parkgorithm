/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.parking;

import com.parkgo.parkgorithm.user.properties.Preference;
import com.parkgo.parkgorithm.user.properties.Weekday;
import com.parkgo.parkgorithm.background.factory.BeanFactory;
import com.parkgo.parkgorithm.background.bean.Bean;
import com.parkgo.parkgorithm.background.bean.ParkinghoursBean;

/**
 *
 * @author Antony
 */
final public class ParkingHours implements BeanFactory{
    /**
     * 7x4 array. The seven rows represent days of the week
     * Column 1 := start time
     * Column 2 := end time
     * Column 3 := hours allowed to park between interval .
     * Column 4 := hours allowed to park after interval.
     */
    private double[][] m_dTimeInterval = {{1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME},
                                          {1.0,24.0, Preference.MAX_TIME, Preference.MAX_TIME}};


    /**
     * Create new bean object
     * @return instance of a new bean object
     */
    @Override
    public Bean newBeanInstance(){
        ParkinghoursBean phb = new ParkinghoursBean();

        // MONDAY
        phb.setMondayInterval(m_dTimeInterval[0][0] + "&" + m_dTimeInterval[0][1]);
        phb.setMondayParkingDuringInterval(m_dTimeInterval[0][2]);
        phb.setMondayParkingAfterInterval(m_dTimeInterval[0][3]);

        // TUESDAY
        phb.setTuesdayInterval(m_dTimeInterval[1][0] + "&" + m_dTimeInterval[1][1]);
        phb.setTuesdayParkingDuringInterval(m_dTimeInterval[1][2]);
        phb.setTuesdayParkingAfterInterval(m_dTimeInterval[1][3]);

        // WEDNESDAY
        phb.setWednesdayInterval(m_dTimeInterval[2][0] + "&" + m_dTimeInterval[2][1]);
        phb.setWednesdayParkingDuringInterval(m_dTimeInterval[2][2]);
        phb.setWednesdayParkingAfterInterval(m_dTimeInterval[2][3]);

        // THURSDAY
        phb.setThursdayInterval(m_dTimeInterval[3][0] + "&" + m_dTimeInterval[3][1]);
        phb.setThursdayParkingDuringInterval(m_dTimeInterval[3][2]);
        phb.setThursdayParkingAfterInterval(m_dTimeInterval[3][3]);

        // FRIDAY
        phb.setFridayInterval(m_dTimeInterval[4][0] + "&" + m_dTimeInterval[4][1]);
        phb.setFridayParkingDuringInterval(m_dTimeInterval[4][2]);
        phb.setFridayParkingAfterInterval(m_dTimeInterval[4][3]);

        // SATURDAY
        phb.setSaturdayInterval(m_dTimeInterval[5][0] + "&" + m_dTimeInterval[5][1]);
        phb.setSaturdayParkingDuringInterval(m_dTimeInterval[5][2]);
        phb.setSaturdayParkingAfterInterval(m_dTimeInterval[5][3]);

        // SUNDAY
        phb.setSundayInterval(m_dTimeInterval[6][0] + "&" + m_dTimeInterval[6][1]);
        phb.setSundayParkingDuringInterval(m_dTimeInterval[6][2]);
        phb.setSundayParkingAfterInterval(m_dTimeInterval[6][3]);

        return (phb);
    }


    /**
     * Acquire time interval for specific day
     * @param _Day [in] desired days time interval
     * @return single dimensional array with four elements: 
     *                  [start time, end time, parking limit during, parking limit after]
     */
    public double[] getTime(Weekday _Day){
        int index = _Day.ordinal();
        return (new double[]{m_dTimeInterval[index][0], m_dTimeInterval[index][1], 
                             m_dTimeInterval[index][2], m_dTimeInterval[index][3]});
    }
    
    /**
     * Acquire times for multiple days
     * @param _Start [in] start day IF set to Weekday.ALL then returns every day.
     * @param _End [in] end day IF set to Weekday.ALL then returns every day.
     * @return times during weekdays specified. IF _Start >= _End then returns
     *         null.
     */
    public double[][] getTime(Weekday _Start, Weekday _End){
        if (_Start == Weekday.ALL || _End == Weekday.ALL){
            return (new double[][]{m_dTimeInterval[0], m_dTimeInterval[1],
                                   m_dTimeInterval[2], m_dTimeInterval[3],
                                   m_dTimeInterval[4], m_dTimeInterval[5],
                                   m_dTimeInterval[6]                     });
        }else{
            double[][] dTimeIntervals;
            int iStart = _Start.ordinal();
            int iEnd   = _End.ordinal();
            
            // Check for valid ordering
            if (iStart >= iEnd || iEnd > 6)
                return (null);
            
            // Initialize time intervals
            dTimeIntervals = new double[iEnd - iStart][0];
            
            // Copy data
            for (int i = iStart; i <= iEnd; i++){
                dTimeIntervals[i - iStart] = m_dTimeInterval[i];
            }
            
            return (dTimeIntervals);
        }
    }
    
    /**
     * Set the time interval for a particular day
     * @param _Day [in] which day to set
     * @param _dStart [in] start time
     * @param _dEnd [in] end time
     * @param _dTime [in] parking time during interval
     * @param _dRem [in] parking time not during interval
     * @return true if the time was successfully set else false.
     */
    public boolean setTime(Weekday _Day, double _dStart, double _dEnd, double _dTime, double _dRem){
        if (_dStart >= 1.0 && _dEnd <= 24.0 && _dStart < _dEnd &&
                _Day.ordinal() < 7){
            m_dTimeInterval[_Day.ordinal()][0] = _dStart;
            m_dTimeInterval[_Day.ordinal()][1] = _dEnd;
            
            if (_dTime > 0.0){
                m_dTimeInterval[_Day.ordinal()][2] = _dTime < Preference.MAX_TIME ? _dTime : Preference.MAX_TIME;
            }
            
            if (_dRem >= 0.0){
                m_dTimeInterval[_Day.ordinal()][3] = _dRem < Preference.MAX_TIME ? _dRem : Preference.MAX_TIME;
            }
            
            return (true);
        }
        return (false);
    }
    
    /**
     * Set the time interval for a particular day
     * @param _Day [in] which day to set
     * @param _iIdx [in] range [0-3] : 0 := start time.
     *                                1 := end time.
     *                                2 := total time can park during interval.
     *                                3 := total time can park after interval.
     * @return true if the time was successfully set else false.
     */
    public boolean setTime(Weekday _Day, double _dTime, int _iIdx){
        if (_iIdx < 4 && _dTime >= 1.0 && _dTime <= 24.0 && _Day.ordinal() < 7){
            m_dTimeInterval[_Day.ordinal()][_iIdx] = _dTime;
            return (true);
        }
        return (false);
    }
    
    /**
     * Determines if objects are equal
     * @param _obj [in] object in question.
     * @return true if objects are equal else false.
     */
    public boolean equals(Object _obj){
        // ensure argument passed is not null
        if (_obj == null)
            return (false);
        
        // ensure types are compatible
        if (ParkingHours.class.isAssignableFrom(_obj.getClass()) == false)
            return (false);
        
        final ParkingHours pkArg = (ParkingHours)_obj;
        
        // Check time intervals, ensure they are the same.
        for (int i = 0; i < 7; i++){
            
            if (Math.abs(m_dTimeInterval[i][0] - pkArg.m_dTimeInterval[i][0]) > 0.1)
                return (false);
            
            if (Math.abs(m_dTimeInterval[i][1] - pkArg.m_dTimeInterval[i][1]) > 0.1)
                return (false);
           
            if (Math.abs(m_dTimeInterval[i][2] - pkArg.m_dTimeInterval[i][2]) > 0.1)
                return (false);
            
            if (Math.abs(m_dTimeInterval[i][3] - pkArg.m_dTimeInterval[i][3]) > 0.1)
                return (false);
        }
        
        return (true);
    }
    
    
}
