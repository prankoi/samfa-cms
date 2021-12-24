package com.samfa.cms.models;

public class ReportDriver extends Report {
	public Integer driverId;
	
	public ReportDriver() {}
	
	public Integer getDriverId() {
		return driverId;
	}
	
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
}