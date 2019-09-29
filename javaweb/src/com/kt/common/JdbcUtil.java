package com.kt.common;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {

	public static Connection getConnection(String dbName) {
		Connection conn = null;
		String resourceName = "jdbc/" + dbName;
		
		try {
			InitialContext ic = new InitialContext();
			Context envCtx = (Context) ic.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup(resourceName);
			
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
