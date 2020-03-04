package iii.ron.server.spots;
import java.sql.Date;
import java.sql.Timestamp;

public class GroupInfoVO implements java.io.Serializable{
	private Integer group_no;
	private Integer memno;
	private String	group_name;
	private String	group_loc;
	private String	group_detail;
	private Integer	group_count;
	private Integer	group_check;
	private byte[]	group_photo;
	private Timestamp group_time;
	private String	group_long;
	private String	group_lati;
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
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_loc() {
		return group_loc;
	}
	public void setGroup_loc(String group_loc) {
		this.group_loc = group_loc;
	}
	public String getGroup_detail() {
		return group_detail;
	}
	public void setGroup_detail(String group_detail) {
		this.group_detail = group_detail;
	}
	public Integer getGroup_count() {
		return group_count;
	}
	public void setGroup_count(Integer group_count) {
		this.group_count = group_count;
	}
	public Integer getGroup_check() {
		return group_check;
	}
	public void setGroup_check(Integer group_check) {
		this.group_check = group_check;
	}
	public byte[] getGroup_photo() {
		return group_photo;
	}
	public void setGroup_photo(byte[] group_photo) {
		this.group_photo = group_photo;
	}
	public Timestamp getGroup_time() {
		return group_time;
	}
	public void setGroup_time(Timestamp group_time) {
		this.group_time = group_time;
	}
	public String getGroup_long() {
		return group_long;
	}
	public void setGroup_long(String group_long) {
		this.group_long = group_long;
	}
	public String getGroup_lati() {
		return group_lati;
	}
	public void setGroup_lati(String group_lati) {
		this.group_lati = group_lati;
	}
	
	
	

	
	
}
