package iii.jbz.server.sport;

import java.io.Serializable;
import java.sql.Timestamp;

public class Sport implements Serializable{
	private Integer sportrec_no;
    private Integer memno;
    private Integer type_no;
    private Timestamp sport_date;
    private Integer sport_duration;
    private Float gps_distance;
    private Double sport_cal;
    private String gps_track;
    
    
    public Sport(Integer type_no, Timestamp sport_date, Integer sport_duration, Float gps_distance, Double sport_cal) {
		super();
		this.type_no = type_no;
		this.sport_date = sport_date;
		this.sport_duration = sport_duration;
		this.gps_distance = gps_distance;
		this.sport_cal = sport_cal;
	}

	public Sport(Integer sportrec_no, Integer memno, Integer type_no, Timestamp sport_date, Integer sport_duration, Float gps_distance, Double sport_cal, String gps_track) {
        this.sportrec_no = sportrec_no;
        this.memno = memno;
        this.type_no = type_no;
        this.sport_date = sport_date;
        this.sport_duration = sport_duration;
        this.gps_distance = gps_distance;
        this.sport_cal = sport_cal;
        this.gps_track = gps_track;
    }

    public Integer getSportrec_no() {
        return sportrec_no;
    }

    public void setSportrec_no(Integer sportrec_no) {
        this.sportrec_no = sportrec_no;
    }

    public Integer getMemno() {
        return memno;
    }

    public void setMemno(Integer memno) {
        this.memno = memno;
    }

    public Integer getType_no() {
        return type_no;
    }

    public void setType_no(Integer type_no) {
        this.type_no = type_no;
    }

    public Timestamp getSport_date() {
        return sport_date;
    }

    public void setSport_date(Timestamp sport_date) {
        this.sport_date = sport_date;
    }

    public Integer getSport_duration() {
        return sport_duration;
    }

    public void setSport_duration(Integer sport_duration) {
        this.sport_duration = sport_duration;
    }

    public Float getGps_distance() {
        return gps_distance;
    }

    public void setGps_distance(Float gps_distance) {
        this.gps_distance = gps_distance;
    }

    public Double getSport_cal() {
        return sport_cal;
    }

    public void setSport_cal(Double sport_cal) {
        this.sport_cal = sport_cal;
    }

    public String getGps_track() {
        return gps_track;
    }

    public void setGps_track(String gps_track) {
        this.gps_track = gps_track;
    }

}
