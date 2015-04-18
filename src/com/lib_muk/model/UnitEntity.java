package com.lib_muk.model;



public class UnitEntity implements java.io.Serializable {
	private String id;
	private String state;
	private String unitname;
	private String unitabout;
	private String coursesEntity_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getUnitabout() {
		return unitabout;
	}
	public void setUnitabout(String unitabout) {
		this.unitabout = unitabout;
	}
	public String getCoursesEntity_id() {
		return coursesEntity_id;
	}
	public void setCoursesEntity_id(String coursesEntity_id) {
		this.coursesEntity_id = coursesEntity_id;
	}
	
	

}
