package com.lib_muk.model;

import java.io.Serializable;

public class GuanzhuEntity implements Serializable{
	
    private String attributes;
    private JsonStr jsonStr;
    private String success;
    private String msg;
    private String obj;
    
    public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public JsonStr getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(JsonStr jsonStr) {
		this.jsonStr = jsonStr;
	}

	public class JsonStr implements Serializable{
		private String success;
	    private String msg;
		public String getSuccess() {
			return success;
		}
		public void setSuccess(String success) {
			this.success = success;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
    }
}
