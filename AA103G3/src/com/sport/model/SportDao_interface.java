package com.sport.model;

import java.sql.Date;
import java.util.List;


public interface SportDao_interface {
	public List<Sport> getAll();
	public List<Sport> getAllWatchByMemno(Integer memno);
	public List<String> groupbydateGPS(Integer memno);
	public List<Sport> getAllGPSbydate(Integer memno,String date);
	public List<Sport> getGPSSumbydate(Integer memno);
//	給朱朱、文澤用，其他參考
	public List<Sport> getAllSumbydate(Integer memno);
	public Sport getAllSum();
	public Sport getSumByMem(Integer memno);
	public List<Sport> getWatchSumbydate(Integer memno);
	public List<Sport> getAllAvgbydate();
	public List<Sport> findGPSByMemno(Integer memno);
	public Sport findByPrimaryKey(Integer sportrec_no);
	public int sportInsert(Sport sport);
	public int sportDelete(Integer sportrec_no);
	public List<Sport> getAllByMemno(Integer memno);
	Integer getDurCount(Integer memno, Date stdate, Date eddate);
	Integer getDurWeek(Integer memno, Date date);
}
