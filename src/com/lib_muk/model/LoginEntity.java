package com.lib_muk.model;

import java.io.Serializable;

public class LoginEntity implements Serializable{
	
    private String attributes;
    private String jsonStr;
    private String success;
    private String msg;
    private objEntity obj;
    
    public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public objEntity getObj() {
		return obj;
	}

	public void setObj(objEntity obj) {
		this.obj = obj;
	}

	public class objEntity implements Serializable{
    	private String id;
    	private String username;
    	private String password;
    	private String note;
    	private String email;
    	private String imgsrc;
    	private String mobile;
    	private String studentabout;
    	private String studentname;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getImgsrc() {
			return imgsrc;
		}
		public void setImgsrc(String imgsrc) {
			this.imgsrc = imgsrc;
		}
		public String getStudentname() {
			return studentname;
		}
		public void setStudentname(String studentname) {
			this.studentname = studentname;
		}
    	
    }
}
