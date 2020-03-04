package iii.jbz.mobile.foodrec;

import java.sql.Date;
import java.sql.Timestamp;

public class FoodListVO implements java.io.Serializable{
	private int fdrecno;
	private String fddesc;
	private Timestamp fddate;
	private int fdqua;
	private int memno;
	private int fd_no;
	
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
	public Timestamp getFddate() {
		return fddate;
	}
	public void setFddate(Timestamp fddate) {
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
