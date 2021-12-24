package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerService {
	private LoginService loginService = new LoginService();
	public ArrayList<Customer> getAllCustomers() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Customer");
			while(rs.next()) {
				customers.add( new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"),
				rs.getString("email_address"), rs.getString("cellphone_number"), rs.getString("password")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return customers;
	}
	
	public Customer getCustomerById(Integer customerId) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Customer customer = new Customer();
		
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Customer WHERE customer_id = " + customerId + ";");
			if(rs.next()) {
				customer = new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"),
				rs.getString("email_address"), rs.getString("cellphone_number"), rs.getString("password"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return customer;
	}
	
	public boolean checkCredentials(String emailAddress, String password) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		
		try {
			password = loginService.hash(password);
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Customer WHERE email_address = '" + emailAddress + "' AND password = '" 
				+ password + "';");
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
	
	public boolean checkEmailAddress(String emailAddress) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Customer WHERE email_address = '" + emailAddress + "';");
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
			rs = stmt.executeQuery("SELECT * FROM Customer WHERE email_address = '" + emailAddress + "' AND customer_id != " + id +";");
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