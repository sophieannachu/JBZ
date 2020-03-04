package com.bgcheck.model;

import java.util.Date;
import java.util.List;

public class BGCheckService {
	private BGCheckDAO_interface dao;
	
	public BGCheckService(){
		dao = new BGCheckDAO();
	}
	
	public BGCheckVO addBGCheck(Integer bgBfMeat,Integer bgAfMeat,
			Integer bgBfSleep,Date checkDate,Integer memno){
		
		BGCheckVO bgCheckVO = new BGCheckVO();
		
		bgCheckVO.setBgBfMeat(bgBfMeat);
		bgCheckVO.setBgAfMeat(bgAfMeat);
		bgCheckVO.setBgBfSleep(bgBfSleep);
		bgCheckVO.setCheckDate(checkDate);
		bgCheckVO.setMemno(memno);;
		dao.insert(bgCheckVO);
				
		return bgCheckVO;
	}
	
	public BGCheckVO updateBGCheck(Integer bgCheckno ,Integer bgBfMeat,Integer bgAfMeat,
			Integer bgBfSleep,Date checkDate,Integer memno){
		
		BGCheckVO bgCheckVO = new BGCheckVO();
		
		bgCheckVO.setBgCheckno(bgCheckno);
		bgCheckVO.setBgBfMeat(bgBfMeat);
		bgCheckVO.setBgAfMeat(bgAfMeat);
		bgCheckVO.setBgBfSleep(bgBfSleep);
		bgCheckVO.setCheckDate(checkDate);
		bgCheckVO.setMemno(memno);;
		dao.update(bgCheckVO);
				
		return bgCheckVO;
	}
	
	public void deleteBGCheck(Integer bgCheckno) {
		dao.delete(bgCheckno);
	}

	public BGCheckVO getOneBGCheck(Integer bgCheckno) {
		return dao.findByPrimaryKey(bgCheckno);
	}

	//µ¹¦¶¦¶¥Îªº	
	public List<BGCheckVO> getAllByMemBGCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
	
	public BGCheckVO getAllAvgNewest(Integer memno){
		return dao.getAllAvgNewest(memno);
	}
	public Integer getTotalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate){
		return dao.getTotalTimes(memno, stdate, eddate);
	}
	public Integer getTodayTimes(Integer memno){
		return dao.getTodayTimes(memno);
	}
	public Integer getGoalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate, Integer value){
		return dao.getGoalTimes(memno, stdate, eddate, value);
	}
}
