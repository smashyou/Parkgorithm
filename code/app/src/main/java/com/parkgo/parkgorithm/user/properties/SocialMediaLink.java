/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.user.properties;

/**
 * Created by John
 * 
 */
public class SocialMediaLink implements SocialMediaLinkInterface{
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!SocialMediaLink.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SocialMediaLink other = (SocialMediaLink) obj;
        return true;
    }
}
