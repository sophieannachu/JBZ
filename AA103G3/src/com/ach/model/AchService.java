package com.ach.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.memach.model.MemAchVO;

public class AchService {
private AchDAO_interface dao;
	
	public AchService(){
		dao = new AchDAO();
	}
	
	public AchVO addAch(String aname ,Integer achcond){
		
		AchVO achVO = new AchVO();
		
		achVO.setaName(aname);
		achVO.setAchCond(achcond);
		dao.insert(achVO);
				
		return achVO;
	}
	
	public AchVO updateAch(Integer achno, String aname ,Integer achcond){
		
		AchVO achVO = new AchVO();
		
		achVO.setAchno(achno);
		achVO.setaName(aname);
		achVO.setAchCond(achcond);
		dao.update(achVO);
				
		return achVO;
	}
	
	public void deleteAch(Integer achno) {
		dao.delete(achno);
	}

	public AchVO getOneAch(Integer achno) {
		return dao.findByPrimaryKey(achno);
	}

	public List<AchVO> getAllAch() {
		return dao.getAll();
	}
	
	public Set<MemAchVO> getEmpsByDeptno(Integer achno) {
		return dao.getMemsByAchno(achno);
	}
	public Set<MemAchVO> getAll(String aName) {
		return dao.getMemsByAchName(aName);
	}
}
