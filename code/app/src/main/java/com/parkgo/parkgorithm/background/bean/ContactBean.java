/************************ PARKGORITHM CONFIDENTIAL **************************
 * NOTICE: All information contained herein is, and remains                 *
 * the property of Parkgorithm and its suppliers,                           *
 * if any. The intellectual and technical concepts contained                *
 * herein are proprietary of Parkgorithm                                    *
 * and its suppliers and may be covered by U.S. and Foreign Patents,        *
 * patents in process, and are protected by trade secret or copyright law.  *
 * Dissemination of this information or reproduction of this material       *
 * is strictly forbidden unless prior written permission is obtained        *
 * from Parkgorithm.                                                        *
 ****************************************************************************/
package com.parkgo.parkgorithm.background.bean;

import com.backendless.Backendless;

public class ContactBean implements Bean
{
  private String objectId;
  private java.util.Date created;
  private String ownerId;
  private java.util.Date updated;
  private String phoneNumber;
  private String userName;
  private String carId;
  private Double userScore;

  public String getObjectId()
  {
    return this.objectId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public Double getUserScore(){ return (this.userScore); }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public String getCarId(){ return this.carId; }


  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setUserScore(Double userScore) { this.userScore = userScore; }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setPhoneNumber( String phoneNumber )
  {
    this.phoneNumber = phoneNumber;
  }

  public void setUserName( String userName )
  {
    this.userName = userName;
  }

  public void setCarId(String carId) { this.carId = carId; }

  public ContactBean save()
  {
    return Backendless.Data.of( ContactBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( ContactBean.class ).remove( this );
  }

  public static ContactBean findById( String id )
  {
    return Backendless.Data.of( ContactBean.class ).findById( id );
  }

  public static ContactBean findFirst()
  {
    return Backendless.Data.of( ContactBean.class ).findFirst();
  }

  public static ContactBean findLast()
  {
    return Backendless.Data.of( ContactBean.class ).findLast();
  }
}