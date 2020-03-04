package com.hpl.model;

import java.sql.Date;
import java.util.List;

public class HplService {
	private HplDAO_interface dao;
	
	public HplService(){
		dao=new HplJNDIDAO();
	}
	public void addHpl(Integer memno, String hpname, Date hpstdate, Integer hpstep, Integer hpexer, Integer hpbmi, Double hpwaist, Integer hpcal, Integer hpbp, Integer hpbg, Date hpeddate){
		HplVO hplVO=new HplVO();
		hplVO.setMemno(memno);
		hplVO.setHpname(hpname);
		hplVO.setHpstdate(hpstdate);
		hplVO.setHpstep(hpstep);
		hplVO.setHpexer(hpexer);
		hplVO.setHpbmi(hpbmi);
		hplVO.setHpwaist(hpwaist);
		hplVO.setHpcal(hpcal);
		hplVO.setHpbp(hpbp);
		hplVO.setHpbg(hpbg);
		hplVO.setHpeddate(hpeddate);
		dao.insert(hplVO);
	}
	
	public void delete(Integer hpno){
		dao.delete(hpno);
	}
	
	public HplVO getOne(Integer hpno){
		return dao.getOne(hpno);
	}
	
	public List<HplVO> getAll_Now(Integer memno){
		return dao.getAll_Now(memno);
	}
	public List<HplVO> getAll_Past(Integer memno){
		return dao.getAll_Past(memno);
	}
}
