package iii.jbz.server.pedometer;

import java.sql.Timestamp;
import java.util.List;

public interface PedometerDao_interface {
	List<Pedometer> findByMemno(Integer memno);
	List<Pedometer> findByMemnoToday(Integer memno);
	int pedoInsert(Pedometer pedometer);

}
