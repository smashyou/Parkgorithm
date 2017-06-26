/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user;

import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.user.properties.UserState;

/**
 * Created by John
 *
 */
public class Wanderer extends User{
    public Wanderer(Info _info){
        super(_info, UserState.USER_WANDERER);
    }
}
