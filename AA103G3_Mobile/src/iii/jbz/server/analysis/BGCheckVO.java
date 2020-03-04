package iii.jbz.server.analysis;

import java.io.Serializable;
import java.util.Date;

public class BGCheckVO implements Serializable{
	private Integer bgCheckno;
	private Integer bgBfMeat;
	private Integer bgAfMeat;
	private Integer bgBfSleep;
	private Date checkDate;
	private Integer memno;
	
	
	
	public Integer getBgCheckno() {
		return bgCheckno;
	}
	public void setBgCheckno(Integer bgCheckno) {
		this.bgCheckno = bgCheckno;
	}
	public Integer getBgBfMeat() {
		return bgBfMeat;
	}
	public void setBgBfMeat(Integer bgBfMeat) {
		this.bgBfMeat = bgBfMeat;
	}
	public Integer getBgAfMeat() {
		return bgAfMeat;
	}
	public void setBgAfMeat(Integer bgAfMeat) {
		this.bgAfMeat = bgAfMeat;
	}
	public Integer getBgBfSleep() {
		return bgBfSleep;
	}
	public void setBgBfSleep(Integer bgBfSleep) {
		this.bgBfSleep = bgBfSleep;
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