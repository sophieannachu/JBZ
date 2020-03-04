package com.pedometer.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Pedometer implements Serializable{
	private Integer pedorec_no;
	private Integer memno;
	private Timestamp pedo_start;
	private Timestamp pedo_end;
	private Integer pedo_total;
	private Integer pedo_acttime;
	
	public Pedometer(){}
	
	public Pedometer(Integer pedorec_no, Integer memno, Timestamp pedo_start, Timestamp pedo_end, Integer pedo_total,
			Integer pedo_acttime) {
		this.pedorec_no = pedorec_no;
		this.memno = memno;
		this.pedo_start = pedo_start;
		this.pedo_end = pedo_end;
		this.pedo_total = pedo_total;
		this.pedo_acttime = pedo_acttime;
	}

	public Integer getPedorec_no() {
		return pedorec_no;
	}

	public void setPedorec_no(Integer pedorec_no) {
		this.pedorec_no = pedorec_no;
	}

	public Integer getMemno() {
		return memno;
	}

	public void setMemno(Integer memno) {
		this.memno = memno;
	}

	public Timestamp getPedo_start() {
		return pedo_start;
	}

	public void setPedo_start(Timestamp pedo_start) {
		this.pedo_start = pedo_start;
	}

	public Timestamp getPedo_end() {
		return pedo_end;
	}

	public void setPedo_end(Timestamp pedo_end) {
		this.pedo_end = pedo_end;
	}

	public Integer getPedo_total() {
		return pedo_total;
	}

	public void setPedo_total(Integer pedo_total) {
		this.pedo_total = pedo_total;
	}

	public Integer getPedo_acttime() {
		return pedo_acttime;
	}

	public void setPedo_acttime(Integer pedo_acttime) {
		this.pedo_acttime = pedo_acttime;
	}

}
