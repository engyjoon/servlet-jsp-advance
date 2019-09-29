package com.kt.controller.zabbix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.kt.common.JsonMapper;
import com.kt.controller.AbstractController;
import com.kt.domain.Data;
import com.kt.service.ZabbixService;

public class ZabbixSelectHistory extends AbstractController {
	
	private final static Logger logger = Logger.getLogger(ZabbixSelectHistory.class);

	ZabbixService service = ZabbixService.getInstnace();
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String host = request.getParameter("host");
		String key = request.getParameter("key");
		String table = request.getParameter("table");
		
		logger.debug(host);
		logger.debug(key);
		logger.debug(table);
		
		JsonMapper<Data> mapper = new JsonMapper<>();
		
		try {
			JSONArray array = mapper.getJSONArray(service.getHistory(host, key, table));
			out.print(array.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return super.doPost(request, response);
	}
}
