package com.clock.model;

import java.sql.*;

public class ClockVO implements java.io.Serializable{
	private Integer memno;
    private Integer clockno;
    private Timestamp clocktime;
    private String clockinfo;
    private String clocksche;
    private String clockring;
    private Integer clocktype;
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getClockno() {
		return clockno;
	}
	public void setClockno(Integer clockno) {
		this.clockno = clockno;
	}
	public Timestamp getClocktime() {
		return clocktime ;
	}
	public void setClocktime(Timestamp clocktime) {
		this.clocktime = clocktime;
	}
	public String getClockinfo() {
		return clockinfo;
	}
	public void setClockinfo(String clockinfo) {
		this.clockinfo = clockinfo;
	}
	public String getClocksche() {
		return clocksche;
	}
	public void setClocksche(String clocksche) {
		this.clocksche = clocksche;
	}
	public String getClockring() {
		return clockring;
	}
	public void setClockring(String clockring) {
		this.clockring = clockring;
	}
	public Integer getClocktype() {
		return clocktype;
	}
	public void setClocktype(Integer clocktype) {
		this.clocktype = clocktype;
	}
    
}
