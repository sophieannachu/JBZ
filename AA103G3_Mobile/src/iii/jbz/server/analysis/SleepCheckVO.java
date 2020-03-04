package iii.jbz.server.analysis;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SleepCheckVO implements Serializable{
	private Integer sleepCheckno;
	private Timestamp bedTime;
	private Timestamp wakeTime;
	private Integer sleepTime;
	private Integer memno;
	public Integer getSleepCheckno() {
		return sleepCheckno;
	}
	public void setSleepCheckno(Integer sleepCheckno) {
		this.sleepCheckno = sleepCheckno;
	}
	public Timestamp getBedTime() {
		return bedTime;
	}
	public void setBedTime(Timestamp bedTime) {
		this.bedTime = bedTime;
	}
	public Timestamp getWakeTime() {
		return wakeTime;
	}
	public void setWakeTime(Timestamp wakeTime) {
		this.wakeTime = wakeTime;
	}
	public Integer getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(Integer sleepTime) {
		this.sleepTime = sleepTime;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	
	
}
