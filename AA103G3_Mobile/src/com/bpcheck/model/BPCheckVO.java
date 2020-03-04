package com.bpcheck.model;

import java.io.Serializable;
import java.util.Date;

public class BPCheckVO implements Serializable{
	private Integer bpCheckno;
	private Integer sPressure;
	private Integer dPressure;
	private Date checkDate;
	private Integer memno;
	
	
	public Integer getBpCheckno() {
		return bpCheckno;
	}
	public void setBpCheckno(Integer bpCheckno) {
		this.bpCheckno = bpCheckno;
	}
	public Integer getsPressure() {
		return sPressure;
	}
	public void setsPressure(Integer sPressure) {
		this.sPressure = sPressure;
	}
	public Integer getdPressure() {
		return dPressure;
	}
	public void setdPressure(Integer dPressure) {
		this.dPressure = dPressure;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	
}
