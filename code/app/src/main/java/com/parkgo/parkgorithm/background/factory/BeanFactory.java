package com.parkgo.parkgorithm.background.factory;


import com.parkgo.parkgorithm.background.bean.Bean;

/**
 * Created by Antony Lulciuc on 7/3/2016.
 */
public interface BeanFactory {
    /**
     * Create new bean object
     * @return instance of a new bean object
     */
    public Bean newBeanInstance();
}
