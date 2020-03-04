package iii.jbz.mobile.clock;

import java.sql.Timestamp;

public class Clock 
{
    private Integer memno;
    private Integer clockno;
    private Timestamp clocktime;
    private String clockinfo;
    private String clocksche;
    private String clockring;
    private Integer clocktype;


    public Clock(Integer memno, Integer clockno, Timestamp clocktime, String clockinfo, String clocksche, String clockring, Integer clocktype) 
    {
        this.memno = memno;
        this.clockno = clockno;
        this.clocktime = clocktime;
        this.clockinfo = clockinfo;
        this.clocksche = clocksche;
        this.clockring = clockring;
        this.clocktype = clocktype;
    }

    public int getMemNo()
    {
        return memno;
    }
    public void setMemNo(int memno)
    {
        this.memno = memno;
    }
    ///////////////////////////////////////////////////////////////
    public int getClockNo()
    {
        return clockno;
    }
    public void setClockNo(int clockno)
    {
        this.clockno = clockno;
    }
    //////////////////////////////////////////////////////////////
    public String getClockInfo()
    {
        return clockinfo;
    }
    public void setClockInfo(String clockinfo)
    {
        this.clockinfo = clockinfo;
    }
    ///////////////////////////////////////////////////////////////
    public Timestamp getClockTime()
    {
        return clocktime;
    }
    public void setClockTime(Timestamp clocktime)
    {
        this.clocktime = clocktime;
    }
    ///////////////////////////////////////////////////////////////
    public String getClockSche()
    {
        return clocksche;
    }
    public void setClockSche(String clocksche)
    {
        this.clocksche = clocksche;
    }
    public String getClockring() 
    {
        return clockring;
    }
    public void setClockring(String clockring) 
    {
        this.clockring = clockring;
    }
    public Integer getClocktype() 
    {
        return clocktype;
    }
    public void setClocktype(Integer clocktype) 
    {
        this.clocktype = clocktype;
    }  
}