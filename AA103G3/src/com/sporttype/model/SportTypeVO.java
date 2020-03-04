package com.sporttype.model;

import java.io.Serializable;

public class SportTypeVO implements Serializable{
	private Integer type_no;
	private String sport_name;
	private byte[] type_pic;
	
	public Integer getType_no() {
		return type_no;
	}
	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}
	public String getSport_name() {
		return sport_name;
	}
	public void setSport_name(String sport_name) {
		this.sport_name = sport_name;
	}
	public byte[] getType_pic() {
		return type_pic;
	}
	public void setType_pic(byte[] type_pic) {
		this.type_pic = type_pic;
	}
	
	
}
