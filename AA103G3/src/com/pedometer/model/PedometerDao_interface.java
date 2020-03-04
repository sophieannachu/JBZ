package com.pedometer.model;

import java.sql.Timestamp;
import java.util.List;

public interface PedometerDao_interface {
	List<Pedometer> findByMemno(Integer memno);
	List<Pedometer> findByMemnoToday(Integer memno);
	int pedoInsert(Pedometer pedometer);
	Integer getSumByMemno(Integer memno);
	Integer getSumPedo();

}
