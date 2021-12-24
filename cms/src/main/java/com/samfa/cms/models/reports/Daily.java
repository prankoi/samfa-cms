package com.samfa.cms.models.reports;

public class Daily {
	public String confirmationDate;
	public Integer bookingFee;
	public Integer totalIncome;
	public Integer totalBookings;
	
	public Daily() {}
	
	public Daily(String confirmationDate, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.confirmationDate = confirmationDate;
		this.bookingFee = bookingFee;
		this.totalIncome = totalIncome;
		this.totalBookings = totalBookings;
	}
	
	public String getConfirmationDate() {
		return confirmationDate;
	}
	
	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
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