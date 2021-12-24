package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Profile {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer profileId;
  public String username;
  public String password;
  public String profileType;
  
	public Profile() {}
	
	public Profile(Integer profileId, String username, String password, String profileType) {
		this.profileId = profileId;
		this.username = username;
		this.password = password;
		this.profileType = profileType;
	}
	
  public Integer getProfileId() {
    return profileId;
  }

  public void setProfileId(Integer profileId) {
    this.profileId = profileId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
	
	public String getProfileType() {
    return profileType;
  }

  public void setProfileType(String profileType) {
    this.profileType = profileType;
  }
}
