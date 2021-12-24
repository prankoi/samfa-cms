package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DriverService {
	private LoginService loginService = new LoginService();
	public ArrayList<Driver> getAllDrivers() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Driver> drivers = new ArrayList<Driver>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT d.driver_id, d.vehicle_id, v.brand, v.model, v.plate_number, d.first_name, d.last_name, "
				+ "d.email_address, d.cellphone_number, d.password FROM Driver as d INNER JOIN Vehicle as v ON d.vehicle_id = v.vehicle_id");
			while(rs.next()) {
				drivers.add( new Driver(rs.getInt("d.driver_id"), rs.getInt("d.vehicle_id"), rs.getString("v.brand") + " " +
				rs.getString("v.model") + " " + rs.getString("v.plate_number"), rs.getString("d.first_name"), rs.getString("d.last_name"),
				rs.getString("d.email_address"), rs.getString("d.cellphone_number"), rs.getString("d.password")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return drivers;
	}
	
	public Driver getDriverById(Integer driverId) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Driver driver = new Driver();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT d.driver_id, d.vehicle_id, v.brand, v.model, v.plate_number, d.first_name, d.last_name, "
				+ "d.email_address, d.cellphone_number, d.password FROM Driver as d INNER JOIN Vehicle as v ON d.vehicle_id = v.vehicle_id"
				+ " WHERE d.driver_id = " + driverId + ";");
			if(rs.next()) {
				driver = new Driver(rs.getInt("d.driver_id"), rs.getInt("d.vehicle_id"), rs.getString("v.brand") + " " +
				rs.getString("v.model") + " " + rs.getString("v.plate_number"), rs.getString("d.first_name"), rs.getString("d.last_name"),
				rs.getString("d.email_address"), rs.getString("d.cellphone_number"), rs.getString("d.password"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return driver;
	}
	
	public boolean checkEmailAddress(String emailAddress) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Driver WHERE email_address = '" + emailAddress + "';");
			if(rs.next()) {
				return(true);
			} else {
				return(false);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return(false);
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
	}
	
	public boolean checkOtherEmailAddress(String emailAddress, Integer id) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Driver WHERE email_address = '" + emailAddress + "' AND driver_id != " + id + ";");
			if(rs.next()) {
				return(true);
			} else {
				return(false);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return(false);
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
	}
}