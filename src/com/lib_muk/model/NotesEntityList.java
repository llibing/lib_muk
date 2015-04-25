package com.lib_muk.model;

import java.util.ArrayList;

public class NotesEntityList implements java.io.Serializable {
	private String total;
	private ArrayList<NotesEntity> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<NotesEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<NotesEntity> rows) {
		this.rows = rows;
	}

	public class NotesEntity implements java.io.Serializable {
		
		private String id;
		private String studentEntity_studentname;
		private String videoEntity_Id;
		private String notescontent;
		private String notesattach;
		private String createtime;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getStudentEntity_studentname() {
			return studentEntity_studentname;
		}
		public void setStudentEntity_studentname(String studentEntity_studentname) {
			this.studentEntity_studentname = studentEntity_studentname;
		}
		public String getVideoEntity_Id() {
			return videoEntity_Id;
		}
		public void setVideoEntity_Id(String videoEntity_Id) {
			this.videoEntity_Id = videoEntity_Id;
		}
		public String getNotescontent() {
			return notescontent;
		}
		public void setNotescontent(String notescontent) {
			this.notescontent = notescontent;
		}
		public String getNotesattach() {
			return notesattach;
		}
		public void setNotesattach(String notesattach) {
			this.notesattach = notesattach;
		}
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		
	}
	
}
