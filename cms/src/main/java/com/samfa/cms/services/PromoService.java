package com.samfa.cms.services;

import com.samfa.cms.models.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PromoService {
	private LoginService loginService = new LoginService();
	public ArrayList<Promo> getAllPromos() throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection db = null;
		db = loginService.getDatabase();
		ArrayList<Promo> promos = new ArrayList<Promo>();

		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Promo");
			while(rs.next()) {
				promos.add( new Promo(rs.getInt("promo_id"), rs.getString("promo_code"), rs.getString("promo_type"), rs.getInt("discount"), rs.getString("start_date"), rs.getString("end_date")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {};
			try { if(stmt != null) stmt.close(); } catch(Exception e) {};
			try { if(db != null) db.close(); } catch(Exception e) {};
		}

		return promos;
	}
}