package com.ach.model;

import java.io.Serializable;

public class AchVO implements Serializable{
	private Integer achno;
	private String aName;
	private Integer achCond;
	
	
	public Integer getAchno() {
		return achno;
	}
	public void setAchno(Integer achno) {
		this.achno = achno;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public Integer getAchCond() {
		return achCond;
	}
	public void setAchCond(Integer achCond) {
		this.achCond = achCond;
	}
	
	
}
