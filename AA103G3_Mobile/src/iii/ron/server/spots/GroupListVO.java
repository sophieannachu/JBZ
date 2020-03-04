package iii.ron.server.spots;
import java.sql.Date;

public class GroupListVO implements java.io.Serializable{
	private Integer group_no;
	private Integer memno;
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
}
