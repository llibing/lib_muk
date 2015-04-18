package com.lib_muk.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitEntityList implements Serializable{
	
	private String total;
	private ArrayList<UnitEntity> rows;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public ArrayList<UnitEntity> getRows() {
		return rows;
	}
	public void setRows(ArrayList<UnitEntity> rows) {
		this.rows = rows;
	}
}
