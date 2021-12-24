package com.samfa.cms.models;

public class User {
  private Integer userId;
  public String username;
  public String password;
  public String profileType;
	public boolean isLoggedIn;
  
	public User() {}
	
	public User(Integer userId, String username, String password, String profileType, boolean isLoggedIn) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.profileType = profileType;
		this.isLoggedIn = isLoggedIn;
	}
	
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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
	
	public boolean getLoginStatus() {
    return isLoggedIn;
  }

  public void setLoginStatus(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }
}
