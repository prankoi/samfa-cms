package com.samfa.cms.models.reports;

public class Weekly {
	public String startDate;
	public String endDate;
	public Integer bookingFee;
	public Integer totalIncome;
	public Integer totalBookings;
	
	public Weekly() {}
	
	public Weekly(String startDate, String endDate, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookingFee = bookingFee;
		this.totalIncome = totalIncome;
		this.totalBookings = totalBookings;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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