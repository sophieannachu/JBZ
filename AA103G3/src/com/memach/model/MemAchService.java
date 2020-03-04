package com.memach.model;

import java.sql.Date;
import java.util.List;



public class MemAchService {
private MemAchDAO_interface dao;
	
	public MemAchService(){
		dao = new MemAchDAO();
	}
	
	public MemAchVO addMemAch(Integer memno,Integer achno,
			Date comDate){
		
		MemAchVO memAchVO = new MemAchVO();
		
		memAchVO.setMemno(memno);
		memAchVO.setAchno(achno);
		memAchVO.setComDate(comDate);
		dao.insert(memAchVO);
				
		return memAchVO;
	}
	
	public void deleteMemAch(Integer memno, Integer achno) {
		dao.delete(memno,achno);
	}

	public MemAchVO getOneMemAch(Integer memno, Integer achno) {
		return dao.findByPrimaryKey(memno,achno);
	}
	
	public List<MemAchVO> getAllByMemMemAch(Integer memno){
		return dao.getAllbymemno(memno);
	}
	
	public List<MemAchVO> getAll(){
		return dao.getAll();
	}
}
