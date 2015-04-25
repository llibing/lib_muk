package com.lib_muk.model;

import java.util.ArrayList;


public class MessagesEntityList implements java.io.Serializable {
	
	private String total;
	private ArrayList<MessagesEntity> rows;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<MessagesEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<MessagesEntity> rows) {
		this.rows = rows;
	}

	public class MessagesEntity implements java.io.Serializable {
		private String id;
		private String teacherEntity_Id;
		private String messagename;
		private String messagecontent;
		private String createtime;
		private String state;
		private String teacherEntity_realName;
		
		public String getTeacherEntity_realName() {
			return teacherEntity_realName;
		}
		public void setTeacherEntity_realName(String teacherEntity_realName) {
			this.teacherEntity_realName = teacherEntity_realName;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTeacherEntity_Id() {
			return teacherEntity_Id;
		}
		public void setTeacherEntity_Id(String teacherEntity_Id) {
			this.teacherEntity_Id = teacherEntity_Id;
		}
		public String getMessagename() {
			return messagename;
		}
		public void setMessagename(String messagename) {
			this.messagename = messagename;
		}
		public String getMessagecontent() {
			return messagecontent;
		}
		public void setMessagecontent(String messagecontent) {
			this.messagecontent = messagecontent;
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
	}
}
