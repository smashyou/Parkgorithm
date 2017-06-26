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

public class RewardBean implements Bean
{
  private String userId;
  private java.util.Date created;
  private java.util.Date updated;
  private String ownerId;
  private String reward;
  private String objectId;

  public String getUserId()
  {
    return this.userId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public String getReward()
  {
    return this.reward;
  }

  public String getObjectId()
  {
    return this.objectId;
  }


  public void setUserId( String userId )
  {
    this.userId = userId;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setReward( String reward )
  {
    this.reward = reward;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public RewardBean save()
  {
    return Backendless.Data.of( RewardBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( RewardBean.class ).remove( this );
  }

  public static RewardBean findById( String id )
  {
    return Backendless.Data.of( RewardBean.class ).findById( id );
  }

  public static RewardBean findFirst()
  {
    return Backendless.Data.of( RewardBean.class ).findFirst();
  }

  public static RewardBean findLast()
  {
    return Backendless.Data.of( RewardBean.class ).findLast();
  }
}