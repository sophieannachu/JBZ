package com.pedometer.model;

import java.sql.Timestamp;
import java.util.List;

public interface Pedometer_interface {
	List<Pedometer> findByMemno(Integer memno);
	List<Pedometer> findByMemnoDate(Integer memno,Timestamp pedo_end);
	int pedoInsert(Pedometer pedometer);

}
