package com.lib_muk.model;


import java.util.ArrayList;
public class HomeworkEntityList implements java.io.Serializable {
	
	private String total;
	private ArrayList<HomeworkEntity> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<HomeworkEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<HomeworkEntity> rows) {
		this.rows = rows;
	}

	public class HomeworkEntity implements java.io.Serializable {
		private String id;
		private String unitEntity_Id;
		private String homeworkname;
		private String homeworkcontent;
		private String createtime;
		private String state;
		private String homeworkattach;
		private String unitEntity_coursesEntity_coursename;
		
		public String getUnitEntity_coursesEntity_coursename() {
			return unitEntity_coursesEntity_coursename;
		}
		public void setUnitEntity_coursesEntity_coursename(
				String unitEntity_coursesEntity_coursename) {
			this.unitEntity_coursesEntity_coursename = unitEntity_coursesEntity_coursename;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUnitEntity_Id() {
			return unitEntity_Id;
		}
		public void setUnitEntity_Id(String unitEntity_Id) {
			this.unitEntity_Id = unitEntity_Id;
		}
		public String getHomeworkname() {
			return homeworkname;
		}
		public void setHomeworkname(String homeworkname) {
			this.homeworkname = homeworkname;
		}
		public String getHomeworkcontent() {
			return homeworkcontent;
		}
		public void setHomeworkcontent(String homeworkcontent) {
			this.homeworkcontent = homeworkcontent;
		}
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getHomeworkattach() {
			return homeworkattach;
		}
		public void setHomeworkattach(String homeworkattach) {
			this.homeworkattach = homeworkattach;
		}
		
	}
}
