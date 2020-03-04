package com.heinfo.model;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Date;

public class HeinfoVO implements java.io.Serializable{
	private Integer heinfono;
	private String heinfoname;
	private String heinfodetail;
	private Date heinfodate;
	private byte[] heinfophoto;
	
	public Integer getHeinfono() {
		return heinfono;
	}
	public void setHeinfono(Integer heinfono) {
		this.heinfono = heinfono;
	}
	public String getHeinfoname() {
		return heinfoname;
	}
	public void setHeinfoname(String heinfoname) {
		this.heinfoname = heinfoname;
	}
	public String getHeinfodetail() {
		return heinfodetail;
	}
	public void setHeinfodetail(String heinfodetail) {
		this.heinfodetail = heinfodetail;
	}
	public Date getHeinfodate() {
		return heinfodate;
	}
	public void setHeinfodate(Date heinfodate) {
		this.heinfodate = heinfodate;
	}
	public byte[] getHeinfophoto() {
		return heinfophoto;
	}
	public void setHeinfophoto(byte[] heinfophoto) {
		this.heinfophoto = heinfophoto;
	}
	
	
}
