package com.bpcheck.model;

import java.sql.Date;
import java.util.List;

import com.bgcheck.model.BGCheckVO;

public interface BPCheckDAO_interface {
	public void insert(BPCheckVO bpCheckVO);
	public void update(BPCheckVO bpCheckVO);
	public void delete(Integer bpCheckno);
	public BPCheckVO findByPrimaryKey(Integer bpCheckno);
//	public BPCheckVO findNewestByDate(Integer memno,Date checkDate);
	//µ¹¦¶¦¶¥Îªº
	public List<BPCheckVO> getAllbymemno(Integer memno);
	
	public BPCheckVO findNewestCheck(Integer memno);
	public Integer getTotalTimes(Integer memno, Date stdate, Date eddate);
	public Integer getGoalTimes(Integer memno, Date stdate, Date eddate, Integer value);
	public Integer getTodayTimes(Integer memno);
}
