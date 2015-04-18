package com.lib_muk.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseCatlogList implements Serializable{
	private String total;
	private ArrayList<CourseCatlog> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<CourseCatlog> getRows() {
		return rows;
	}

	public void setRows(ArrayList<CourseCatlog> rows) {
		this.rows = rows;
	}

	public class CourseCatlog implements Serializable{
		private String id;
		private String courseClassEntity_id;
		private String catalogname;
		private String state;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCourseClassEntity_id() {
			return courseClassEntity_id;
		}
		public void setCourseClassEntity_id(String courseClassEntity_id) {
			this.courseClassEntity_id = courseClassEntity_id;
		}
		public String getCatalogname() {
			return catalogname;
		}
		public void setCatalogname(String catalogname) {
			this.catalogname = catalogname;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	}
	
}
