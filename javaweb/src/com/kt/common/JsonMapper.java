package com.kt.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMapper<T> {

	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject(Object instance) throws Exception {
		JSONObject rtObj = null;
		
		if (instance == null) {
			return rtObj;
		}
		
		Class<?> clazz = instance.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		if (fields == null || fields.length == 0) {
			return rtObj;
		}
		
		rtObj = new JSONObject();
		for (Field field : fields) {
			StringBuffer methodName = new StringBuffer();
			
			if (field.getType() == boolean.class) {
				methodName.append("is");
			} else {
				methodName.append("get");
			}
			
			methodName.append(field.getName().substring(0, 1).toUpperCase());
			methodName.append(field.getName().substring(1));
				
			Method method = clazz.getDeclaredMethod(methodName.toString());
			rtObj.put(field.getName(), method.invoke(instance));
		}
		
		return rtObj;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getJSONArray(ArrayList<T> list) throws Exception {
		JSONArray rtArray = null;
		
		if (list == null || list.size() == 0) {
			return rtArray;
		}
		
		rtArray = new JSONArray();
		for (T obj : list) {
			rtArray.add(getJSONObject(obj));
		}
		
		return rtArray;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getJSONArraySimple(ArrayList<T> list) throws Exception {
		JSONArray rtArray = null;
		
		if (list == null || list.size() == 0) {
			return rtArray;
		}
		
		rtArray = new JSONArray();
		for (T obj : list) {
			rtArray.add(obj);
		}
		
		return rtArray;
	}
	
	public ArrayList<T> getValueObjectList(Class<T> clazz, JSONArray jsonArray) throws Exception {
		if (jsonArray == null || jsonArray.size() == 0) {
			return null;
		}
		
		ArrayList<T> rtList = new ArrayList<T>();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			rtList.add(getValueObject(clazz, (JSONObject) jsonArray.get(i)));
		}
		
		return rtList;
	}
	
	public T getValueObject(Class<T> clazz, JSONObject jsonObj) throws Exception {
		T rtObj = clazz.newInstance();
		
		@SuppressWarnings("unchecked")
		Set<String> keySet = jsonObj.keySet();
		if (keySet == null || keySet.size() == 0) {
			return null;
		}
		
		for (String key : keySet) {
			Object vlu = jsonObj.get(key);
			Field field = clazz.getDeclaredField(key);
			StringBuffer methodName = new StringBuffer("set");
			methodName.append(field.getName().substring(0,  1).toUpperCase());
			methodName.append(field.getName().substring(1));
			
			Method method = clazz.getDeclaredMethod(methodName.toString(), field.getType());
			method.invoke(rtObj, vlu);
		}
		
		return rtObj;
	}
}
