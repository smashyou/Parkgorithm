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

public class PreferenceBean implements Bean
{
  private Double idealParkingDurationLimit;
  private String userId;
  private String ownerId;
  private java.util.Date created;
  private Double distanceLimit;
  private java.util.Date updated;
  private Double priceLimit;
  private String objectId;
  private Double minimalUserRatingScore;

  public Double getIdealParkingDurationLimit()
  {
    return this.idealParkingDurationLimit;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public Double getDistanceLimit()
  {
    return this.distanceLimit;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public Double getPriceLimit()
  {
    return this.priceLimit;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public Double getMinimalUserRatingScore()
  {
    return this.minimalUserRatingScore;
  }


  public void setIdealParkingDurationLimit( Double idealParkingDurationLimit )
  {
    this.idealParkingDurationLimit = idealParkingDurationLimit;
  }

  public void setUserId( String userId )
  {
    this.userId = userId;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setDistanceLimit( Double distanceLimit )
  {
    this.distanceLimit = distanceLimit;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setPriceLimit( Double priceLimit )
  {
    this.priceLimit = priceLimit;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setMinimalUserRatingScore( Double minimalUserRatingScore )
  {
    this.minimalUserRatingScore = minimalUserRatingScore;
  }

  public PreferenceBean save()
  {
    return Backendless.Data.of( PreferenceBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( PreferenceBean.class ).remove( this );
  }

  public static PreferenceBean findById( String id )
  {
    return Backendless.Data.of( PreferenceBean.class ).findById( id );
  }

  public static PreferenceBean findFirst()
  {
    return Backendless.Data.of( PreferenceBean.class ).findFirst();
  }

  public static PreferenceBean findLast()
  {
    return Backendless.Data.of( PreferenceBean.class ).findLast();
  }
}