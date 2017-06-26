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

public class CarBean implements Bean
{
  private String color;
  private String model;
  private String ownerId;
  private String objectId;
  private String make;
  private java.util.Date updated;
  private java.util.Date created;
  private String year;

  public String getColor()
  {
    return this.color;
  }

  public String getModel()
  {
    return this.model;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public String getMake()
  {
    return this.make;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getYear()
  {
    return this.year;
  }


  public void setColor( String color )
  {
    this.color = color;
  }

  public void setModel( String model )
  {
    this.model = model;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setMake( String make )
  {
    this.make = make;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setYear( String year )
  {
    this.year = year;
  }

  public CarBean save()
  {
    return Backendless.Data.of( CarBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( CarBean.class ).remove( this );
  }

  public static CarBean findById( String id )
  {
    return Backendless.Data.of( CarBean.class ).findById( id );
  }

  public static CarBean findFirst()
  {
    return Backendless.Data.of( CarBean.class ).findFirst();
  }

  public static CarBean findLast()
  {
    return Backendless.Data.of( CarBean.class ).findLast();
  }
}