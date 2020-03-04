package com.sleepcheck.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public interface SleepCheckDAO_interface {
	public void insert(SleepCheckVO sleepCheckVO);
	public void update(SleepCheckVO sleepCheckVO);
	public void delete(Integer sleepCheckno);
	public SleepCheckVO findByPrimaryKey(Integer sleepCheckno);
	//µ¹¦¶¦¶¥Îªº
	public List<SleepCheckVO> getAllbymemno(Integer memno);
	public List<SleepCheckVO> getAll();
}
