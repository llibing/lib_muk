package com.lib_muk.model;

import java.io.Serializable;

public class NoteEntity implements Serializable{
	
    private String attributes;
    private String jsonStr;
    private String success;
    private String msg;
    private objNote obj;
    
    public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public class objNote implements Serializable{
    	
    	
    }
}
