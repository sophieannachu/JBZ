package com.friend.model;

import java.io.Serializable;
import java.sql.Date;

public class FriendVO implements Serializable{
	private Integer memno;
	private Integer frino;
	private Date addDate;
	private String status;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getFrino() {
		return frino;
	}
	public void setFrino(Integer frino) {
		this.frino = frino;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
