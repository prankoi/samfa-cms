package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class VehicleService {
	private LoginService loginService = new LoginService();
	public ArrayList<Vehicle> getAllVehicles() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Vehicle");
			while(rs.next()) {
				vehicles.add( new Vehicle(rs.getInt("vehicle_id"), rs.getString("plate_number"), rs.getString("brand"), rs.getString("model"), rs.getInt("passenger_capacity"), rs.getInt("load_capacity"), rs.getString("status"), rs.getString("assignability")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return vehicles;
	}
	
	public ArrayList<Vehicle> getAssignableVehicles() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE assignability = 'Assignable'");
			while(rs.next()) {
				vehicles.add( new Vehicle(rs.getInt("vehicle_id"), rs.getString("plate_number"), rs.getString("brand"), rs.getString("model"), rs.getInt("passenger_capacity"), rs.getInt("load_capacity"), rs.getString("status"), rs.getString("assignability")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return vehicles;
	}
	
	public void updateAssignableStatus(Integer vehicleId, String status) throws Exception {
		PreparedStatement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			stmt = db.prepareStatement("UPDATE Vehicle SET assignability = '" + status + "' WHERE vehicle_id = ?;");
			stmt.setInt(1, vehicleId);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
	}
	
	public boolean checkPlateNumber(String plateNumber) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		plateNumber = plateNumber.replaceAll(" ", "");
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE plate_number = '" + plateNumber + "';");
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
	
	public boolean checkOtherPlateNumber(String plateNumber, Integer id) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		plateNumber = plateNumber.replaceAll(" ", "");
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Vehicle WHERE plate_number = '" + plateNumber + "' AND vehicle_id != " + id + ";");
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