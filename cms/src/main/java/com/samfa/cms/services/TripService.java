package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TripService {
	private LoginService loginService = new LoginService();
	public ArrayList<Trip> getAllTrips() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip;");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public Trip getTripById(String referenceNumber) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Trip trip = new Trip();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE trip_reference_number = '" + referenceNumber + "';");
			if(rs.next()) {
				trip = new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trip;
	}
	
	public void deleteTripById(String referenceNumber) throws Exception {
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			stmt.executeUpdate("DELETE FROM Trip WHERE trip_reference_number = '" + referenceNumber + "';");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
	}
	
	//For Sorting and Filter
	public ArrayList<Trip> getNewestAndCash() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE payment_id = 1 ORDER BY confirmation_date DESC");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getNewestAndCard() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE payment_id = 2 ORDER BY confirmation_date DESC");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getNewestAndConfirmed() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'confirmed' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getNewestAndCancelled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'cancelled' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getNewestAndFulfilled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'fulfilled' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getNewestAndAll() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip ORDER BY confirmation_date DESC;");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndCash() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE payment_id = 1 ORDER BY confirmation_date;");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndCard() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE payment_id = 2 ORDER BY confirmation_date");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndConfirmed() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'confirmed' ORDER BY confirmation_date");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndCancelled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'cancelled' ORDER BY confirmation_date");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndFulfilled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip WHERE status = 'fulfilled' ORDER BY confirmation_date");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
	
	public ArrayList<Trip> getOldestAndAll() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Trip ORDER BY confirmation_date;");
			while(rs.next()) {
				trips.add( new Trip(rs.getString("trip_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("booking_fee"),
				rs.getInt("service_fee"), rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"),
				rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return trips;
	}
}