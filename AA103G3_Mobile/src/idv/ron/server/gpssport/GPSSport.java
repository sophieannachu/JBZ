package idv.ron.server.gpssport;

import java.io.Serializable;
import java.sql.Timestamp;

public class GPSSport implements Serializable{
	private Integer gpsrec_no;
    private Integer memno;
    private Integer gpstype_no;
    private Timestamp gps_date;
    private Integer gps_duration;
    private Float gps_distance;
    private Double gps_cal;
    private String gps_track;

    public GPSSport(){}

    public GPSSport(Timestamp gps_date, Integer gps_duration, Float gps_distance, Double gps_cal) {
        this.gps_date = gps_date;
        this.gps_duration = gps_duration;
        this.gps_distance = gps_distance;
        this.gps_cal = gps_cal;
    }

    public GPSSport(Integer gpsrec_no, Integer memno, Integer gpstype_no, Timestamp gps_date, Integer gps_duration, Float gps_distance, Double gps_cal, String gps_track) {
        this.gpsrec_no = gpsrec_no;
        this.memno = memno;
        this.gpstype_no = gpstype_no;
        this.gps_date = gps_date;
        this.gps_duration = gps_duration;
        this.gps_distance = gps_distance;
        this.gps_cal = gps_cal;
        this.gps_track = gps_track;
    }

    public Integer getGpsrec_no() {
        return gpsrec_no;
    }

    public void setGpsrec_no(Integer gpsrec_no) {
        this.gpsrec_no = gpsrec_no;
    }

    public Integer getMemno() {
        return memno;
    }

    public void setMemno(Integer memno) {
        this.memno = memno;
    }

    public Integer getGpstype_no() {
        return gpstype_no;
    }

    public void setGpstype_no(Integer gpstype_no) {
        this.gpstype_no = gpstype_no;
    }

    public Timestamp getGps_date() {
        return gps_date;
    }

    public void setGps_date(Timestamp gps_date) {
        this.gps_date = gps_date;
    }

    public Integer getGps_duration() {
        return gps_duration;
    }

    public void setGps_duration(Integer gps_duration) {
        this.gps_duration = gps_duration;
    }

    public Float getGps_distance() {
        return gps_distance;
    }

    public void setGps_distance(Float gps_distance) {
        this.gps_distance = gps_distance;
    }

    public Double getGps_cal() {
        return gps_cal;
    }

    public void setGps_cal(Double gps_cal) {
        this.gps_cal = gps_cal;
    }

    public String getGps_track() {
        return gps_track;
    }

    public void setGps_track(String gps_track) {
        this.gps_track = gps_track;
    }
}
