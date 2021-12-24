package com.samfa.cms.models.reports;

public class MonthlyDriver extends Monthly {
	public Integer driverId;
	
	public MonthlyDriver() {}
	
	public MonthlyDriver(Integer driverId, String month, String year, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.driverId = driverId;
		this.setMonth(month);
		this.setYear(year);
		this.setBookingFee(bookingFee);
		this.setTotalIncome(totalIncome);
		this.setTotalBookings(totalBookings);
	}
	
	public Integer getDriverId() {
		return driverId;
	}
	
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
}