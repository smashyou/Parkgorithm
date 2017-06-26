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

public class AccountBean implements Bean
{
  private String preferenceId;
  private String lastName;
  private String ownerId;
  private Integer userState;
  private java.util.Date updated;
  private String creditCardNumber;
  private String objectId;
  private java.util.Date created;
  private String firstName;
  private String rewardId;
  private String contactId;

  public String getPreferenceId()
  {
    return this.preferenceId;
  }

  public String getLastName()
  {
    return this.lastName;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public Integer getUserState()
  {
    return this.userState;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getCreditCardNumber()
  {
    return this.creditCardNumber;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getFirstName()
  {
    return this.firstName;
  }

  public String getRewardId()
  {
    return this.rewardId;
  }

  public String getContactId()
  {
    return this.contactId;
  }

  public void setPreferenceId( String preferenceId )
  {
    this.preferenceId = preferenceId;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setUserState( Integer userState )
  {
    this.userState = userState;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setCreditCardNumber( String creditCardNumber )
  {
    this.creditCardNumber = creditCardNumber;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  public void setRewardId( String rewardId )
  {
    this.rewardId = rewardId;
  }

  public void setContactId( String contactId )
  {
    this.contactId = contactId;
  }

  public AccountBean save()
  {
    return Backendless.Data.of( AccountBean.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( AccountBean.class ).remove( this );
  }

  public static AccountBean findById( String id )
  {
    return Backendless.Data.of( AccountBean.class ).findById( id );
  }

  public static AccountBean findFirst()
  {
    return Backendless.Data.of( AccountBean.class ).findFirst();
  }

  public static AccountBean findLast()
  {
    return Backendless.Data.of( AccountBean.class ).findLast();
  }
}