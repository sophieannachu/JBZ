package com.foodlist.model;

import java.sql.Date;

public class FoodlistVO implements java.io.Serializable{
	private int fdrecno;
	private String fddesc;
	private Date fddate;
	private int fdqua;
	private int memno;
	private int fd_no;
	private int fd_cal;
	public int getFd_cal(){
		return fd_cal;
	}
	public void setFd_cal(int fd_cal){
		this.fd_cal = fd_cal;
	}
	
	public int getFdrecno() {
		return fdrecno;
	}
	public void setFdrecno(int fdrecno) {
		this.fdrecno = fdrecno;
	}
	public String getFddesc() {
		return fddesc;
	}
	public void setFddesc(String fddesc) {
		this.fddesc = fddesc;
	}
	public Date getFddate() {
		return fddate;
	}
	public void setFddate(Date fddate) {
		this.fddate = fddate;
	}
	public int getFdqua() {
		return fdqua;
	}
	public void setFdqua(int fdqua) {
		this.fdqua = fdqua;
	}
	
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public int getFd_no() {
		return fd_no;
	}
	public void setFd_no(int fd_no) {
		this.fd_no = fd_no;
	}
	
	
	
	
}
