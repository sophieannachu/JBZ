package iii.jbz.server.analysis;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public interface SleepCheckDAO_interface {

	public List<SleepCheckVO> getAllbymemno(Integer memno);
	public List<SleepCheckVO> getAll();
}
