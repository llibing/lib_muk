package com.lib_muk.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitAndVideoList implements Serializable{
	
	ArrayList<UnitAndVideo> unitAndVideo;
	
	public ArrayList<UnitAndVideo> getUnitAndVideo() {
		return unitAndVideo;
	}

	public void setUnitAndVideo(ArrayList<UnitAndVideo> unitAndVideo) {
		this.unitAndVideo = unitAndVideo;
	}

	public class UnitAndVideo implements Serializable{
		
		private CoursesEntity coursesEntity;
		private ArrayList<VideoEntityList> videolist;
		private String id;
		private String unitabout;
		private String unitname;
		
		public CoursesEntity getCoursesEntity() {
			return coursesEntity;
		}
		public void setCoursesEntity(CoursesEntity coursesEntity) {
			this.coursesEntity = coursesEntity;
		}
		public ArrayList<VideoEntityList> getVideolist() {
			return videolist;
		}
		public void setVideolist(ArrayList<VideoEntityList> videolist) {
			this.videolist = videolist;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUnitabout() {
			return unitabout;
		}
		public void setUnitabout(String unitabout) {
			this.unitabout = unitabout;
		}
		public String getUnitname() {
			return unitname;
		}
		public void setUnitname(String unitname) {
			this.unitname = unitname;
		}
	}
	
}
