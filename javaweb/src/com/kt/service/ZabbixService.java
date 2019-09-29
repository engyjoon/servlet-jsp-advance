package com.kt.service;

import java.util.ArrayList;

import com.kt.dao.ZabbixDAO;
import com.kt.domain.Data;

public class ZabbixService {
	
	private ZabbixService() {}
	
	private static ZabbixService service = new ZabbixService();
	
	ZabbixDAO dao = ZabbixDAO.getInstance();

	public static ZabbixService getInstnace() {
		return service;
	}

	public ArrayList<Integer> getAlarmCount(String host) {
		return dao.getAlarmCount(host);
	}

	public ArrayList<Data> getHistory(String host, String key, String table) {
		return dao.getHistory(host, key, table);
	}

}
