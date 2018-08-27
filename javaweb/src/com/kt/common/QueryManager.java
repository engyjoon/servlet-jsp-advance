package com.kt.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryManager {

	public ResultSet select(PreparedStatement pstmt, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			
			return pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Integer update(String dbName, String query, Object... params) {
		try (Connection conn = JdbcUtil.getConnection(dbName);
			PreparedStatement pstmt = conn.prepareStatement(query)) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
