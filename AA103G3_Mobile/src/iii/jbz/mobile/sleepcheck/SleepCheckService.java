package iii.jbz.mobile.sleepcheck;

import java.util.List;

public class SleepCheckService {
	private SleepCheckDAO_interface dao;
	
	public SleepCheckService(){
		dao = new SleepCheckDAO();
	}
	
	public int addSleepCheck(SleepCheckVO sleepCheckVO){
		int count = 0;
		count = dao.insert(sleepCheckVO);	
		return count;
	}
	
	public int updateSleepCheck(SleepCheckVO sleepCheckVO){
		int count = 0;
		count = dao.update(sleepCheckVO);	
		return count;
	}
	
	public int deleteSleepCheck(Integer sleepCheckno) {
		int count = 0;
		count = dao.delete(sleepCheckno);
		return count;
	}


	public List<SleepCheckVO> getAllByMemSleepCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
}
