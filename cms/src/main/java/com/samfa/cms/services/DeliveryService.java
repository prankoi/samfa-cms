package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeliveryService {
	private LoginService loginService = new LoginService();
	public ArrayList<Delivery> getAllDeliveries() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"), rs.getInt("service_fee"),
				rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"), rs.getInt("distance")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return deliveries;
	}
	
	public Delivery getDeliveryById(String referenceNumber) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Delivery delivery = new Delivery();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE delivery_reference_number = '" + referenceNumber + "';");
			if(rs.next()) {
				delivery = new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"), rs.getInt("service_fee"),
				rs.getInt("amount_paid"), rs.getInt("promo_id"), rs.getInt("payment_id"), rs.getString("status"), rs.getInt("distance"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return delivery;
	}
	
	public void deleteDeliveryById(String referenceNumber) throws Exception {
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			stmt.executeUpdate("DELETE FROM Delivery WHERE delivery_reference_number = '" + referenceNumber + "';");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
	}
	
	//For Sorting and Filter
	public ArrayList<Delivery> getNewestAndCash() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE payment_id = 1 ORDER BY confirmation_date DESC");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getNewestAndCard() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE payment_id = 2 ORDER BY confirmation_date DESC");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getNewestAndConfirmed() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'confirmed' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getNewestAndCancelled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'cancelled' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getNewestAndFulfilled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'fulfilled' ORDER BY confirmation_date DESC");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getNewestAndAll() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery ORDER BY confirmation_date DESC;");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndCash() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE payment_id = 1 ORDER BY confirmation_date;");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndCard() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE payment_id = 2 ORDER BY confirmation_date");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndConfirmed() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'confirmed' ORDER BY confirmation_date");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndCancelled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'cancelled' ORDER BY confirmation_date");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndFulfilled() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery WHERE status = 'fulfilled' ORDER BY confirmation_date");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
	
	public ArrayList<Delivery> getOldestAndAll() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Delivery ORDER BY confirmation_date;");
			while(rs.next()) {
				deliveries.add( new Delivery(rs.getString("delivery_reference_number"), rs.getInt("customer_id"), rs.getInt("driver_id"),
				rs.getInt("vehicle_id"), rs.getString("confirmation_date"), rs.getString("booking_date"), rs.getString("fulfillment_date"),
				rs.getString("pickup_location"), rs.getString("dropoff_location"), rs.getString("landmarks"), rs.getInt("item_id"),
				rs.getString("recipient_name"), rs.getString("recipient_contact"), rs.getInt("booking_fee"),
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
		
		return deliveries;
	}
}