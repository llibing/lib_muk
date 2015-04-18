package com.lib_muk.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class VideoEntityList implements java.io.Serializable {
	
	private String total;
	private ArrayList<VideoEntity> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<VideoEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<VideoEntity> rows) {
		this.rows = rows;
	}


	public class VideoEntity implements java.io.Serializable {
		private String id;
		private String unitEntity_id;
		private String videoname;
		private String videosrc;
		private String videotime;
		private String state;
		private String unitEntity_coursesEntity_teacherEntity_realName;
		private String unitEntity_coursesEntity_teacherEntity_teacherabout;
		private String unitEntity_coursesEntity_courseabout;
		
		
		
		public String getUnitEntity_coursesEntity_teacherEntity_realName() {
			return unitEntity_coursesEntity_teacherEntity_realName;
		}
		public void setUnitEntity_coursesEntity_teacherEntity_realName(
				String unitEntity_coursesEntity_teacherEntity_realName) {
			this.unitEntity_coursesEntity_teacherEntity_realName = unitEntity_coursesEntity_teacherEntity_realName;
		}
		public String getUnitEntity_coursesEntity_teacherEntity_teacherabout() {
			return unitEntity_coursesEntity_teacherEntity_teacherabout;
		}
		public void setUnitEntity_coursesEntity_teacherEntity_teacherabout(
				String unitEntity_coursesEntity_teacherEntity_teacherabout) {
			this.unitEntity_coursesEntity_teacherEntity_teacherabout = unitEntity_coursesEntity_teacherEntity_teacherabout;
		}
		public String getUnitEntity_coursesEntity_courseabout() {
			return unitEntity_coursesEntity_courseabout;
		}
		public void setUnitEntity_coursesEntity_courseabout(
				String unitEntity_coursesEntity_courseabout) {
			this.unitEntity_coursesEntity_courseabout = unitEntity_coursesEntity_courseabout;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getUnitEntity_id() {
			return unitEntity_id;
		}
		public void setUnitEntity_id(String unitEntity_id) {
			this.unitEntity_id = unitEntity_id;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getVideoname() {
			return videoname;
		}
		public void setVideoname(String videoname) {
			this.videoname = videoname;
		}
		public String getVideosrc() {
			return videosrc;
		}
		public void setVideosrc(String videosrc) {
			this.videosrc = videosrc;
		}
		public String getVideotime() {
			return videotime;
		}
		public void setVideotime(String videotime) {
			this.videotime = videotime;
		}

	}
	
	
}
