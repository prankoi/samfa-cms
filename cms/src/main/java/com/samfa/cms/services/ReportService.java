package com.samfa.cms.services;

import com.samfa.cms.models.reports.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportService {
	private LoginService loginService = new LoginService();
	public Integer totalCashPaymentTrip() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Integer totalCashPayment = 0;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT SUM(service_fee) as total_cash FROM Trip WHERE payment_id = 1");
			if(rs.next()) {
				totalCashPayment = rs.getInt("total_cash");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return totalCashPayment;
	}
	
	public Integer totalCashPaymentDelivery() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Integer totalCashPayment = 0;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT SUM(service_fee) as total_cash FROM Delivery WHERE payment_id = 1");
			if(rs.next()) {
				totalCashPayment = rs.getInt("total_cash");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return totalCashPayment;
	}
	
	public Integer totalCardPaymentTrip() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Integer totalCardPayment = 0;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT SUM(service_fee) as total_card FROM Trip WHERE payment_id = 2");
			if(rs.next()) {
				totalCardPayment = rs.getInt("total_card");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return totalCardPayment;
	}
	
	public Integer totalCardPaymentDelivery() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Integer totalCardPayment = 0;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT SUM(service_fee) as total_card FROM Delivery WHERE payment_id = 2");
			if(rs.next()) {
				totalCardPayment = rs.getInt("total_card");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return totalCardPayment;
	}
	
	public Integer totalCashPayment() throws Exception {
		Integer totalCashPayment = 0;
		totalCashPayment = totalCashPaymentTrip() + totalCashPaymentDelivery();
		return totalCashPayment;
	}
	
	public Integer totalCardPayment() throws Exception {
		Integer totalCardPayment = 0;
		totalCardPayment = totalCardPaymentTrip() + totalCardPaymentDelivery();
		return totalCardPayment;
	}
	
	public ArrayList<Daily> getDailyIncomeTrip() throws Exception {
		ArrayList<Daily> dailies = new ArrayList<Daily>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(confirmation_date, '%m/%d/%Y') AS date, SUM(booking_fee) AS booking_fee,"
				+ "SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip GROUP BY DAY(confirmation_date);");
			while(rs.next()) {
				dailies.add( new Daily(rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<Weekly> getWeeklyIncomeTrip() throws Exception {
		ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y')"
				+ " AS start_date, DATE_FORMAT(date_add(confirmation_date, interval(7-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y') AS end_date,"
				+ " SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip GROUP BY"
				+ " WEEK(confirmation_date);");
			while(rs.next()) {
				weeklies.add( new Weekly(rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<Monthly> getMonthlyIncomeTrip() throws Exception {
		ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date) AS year, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip GROUP BY MONTH(confirmation_date);");
			while(rs.next()) {
				monthlies.add( new Monthly(rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
	
	public ArrayList<Daily> getDailyIncomeDelivery() throws Exception {
		ArrayList<Daily> dailies = new ArrayList<Daily>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(confirmation_date, '%m/%d/%Y') AS date, SUM(booking_fee) AS booking_fee,"
				+ "SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery GROUP BY DAY(confirmation_date);");
			while(rs.next()) {
				dailies.add( new Daily(rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<Weekly> getWeeklyIncomeDelivery() throws Exception {
		ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y')"
				+ " AS start_date, DATE_FORMAT(date_add(confirmation_date, interval(7-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y') AS end_date,"
				+ " SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery GROUP BY"
				+ " WEEK(confirmation_date);");
			while(rs.next()) {
				weeklies.add( new Weekly(rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<Monthly> getMonthlyIncomeDelivery() throws Exception {
		ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date) AS year, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery GROUP BY MONTH(confirmation_date);");
			while(rs.next()) {
				monthlies.add( new Monthly(rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
	
		public ArrayList<Daily> getDailyIncome() throws Exception {
		ArrayList<Daily> dailies = new ArrayList<Daily>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(t.confirmation_date, '%m/%d/%Y') AS date, SUM(t.booking_fee) AS booking_fee,"
				+ " SUM(t.service_fee) AS total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT confirmation_date, SUM(booking_fee) AS"
				+ " booking_fee, SUM(service_fee) AS service_fee, COUNT(*) AS total_bookings FROM Trip GROUP BY DAY(confirmation_date) union SELECT"
				+ " confirmation_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS service_fee, COUNT(*) AS total_bookings FROM Delivery"
				+ " GROUP BY DAY(confirmation_date)) t GROUP BY DAY(t.confirmation_date);");
			while(rs.next()) {
				dailies.add( new Daily(rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<Weekly> getWeeklyIncome() throws Exception {
		ArrayList<Weekly> weeklies = new ArrayList<Weekly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(t.start_date, '%m/%d/%Y') AS start_date, DATE_FORMAT(t.end_date, '%m/%d/%Y') AS end_date,"
				+ " SUM(t.booking_fee) AS booking_fee, SUM(t.total_income) AS total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT"
				+ " date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY) AS start_date, date_add(confirmation_date,"
				+ " interval(7-DAYofWEEK(confirmation_date)) DAY) AS end_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income,"
				+ " COUNT(*) AS total_bookings FROM Trip GROUP BY WEEK(confirmation_date) union SELECT date_add(confirmation_date,"
				+ " interval(1-DAYofWEEK(confirmation_date)) DAY) AS start_date, date_add(confirmation_date,"
				+ " interval(7-DAYofWEEK(confirmation_date)) DAY) AS end_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income,"
				+ " COUNT(*) AS total_bookings FROM Delivery GROUP BY WEEK(confirmation_date)) t GROUP BY WEEK(t.start_date);");
			while(rs.next()) {
				weeklies.add( new Weekly(rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<Monthly> getMonthlyIncome() throws Exception {
		ArrayList<Monthly> monthlies = new ArrayList<Monthly>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT t.month AS month, t.year AS year, SUM(t.booking_fee) AS booking_fee, SUM(t.total_income) AS"
				+ " total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date)"
				+ " AS year, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip GROUP BY"
				+ " MONTH(confirmation_date) union SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date) AS year, SUM(booking_fee) AS"
				+ " booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery GROUP BY MONTH(confirmation_date)) t"
				+ " GROUP BY t.month;");
			while(rs.next()) {
				monthlies.add( new Monthly(rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
	
	
	//Income Report - Driver
	public ArrayList<DailyDriver> getDailyIncomeTripByDriver(Integer driverId) throws Exception {
		ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(confirmation_date, '%m/%d/%Y') AS date, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip WHERE driver_id = " + driverId + " GROUP BY"
				+ " DAY(confirmation_date);");
			while(rs.next()) {
				dailies.add( new DailyDriver(driverId, rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<WeeklyDriver> getWeeklyIncomeTripByDriver(Integer driverId) throws Exception {
		ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y')"
				+ " AS start_date, DATE_FORMAT(date_add(confirmation_date, interval(7-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y') AS end_date,"
				+ " SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip WHERE driver_id = "
				+ driverId + " GROUP BY WEEK(confirmation_date);");
			while(rs.next()) {
				weeklies.add( new WeeklyDriver(driverId, rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<MonthlyDriver> getMonthlyIncomeTripByDriver(Integer driverId) throws Exception {
		ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date) AS year, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip WHERE driver_id = " + driverId + " GROUP BY"
				+ " MONTH(confirmation_date);");
			while(rs.next()) {
				monthlies.add( new MonthlyDriver(driverId, rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
	
	public ArrayList<DailyDriver> getDailyIncomeDeliveryByDriver(Integer driverId) throws Exception {
		ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(confirmation_date, '%m/%d/%Y') AS date, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery WHERE driver_id = " + driverId + " GROUP BY"
				+ " DAY(confirmation_date);");
			while(rs.next()) {
				dailies.add( new DailyDriver(driverId, rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<WeeklyDriver> getWeeklyIncomeDeliveryByDriver(Integer driverId) throws Exception {
		ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y')"
				+ " AS start_date, DATE_FORMAT(date_add(confirmation_date, interval(7-DAYofWEEK(confirmation_date)) DAY), '%m/%d/%Y') AS end_date,"
				+ " SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery WHERE driver_id = "
				+ driverId + " GROUP BY WEEK(confirmation_date);");
			while(rs.next()) {
				weeklies.add( new WeeklyDriver(driverId, rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<MonthlyDriver> getMonthlyIncomeDeliveryByDriver(Integer driverId) throws Exception {
		ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date) AS year, SUM(booking_fee) AS booking_fee,"
				+ " SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Delivery WHERE driver_id = " + driverId + " GROUP BY"
				+ " MONTH(confirmation_date);");
			while(rs.next()) {
				monthlies.add( new MonthlyDriver(driverId, rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
	
	public ArrayList<DailyDriver> getDailyIncomeByDriver(Integer driverId) throws Exception {
		ArrayList<DailyDriver> dailies = new ArrayList<DailyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(t.confirmation_date, '%m/%d/%Y') AS date, SUM(t.booking_fee) AS booking_fee,"
				+ " SUM(t.service_fee) AS total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT confirmation_date, SUM(booking_fee) AS"
				+ " booking_fee, SUM(service_fee) AS service_fee, COUNT(*) AS total_bookings FROM Trip WHERE driver_id = " + driverId + " GROUP BY"
				+ " DAY(confirmation_date) union SELECT confirmation_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS service_fee,"
				+ " COUNT(*) AS total_bookings FROM Delivery WHERE driver_id = " + driverId + " GROUP BY DAY(confirmation_date)) t GROUP BY"
				+ " DAY(t.confirmation_date);");
			while(rs.next()) {
				dailies.add( new DailyDriver(driverId, rs.getString("date"), rs.getInt("booking_fee"), rs.getInt("total_income"),
				rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return dailies;
	}
	
	public ArrayList<WeeklyDriver> getWeeklyIncomeByDriver(Integer driverId) throws Exception {
		ArrayList<WeeklyDriver> weeklies = new ArrayList<WeeklyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT DATE_FORMAT(t.start_date, '%m/%d/%Y') AS start_date, DATE_FORMAT(t.end_date, '%m/%d/%Y') AS end_date,"
				+ " SUM(t.booking_fee) AS booking_fee, SUM(t.total_income) AS total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT"
				+ " date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY) AS start_date, date_add(confirmation_date,"
				+ " interval(7-DAYofWEEK(confirmation_date)) DAY) AS end_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income,"
				+ " COUNT(*) AS total_bookings FROM Trip WHERE driver_id = " + driverId + " GROUP BY WEEK(confirmation_date) union SELECT"
				+ " date_add(confirmation_date, interval(1-DAYofWEEK(confirmation_date)) DAY) AS start_date, date_add(confirmation_date,"
				+ " interval(7-DAYofWEEK(confirmation_date)) DAY) AS end_date, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income,"
				+ " COUNT(*) AS total_bookings FROM Delivery WHERE driver_id = " + driverId + " GROUP BY WEEK(confirmation_date)) t GROUP BY"
				+ " WEEK(t.start_date);");
			while(rs.next()) {
				weeklies.add( new WeeklyDriver(driverId, rs.getString("start_date"), rs.getString("end_date"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return weeklies;
	}
	
	public ArrayList<MonthlyDriver> getMonthlyIncomeByDriver(Integer driverId) throws Exception {
		ArrayList<MonthlyDriver> monthlies = new ArrayList<MonthlyDriver>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT t.month AS month, t.year AS year, SUM(t.booking_fee) AS booking_fee, SUM(t.total_income) AS"
				+ " total_income, SUM(t.total_bookings) AS total_bookings FROM (SELECT MONTH(confirmation_date) AS month, YEAR(confirmation_date)"
				+ " AS year, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings FROM Trip WHERE"
				+ " driver_id = " + driverId + " GROUP BY MONTH(confirmation_date) union SELECT MONTH(confirmation_date) AS month,"
				+ " YEAR(confirmation_date) AS year, SUM(booking_fee) AS booking_fee, SUM(service_fee) AS total_income, COUNT(*) AS total_bookings"
				+ " FROM Delivery WHERE driver_id = " + driverId + " GROUP BY MONTH(confirmation_date)) t GROUP BY t.month;");
			while(rs.next()) {
				monthlies.add( new MonthlyDriver(driverId, rs.getString("month"), rs.getString("year"), rs.getInt("booking_fee"),
				rs.getInt("total_income"), rs.getInt("total_bookings")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return monthlies;
	}
}