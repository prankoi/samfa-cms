package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Location {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
	
  private Integer locationId;
  public String locationName;
  public String locationCity;
  public String locationAddress;
	public Integer locationLongitude;
  public Integer locationLatitude;
  
	public Location() {}
	
	public Location(Integer locationId, String locationName, String locationCity, String locationAddress, Integer locationLongitude,
		Integer locationLatitude) {
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationCity = locationCity;
		this.locationAddress = locationAddress;
		this.locationLongitude = locationLongitude;
		this.locationLatitude = locationLatitude;
	}
	
  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLocationCity() {
    return locationCity;
  }

  public void setLocationCity(String locationCity) {
    this.locationCity = locationCity;
  }
	
	public String getLocationAddress() {
    return locationAddress;
  }

  public void setLocationAddress(String locationAddress) {
    this.locationAddress = locationAddress;
  }

  public Integer getLocationLongitude() {
    return locationLongitude;
  }

  public void setLocationLongitude(Integer locationLongitude) {
    this.locationLongitude = locationLongitude;
  }
	
	public Integer getLocationLatitude() {
    return locationLatitude;
  }

  public void setLocationLatitude(Integer locationLatitude) {
    this.locationLatitude = locationLatitude;
  }
}
