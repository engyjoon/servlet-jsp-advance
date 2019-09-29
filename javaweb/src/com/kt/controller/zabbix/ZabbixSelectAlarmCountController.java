package com.kt.controller.zabbix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.kt.common.JsonMapper;
import com.kt.controller.AbstractController;
import com.kt.service.ZabbixService;

public class ZabbixSelectAlarmCountController extends AbstractController {

	ZabbixService service = ZabbixService.getInstnace();
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String host = request.getParameter("host");
		
		JsonMapper<Integer> mapper = new JsonMapper<>();
		try {
			JSONArray array = mapper.getJSONArraySimple(service.getAlarmCount(host));
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.doPost(request, response);
	}

}
