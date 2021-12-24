package com.samfa.cms.services;

import java.util.Formatter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginService {
	
	public boolean checkCredentials(String username, String password, String profileType) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = getDatabase();
		
		try {
			password = hash(password);
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Profile WHERE username = '" + username + "' AND password = '" 
				+ password + "' AND profile_type = '" + profileType + "';");
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
	
	//Hashes password into SHA-1 format
	public String hash(String password) throws Exception{
		Formatter fmt = new Formatter();
		MessageDigest dd = MessageDigest.getInstance("SHA-1");
		byte[] digest = dd.digest(password.getBytes("utf8"));
		for(byte b : digest) {
			fmt.format("%02x", b);
		}
		return(fmt.toString());
	}
	
	//Initializes the database
	public Connection getDatabase() throws Exception{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb?" + 
				"user=root&password=Webdevprankoi23!");
		} catch(SQLException ex) {
			conn = null;
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return(conn);
	}
}