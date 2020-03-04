package iii.jbz.mobile.healthplan;

import java.sql.Date;
import java.sql.Timestamp;

public class HealthPlanVO {

    private Integer hpno;
    private Integer memno;
    private String hpname;
    private Timestamp hpstdate;
    private Timestamp hpeddate;
    private Integer hpstep;
    private Integer hpexer;
    private Integer hpbmi;
    private Double hpwaist;
    private Integer hpcal;
    private Integer hpbp;
    private Integer hpbg;
    
    public HealthPlanVO(int hpno, int memno, String hpname, Timestamp hpstdate, Timestamp hpeddate, int hpstep, int hpexer, int hpbmi, double hpwaist, int hpcal, int hpbp, int hpbg) 
    {
    	this.hpno = hpno;
    	this.memno = memno;
    	this.hpname = hpname;
    	this.hpstdate = hpstdate;
    	this.hpeddate = hpeddate;
    	this.hpstep = hpstep;
    	this.hpexer = hpexer;
    	this.hpbmi = hpbmi;
    	this.hpwaist = hpwaist;
    	this.hpcal = hpcal;
    	this.hpbp = hpbp;
    	this.hpbg = hpbg;
	}

    
 
  
    public Integer getHpno() {
        return hpno;
    }
    public void setHpno(Integer hpno) {
        this.hpno = hpno;
    }
    public Integer getMemno() {
        return memno;
    }
    public void setMemno(Integer memno) {
        this.memno = memno;
    }
    public String getHpname() {
        return hpname;
    }
    public void setHpname(String hpname) {
        this.hpname = hpname;
    }
    public Timestamp getHpstdate() {
        return hpstdate;
    }
    public void setHpstdate(Timestamp hpdate) {
        this.hpstdate = hpdate;
    }
    public Timestamp getHpeddate() {
        return hpeddate;
    }
    public void setHpeddate(Timestamp hpdate) {
        this.hpeddate = hpdate;
    }
    public Integer getHpstep() {
        return hpstep;
    }
    public void setHpstep(Integer hpstep) {
        this.hpstep = hpstep;
    }
    public Integer getHpexer() {
        return hpexer;
    }
    public void setHpexer(Integer hpexer) {
        this.hpexer = hpexer;
    }
    public Integer getHpbmi() {
        return hpbmi;
    }
    public void setHpbmi(Integer hpbmi) {
        this.hpbmi = hpbmi;
    }
    public Double getHpwaist() {
        return hpwaist;
    }
    public void setHpwaist(Double hpwaist) {
        this.hpwaist = hpwaist;
    }
    public Integer getHpcal() {
        return hpcal;
    }
    public void setHpcal(Integer hpcal) {
        this.hpcal = hpcal;
    }
    public Integer getHpbp() {
        return hpbp;
    }
    public void setHpbp(Integer hpbp) {
        this.hpbp = hpbp;
    }
    public Integer getHpbg() {
        return hpbg;
    }
    public void setHpbg(Integer hpbg) {
        this.hpbg = hpbg;
    }
}