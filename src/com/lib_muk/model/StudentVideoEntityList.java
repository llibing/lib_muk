package com.lib_muk.model;

import java.util.ArrayList;

public class StudentVideoEntityList implements java.io.Serializable {
	
	private String total;
	private ArrayList<StudentVideoEntity> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<StudentVideoEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<StudentVideoEntity> rows) {
		this.rows = rows;
	}


	public class StudentVideoEntity implements java.io.Serializable {
		private String id;
		private String videoentity_videoname;
		private String videoentity_videotime;
		private String videoentity_videosrc;
		private String videoentity_Id;
		private String converttime;
		private String learntime;
		private String state;
		private String videoentity_unitEntity_coursesEntity_teacherEntity_realName;
		private String videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout;
		private String videoentity_unitEntity_coursesEntity_courseabout;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getVideoentity_Id() {
			return videoentity_Id;
		}
		public void setVideoentity_Id(String videoentity_Id) {
			this.videoentity_Id = videoentity_Id;
		}
		public String getVideoentity_videosrc() {
			return videoentity_videosrc;
		}
		public void setVideoentity_videosrc(String videoentity_videosrc) {
			this.videoentity_videosrc = videoentity_videosrc;
		}
		public String getVideoentity_videoname() {
			return videoentity_videoname;
		}
		public void setVideoentity_videoname(String videoentity_videoname) {
			this.videoentity_videoname = videoentity_videoname;
		}
		public String getVideoentity_videotime() {
			return videoentity_videotime;
		}
		public void setVideoentity_videotime(String videoentity_videotime) {
			this.videoentity_videotime = videoentity_videotime;
		}
		public String getConverttime() {
			return converttime;
		}
		public void setConverttime(String converttime) {
			this.converttime = converttime;
		}
		public String getLearntime() {
			return learntime;
		}
		public void setLearntime(String learntime) {
			this.learntime = learntime;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getVideoentity_unitEntity_coursesEntity_teacherEntity_realName() {
			return videoentity_unitEntity_coursesEntity_teacherEntity_realName;
		}
		public void setVideoentity_unitEntity_coursesEntity_teacherEntity_realName(
				String videoentity_unitEntity_coursesEntity_teacherEntity_realName) {
			this.videoentity_unitEntity_coursesEntity_teacherEntity_realName = videoentity_unitEntity_coursesEntity_teacherEntity_realName;
		}
		public String getVideoentity_unitEntity_coursesEntity_teacherEntity_teacherabout() {
			return videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout;
		}
		public void setVideoentity_unitEntity_coursesEntity_teacherEntity_teacherabout(
				String videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout) {
			this.videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout = videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout;
		}
		public String getVideoentity_unitEntity_coursesEntity_courseabout() {
			return videoentity_unitEntity_coursesEntity_courseabout;
		}
		public void setVideoentity_unitEntity_coursesEntity_courseabout(
				String videoentity_unitEntity_coursesEntity_courseabout) {
			this.videoentity_unitEntity_coursesEntity_courseabout = videoentity_unitEntity_coursesEntity_courseabout;
		}
		
	}
	
}
