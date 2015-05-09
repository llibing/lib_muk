package com.lib_muk.model;

import java.io.Serializable;

public class Uploadcommit implements Serializable{
	
	    private String fileName;
		private String originalName;
		private String size;
		private String state;
		private String type;
		private String url;
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getOriginalName() {
			return originalName;
		}
		public void setOriginalName(String originalName) {
			this.originalName = originalName;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
}
