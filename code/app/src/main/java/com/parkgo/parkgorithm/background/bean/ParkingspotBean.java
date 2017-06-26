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

public class ParkingspotBean implements Bean
{
  private java.util.Date created;
  private String addressId;
  private Integer regionId;
  private String parkingAvailableTimeId;
  private String objectId;
  private String transactionId;
  private String ownerId;
  private java.util.Date updated;
  private Double cost;

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getAddressId()
  {
    return this.addressId;
  }

  public Integer getRegionId()
  {
    return this.regionId;
  }

  public String getParkingAvailableTimeId()
  {
    return this.parkingAvailableTimeId;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public String getTransactionId()
  {
    return this.transactionId;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public Double getCost()
  {
    return this.cost;
  }


  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setAddressId( String addressId )
  {
    this.addressId = addressId;
  }

  public void setRegionId( Integer regionId )
  {
    this.regionId = regionId;
  }

  public void setParkingAvailableTimeId( String parkingAvailableTimeId )
  {
    this.parkingAvailableTimeId = parkingAvailableTimeId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setTransactionId( String transactionId )
  {
    this.transactionId = transactionId;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setCost( Double cost )
  {
    this.cost = cost;
  }

  public ParkingspotBean save()
  {
    return Backendless.Data.of( ParkingspotBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( ParkingspotBean.class ).remove( this );
  }

  public static ParkingspotBean findById( String id )
  {
    return Backendless.Data.of( ParkingspotBean.class ).findById( id );
  }

  public static ParkingspotBean findFirst()
  {
    return Backendless.Data.of( ParkingspotBean.class ).findFirst();
  }

  public static ParkingspotBean findLast()
  {
    return Backendless.Data.of( ParkingspotBean.class ).findLast();
  }
}