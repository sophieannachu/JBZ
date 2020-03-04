package idv.ron.server.watchsport;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class WatchSport implements Serializable{
	private Integer watchrec_no;
	private Integer memno;
	private Integer watchtype_no;
	private Timestamp watch_date;
	private Integer watch_duration;
	private Integer watch_cal;
	
	public WatchSport(Integer watchrec_no, Integer memno, Integer watchtype_no, Timestamp watch_date, Integer watch_duration,
			Integer watch_cal) {
		super();
		this.watchrec_no = watchrec_no;
		this.memno = memno;
		this.watchtype_no = watchtype_no;
		this.watch_date = watch_date;
		this.watch_duration = watch_duration;
		this.watch_cal = watch_cal;
	}
	public Integer getWatchrec_no() {
		return watchrec_no;
	}
	public void setWatchrec_no(Integer watchrec_no) {
		this.watchrec_no = watchrec_no;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getWatchtype_no() {
		return watchtype_no;
	}
	public void setWatchtype_no(Integer watchtype_no) {
		this.watchtype_no = watchtype_no;
	}
	public Timestamp getWatch_date() {
		return watch_date;
	}
	public void setWatch_date(Timestamp watch_date) {
		this.watch_date = watch_date;
	}
	public Integer getWatch_duration() {
		return watch_duration;
	}
	public void setWatch_duration(Integer watch_duration) {
		this.watch_duration = watch_duration;
	}
	public Integer getWatch_cal() {
		return watch_cal;
	}
	public void setWatch_cal(Integer watch_cal) {
		this.watch_cal = watch_cal;
	}
	
}
