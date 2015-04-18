package com.lib_muk.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkPageList implements Serializable{
	private String total;
	private ArrayList<WorkPage> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<WorkPage> getRows() {
		return rows;
	}

	public void setRows(ArrayList<WorkPage> rows) {
		this.rows = rows;
	}

	public class WorkPage implements Serializable{
		private String createtime;
		private String id;
		private String planstageEntity_Id;
		private String coursediffcult;
		private String courseCatlogEntity_Id;
		private String imgsrc;
		private String state;
		private String whatlearn;
		private String coursenote;
		private String  teacherEntity_teacherabout;
		private String courseabout;
		private String teacherEntity_Id;
		private String coursename;
		private String teacherEntity_realName;
		
		public String getTeacherEntity_teacherabout() {
			return teacherEntity_teacherabout;
		}
		public void setTeacherEntity_teacherabout(String teacherEntity_teacherabout) {
			this.teacherEntity_teacherabout = teacherEntity_teacherabout;
		}
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPlanstageEntity_Id() {
			return planstageEntity_Id;
		}
		public void setPlanstageEntity_Id(String planstageEntity_Id) {
			this.planstageEntity_Id = planstageEntity_Id;
		}
		public String getCoursediffcult() {
			return coursediffcult;
		}
		public void setCoursediffcult(String coursediffcult) {
			this.coursediffcult = coursediffcult;
		}
		public String getCourseCatlogEntity_Id() {
			return courseCatlogEntity_Id;
		}
		public void setCourseCatlogEntity_Id(String courseCatlogEntity_Id) {
			this.courseCatlogEntity_Id = courseCatlogEntity_Id;
		}
		public String getImgsrc() {
			return imgsrc;
		}
		public void setImgsrc(String imgsrc) {
			this.imgsrc = imgsrc;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getWhatlearn() {
			return whatlearn;
		}
		public void setWhatlearn(String whatlearn) {
			this.whatlearn = whatlearn;
		}
		public String getCoursenote() {
			return coursenote;
		}
		public void setCoursenote(String coursenote) {
			this.coursenote = coursenote;
		}
		public String getCourseabout() {
			return courseabout;
		}
		public void setCourseabout(String courseabout) {
			this.courseabout = courseabout;
		}
		public String getTeacherEntity_Id() {
			return teacherEntity_Id;
		}
		public void setTeacherEntity_Id(String teacherEntity_Id) {
			this.teacherEntity_Id = teacherEntity_Id;
		}
		public String getCoursename() {
			return coursename;
		}
		public void setCoursename(String coursename) {
			this.coursename = coursename;
		}
		public String getTeacherEntity_realName() {
			return teacherEntity_realName;
		}
		public void setTeacherEntity_realName(String teacherEntity_realName) {
			this.teacherEntity_realName = teacherEntity_realName;
		}
	}
}
