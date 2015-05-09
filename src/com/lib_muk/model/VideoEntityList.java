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

}
