package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.lang.Integer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MiscService {
	private LoginService loginService = new LoginService();
	public String getPaymentType(Integer paymentId) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		String paymentType = "";

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT payment_type FROM Payment WHERE payment_id = " + paymentId + ";");
			if(rs.next()) {
				paymentType = rs.getString("payment_type");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}

		return paymentType;
	}
	
	public Location getLocation(String location) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Location locationInfo = new Location();
		Integer locationId = 0;
		locationId = Integer.parseInt(location);

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Location WHERE location_id = " + locationId + ";");
			if(rs.next()) {
				locationInfo = new Location(rs.getInt("location_id"), rs.getString("location_name"), rs.getString("location_city"), rs.getString("location_address"), rs.getInt("location_longitude"), rs.getInt("location_latitude"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}

		return locationInfo;
	}

	public Item getItem(Integer itemId) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Item item = new Item();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Item WHERE item_id = " + itemId + ";");
			if(rs.next()) {
				item = new Item(rs.getInt("item_id"), rs.getString("item_category"), rs.getString("item_details"), rs.getInt("item_weight"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}

		return item;
	}
}