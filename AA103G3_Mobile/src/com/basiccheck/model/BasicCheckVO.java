package com.basiccheck.model;

import java.io.Serializable;
import java.util.Date;

public class BasicCheckVO implements Serializable{
	private Integer basicCheckno;
	private Integer weight;
	private Double bmi;
	private Double bmr;
	private Double bFat;
	private Double waisyline;
	private Date checkDate;
	private Integer memno;
	
	public Integer getBasicCheckno() {
		return basicCheckno;
	}
	public void setBasicCheckno(Integer basicCheckno) {
		this.basicCheckno = basicCheckno;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	public Double getBmr() {
		return bmr;
	}
	public void setBmr(Double bmr) {
		this.bmr = bmr;
	}
	public Double getbFat() {
		return bFat;
	}
	public void setbFat(Double bFat) {
		this.bFat = bFat;
	}
	public Double getWaisyline() {
		return waisyline;
	}
	public void setWaisyline(Double waisyline) {
		this.waisyline = waisyline;
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
