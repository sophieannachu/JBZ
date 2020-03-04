package iii.jbz.mobile.sleepcheck;

import java.util.List;


public interface SleepCheckDAO_interface {
	public int insert(SleepCheckVO sleepCheckVO);
	public int update(SleepCheckVO sleepCheckVO);
	public int delete(Integer sleepCheckno);
	public List<SleepCheckVO> getAllbymemno(Integer memno);
}
