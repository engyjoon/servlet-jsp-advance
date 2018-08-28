package com.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.kt.common.JdbcUtil;
import com.kt.common.QueryManager;
import com.kt.domain.Data;

public class ZabbixDAO {
	
	private final static String DB_NAME = "zabbix";

	private ZabbixDAO() {}
	
	private static ZabbixDAO dao = new ZabbixDAO();
	
	public static ZabbixDAO getInstance() {
		return dao;
	}

	public ArrayList<Integer> getAlarmCount(String host) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer();
		query.append("select count(*) ");
		query.append("from hosts t1, items t2, functions t3, triggers t4 ");
		query.append("where t1.hostid = t2.hostid and t2.itemid = t3.itemid and t3.triggerid = t4.triggerid ");
		query.append("  and t1.host = ? ");
		query.append("group by t4.priority ");
		query.append("order by t4.priority desc");
		
		ArrayList<Integer> result = new ArrayList<>();
		
		try (Connection conn = JdbcUtil.getConnection(DB_NAME);
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			ResultSet rs = manager.select(pstmt, host)) {
			
			while(rs.next()) {
				result.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Data> getHistory(String host, String key, String table) {
		QueryManager manager = new QueryManager();
		
		StringBuffer query = new StringBuffer();
		query.append("select t3.clock, t3.value ");
		query.append("from hosts t1, items t2, " + table + " t3 ");
		query.append("where t1.hostid = t2.hostid and t2.itemid = t3.itemid ");
		query.append("  and t3.clock > extract(epoch from date_trunc('second', now() - interval '30 minute')) ");
		query.append("  and t1.host = ? and t2.key_ = ? ");
		query.append("order by t3.clock");
		
		ArrayList<Data> result = new ArrayList<>();
		
		try (Connection conn = JdbcUtil.getConnection(DB_NAME);
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				ResultSet rs = manager.select(pstmt, host, key)) {
			
			while(rs.next()) {
				Data data = new Data();
				
				Instant instant = Instant.ofEpochSecond(rs.getInt("CLOCK"));
				LocalDateTime zdt = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"));
				data.setX(zdt.format(DateTimeFormatter.ISO_DATE_TIME));
				data.setY(rs.getInt("VALUE"));
				result.add(data);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
