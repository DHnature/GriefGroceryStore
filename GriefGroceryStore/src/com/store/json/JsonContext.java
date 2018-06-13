package com.store.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonContext {
	
		/**
		 * 用于后台处理成功时组装json字符串操作
		 * @param message
		 * @param object
		 * @param arr
		 * @return
		 * @throws IllegalArgumentException
		 */
		public JSONObject getSuccessObject(String message,JSONObject object,JSONArray arr)
				throws IllegalArgumentException{
			JSONObject json=new JSONObject();
			JSONObject object1=new JSONObject();
			object1.put("state","success" );
			object1.put("message",message);
			json.put("meta", object1);
			if(object!=null)
				json.put("data", object);
			if(arr!=null)
				json.put("data", arr);
			return json;
		}
		
		/**
		 * 用于后台处理失败时组装json字符串操作
		 * @param message 调用层传递错误信息
		 * @return
		 */
		public JSONObject getFailedObject(String message){
			JSONObject json=new JSONObject();
			JSONObject object1=new JSONObject();
			object1.put("state","failed" );
			object1.put("message",message);
			json.put("meta", object1);
			return json;
		}

		
	}


