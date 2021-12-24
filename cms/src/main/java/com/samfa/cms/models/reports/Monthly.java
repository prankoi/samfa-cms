package com.samfa.cms.models.reports;

public class Monthly {
	public String month;
	public String year;
	public Integer bookingFee;
	public Integer totalIncome;
	public Integer totalBookings;
	
	public Monthly() {}
	
	public Monthly(String month, String year, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.month = month;
		this.year = year;
		this.bookingFee = bookingFee;
		this.totalIncome = totalIncome;
		this.totalBookings = totalBookings;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public Integer getBookingFee() {
		return bookingFee;
	}
	
	public void setBookingFee(Integer bookingFee) {
		this.bookingFee = bookingFee;
	}
	
	public Integer getTotalIncome() {
		return totalIncome;
	}
	
	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
	}
	
	public Integer getTotalBookings() {
		return totalBookings;
	}
	
	public void setTotalBookings(Integer totalBookings) {
		this.totalBookings = totalBookings;
	}
}