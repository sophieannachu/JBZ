package com.memach.model;
import java.io.Serializable;
import java.sql.Date;

public class MemAchVO implements Serializable {
	private Integer memno;
	private Integer achno;
	private Date comDate;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getAchno() {
		return achno;
	}
	public void setAchno(Integer achno) {
		this.achno = achno;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	
	
}
