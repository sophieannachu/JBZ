package com.memanyl.model;

import java.sql.Date;
import java.util.List;

public class MemAnylService {
private MemAnylDAO_interface dao;
	
	public MemAnylService(){
		dao = new MemAnylDAO();
	}
	
	public MemAnylVO addMemAnyl(Date anylDate,Integer stepAvg,
			Integer calTakeAvg, Integer exeTimeAvg,Integer calBurnAvg){
		
		MemAnylVO memAnylVO = new MemAnylVO();
		
		memAnylVO.setAnylDate(anylDate);
		memAnylVO.setStepAvg(stepAvg);
		memAnylVO.setCalTakeAvg(calTakeAvg);
		memAnylVO.setExeTimeAvg(exeTimeAvg);
		memAnylVO.setCalBurnAvg(calBurnAvg);
		dao.insert(memAnylVO);
				
		return memAnylVO;
	}
	
	public void deleteMemAnyl(Integer memAnylno) {
		dao.delete(memAnylno);
	}

	public MemAnylVO getOneMemAnyl(Integer memAnylno) {
		return dao.findByPrimaryKey(memAnylno);
	}
	
	public List<MemAnylVO> getAllMemAnyl(){
		return dao.getAll();
	}
}
