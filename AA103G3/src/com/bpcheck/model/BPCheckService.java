package com.bpcheck.model;

import java.util.Date;
import java.util.List;


public class BPCheckService {
private BPCheckDAO_interface dao;
	
	public BPCheckService(){
		dao = new BPCheckDAO();
	}
	
	public BPCheckVO addBPCheck(Integer sPressure,Integer dPressure,
			Date checkDate,Integer memno){
		
		BPCheckVO bpCheckVO = new BPCheckVO();
		
		bpCheckVO.setsPressure(sPressure);
		bpCheckVO.setdPressure(dPressure);
		bpCheckVO.setCheckDate(checkDate);
		bpCheckVO.setMemno(memno);;
		dao.insert(bpCheckVO);
				
		return bpCheckVO;
	}
	
	public BPCheckVO updateBPCheck(Integer bpCheckno ,Integer sPressure,Integer dPressure,
			Date checkDate,Integer memno){
		
		BPCheckVO bpCheckVO = new BPCheckVO();
		
		bpCheckVO.setBpCheckno(bpCheckno);
		bpCheckVO.setsPressure(sPressure);
		bpCheckVO.setdPressure(dPressure);
		bpCheckVO.setCheckDate(checkDate);
		bpCheckVO.setMemno(memno);;
		dao.update(bpCheckVO);
				
		return bpCheckVO;
	}
	
	public void deleteBPCheck(Integer bpCheckno) {
		dao.delete(bpCheckno);
	}

	public BPCheckVO getOneBPCheck(Integer bpCheckno) {
		return dao.findByPrimaryKey(bpCheckno);
	}

//	public BPCheckVO getOneNewestBPCheck(Integer memno,java.sql.Date checkDate){
//		return dao.findNewestByDate(memno,checkDate);
//	}
	//µ¹¦¶¦¶¥Îªº	
	public List<BPCheckVO> getAllByMemBPCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
	
	public BPCheckVO getOneNewestBPCheck(Integer memno){
		return dao.findNewestCheck(memno);
	}
	
	public Integer getTotalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate){
		return dao.getTotalTimes(memno, stdate, eddate);
	}
	public Integer getTodayTimes(Integer memno) {
		return dao.getTodayTimes(memno);
	}
	public Integer getGoalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate, Integer value){
		return dao.getGoalTimes(memno, stdate, eddate, value);
	}
}
