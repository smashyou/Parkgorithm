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
import com.backendless.geo.GeoPoint;

public class AddressBean implements Bean
{
  private Boolean inUse;
  private String address;
  private java.util.Date updated;
  private String objectId;
  private java.util.Date created;
  private Integer regionid;
  private String streetName;
  private String parkingSpotId;
  private GeoPoint geographicalLocation;
  private String ownerId;
  private String catmsg;

  public Boolean getInUse()
  {
    return this.inUse;
  }

  public String getAddress()
  {
    return this.address;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public Integer getRegionid()
  {
    return this.regionid;
  }

  public String getStreetName()
  {
    return this.streetName;
  }

  public String getParkingSpotId()
  {
    return this.parkingSpotId;
  }

  public GeoPoint getGeographicalLocation()
  {
    return this.geographicalLocation;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public String getCatmsg() { return this.catmsg; }

  public void setInUse( Boolean inUse )
  {
    this.inUse = inUse;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setRegionid( Integer regionid )
  {
    this.regionid = regionid;
  }

  public void setStreetName( String streetName )
  {
    this.streetName = streetName;
  }

  public void setParkingSpotId( String parkingSpotId )
  {
    this.parkingSpotId = parkingSpotId;
  }

  public void setGeographicalLocation( GeoPoint geographicalLocation )
  {
    this.geographicalLocation = geographicalLocation;
  }

  public void setCatmsg(String catmsg){ this.catmsg = catmsg; }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public AddressBean save()
  {
    return Backendless.Data.of( AddressBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( AddressBean.class ).remove( this );
  }

  public static AddressBean findById( String id )
  {
    return Backendless.Data.of( AddressBean.class ).findById( id );
  }

  public static AddressBean findFirst()
  {
    return Backendless.Data.of( AddressBean.class ).findFirst();
  }

  public static AddressBean findLast()
  {
    return Backendless.Data.of( AddressBean.class ).findLast();
  }
}