package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfileService {
	private LoginService loginService = new LoginService();
	public ArrayList<Profile> getAllProfiles() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Profile> profiles = new ArrayList<Profile>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Profile");
			while(rs.next()) {
				profiles.add( new Profile(rs.getInt("profile_id"), rs.getString("username"), rs.getString("password"),
				rs.getString("profile_type")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return profiles;
	}
	
	public Profile getProfileById(Integer profileId) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		Profile profile = new Profile();
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Profile WHERE profile_id = " + profileId + ";");
			if(rs.next()) {
				profile = new Profile(rs.getInt("profile_id"), rs.getString("username"), rs.getString("password"),
				rs.getString("profile_type"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}
		
		return profile;
	}
	
	public boolean checkUsername(String username) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		username = username.replaceAll(" ", "");
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Profile WHERE username = '" + username + "';");
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
	
	public boolean checkOtherUsername(String username, Integer id) throws Exception{
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		username = username.replaceAll(" ", "");
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Profile WHERE username = '" + username + "' AND profile_id != " + id + ";");
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