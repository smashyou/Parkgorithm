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

public class TransactionBean implements Bean
{
  private java.util.Date time;
  private String foreignContactId;
  private String objectId;
  private String ownerId;
  private java.util.Date updated;
  private String addressId;
  private java.util.Date created;
  private String accountId;
  private String accountState;
  private String ownContactId;
  private Boolean userResponse;
  private Boolean foreignResponse;

  public String getAccountState() { return this.accountState; }
  public Boolean getUserResponse() { return this.userResponse; }
  public Boolean getForeignResponse() { return this.foreignResponse; }
  public String getAccountId(){ return this.accountId; }

  public java.util.Date getTime()
  {
    return this.time;
  }

  public String getForeignContactId()
  {
    return this.foreignContactId;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getAddressId()
  {
    return this.addressId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getOwnContactId() { return (this.ownContactId); }

  public void setTime( java.util.Date time )
  {
    this.time = time;
  }

  public void setForeignContactId( String foreignContactId )
  {
    this.foreignContactId = foreignContactId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }


  public void setOwnContactId(String ownContactId) { this.ownContactId = ownContactId; }
  public void setAccountState(String accountState) { this.accountState = accountState; }
  public void setUserResponse(Boolean userResponse) { this.userResponse = userResponse; }
  public void setForeignResponse(Boolean foreignResponse) { this.foreignResponse = foreignResponse; }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setAddressId( String addressId )
  {
    this.addressId = addressId;
  }

  public void setAccountId(String accountId) { this.accountId = accountId; }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public TransactionBean save()
  {
    return Backendless.Data.of( TransactionBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( TransactionBean.class ).remove( this );
  }

  public static TransactionBean findById( String id )
  {
    return Backendless.Data.of( TransactionBean.class ).findById( id );
  }

  public static TransactionBean findFirst()
  {
    return Backendless.Data.of( TransactionBean.class ).findFirst();
  }

  public static TransactionBean findLast()
  {
    return Backendless.Data.of( TransactionBean.class ).findLast();
  }
}