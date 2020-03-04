package com.sleepcheck.model;


import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

public class SleepCheckService {
	private SleepCheckDAO_interface dao;
	
	public SleepCheckService(){
		dao = new SleepCheckDAO();
	}
	
	public SleepCheckVO addSleepCheck(Timestamp bedTime,Timestamp wakeTime,
			Integer sleepTime,Integer memno){
		
		SleepCheckVO sleepCheckVO = new SleepCheckVO();
		
		sleepCheckVO.setBedTime(bedTime);
		sleepCheckVO.setWakeTime(wakeTime);
		sleepCheckVO.setSleepTime(sleepTime);
		sleepCheckVO.setMemno(memno);
		dao.insert(sleepCheckVO);
				
		return sleepCheckVO;
	}
	
	public SleepCheckVO updateSleepCheck(Integer sleepCheckno ,Timestamp bedTime,Timestamp wakeTime,
			Integer sleepTime,Integer memno){
		
		SleepCheckVO sleepCheckVO = new SleepCheckVO();
		
		sleepCheckVO.setSleepCheckno(sleepCheckno);
		sleepCheckVO.setBedTime(bedTime);
		sleepCheckVO.setWakeTime(wakeTime);
		sleepCheckVO.setSleepTime(sleepTime);
		sleepCheckVO.setMemno(memno);
		dao.update(sleepCheckVO);
				
		return sleepCheckVO;
	}
	
	public void deleteSleepCheck(Integer sleepCheckno) {
		dao.delete(sleepCheckno);
	}

	public SleepCheckVO getOneSleepCheck(Integer sleepCheckno) {
		return dao.findByPrimaryKey(sleepCheckno);
	}

	public List<SleepCheckVO> getAllSleepCheck() {
		return dao.getAll();
	}
	//µ¹¦¶¦¶¥Îªº	
	public List<SleepCheckVO> getAllByMemSleepCheck(Integer memno) {
		return dao.getAllbymemno(memno);
	}
	
}
