package com.memanyl.model;

import java.io.Serializable;
import java.sql.Date;

public class MemAnylVO implements Serializable{
	private Integer anylno;
	private Date anylDate;
	private Integer stepAvg;
	private Integer calTakeAvg;
	private Integer exeTimeAvg;
	private Integer calBurnAvg;
	
	public Integer getAnylno() {
		return anylno;
	}
	public void setAnylno(Integer anylno) {
		this.anylno = anylno;
	}
	public Date getAnylDate() {
		return anylDate;
	}
	public void setAnylDate(Date anylDate) {
		this.anylDate = anylDate;
	}
	public Integer getStepAvg() {
		return stepAvg;
	}
	public void setStepAvg(Integer stepAvg) {
		this.stepAvg = stepAvg;
	}
	public Integer getCalTakeAvg() {
		return calTakeAvg;
	}
	public void setCalTakeAvg(Integer calTakeAvg) {
		this.calTakeAvg = calTakeAvg;
	}
	public Integer getExeTimeAvg() {
		return exeTimeAvg;
	}
	public void setExeTimeAvg(Integer exeTimeAvg) {
		this.exeTimeAvg = exeTimeAvg;
	}
	public Integer getCalBurnAvg() {
		return calBurnAvg;
	}
	public void setCalBurnAvg(Integer calBurnAvg) {
		this.calBurnAvg = calBurnAvg;
	}
	
}
