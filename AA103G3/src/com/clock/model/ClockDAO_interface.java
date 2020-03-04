package com.clock.model;

import java.sql.Date;
import java.util.List;



public interface ClockDAO_interface {
	public void insert(ClockVO clockVO);
	public void update(ClockVO clocktVO);
	public void delete(Integer clockno);
	public ClockVO findByPrimaryKey(Integer clockno);
	public List<ClockVO> getAll();
	
}
