package com.basiccheck.model;

import java.util.Date;
import java.util.List;

public class BasicCheckService {
		
	private BasicCheckDAO_interface dao;
	
	public BasicCheckService(){
		dao = new BasicCheckDAO();
	}
	
	public BasicCheckVO addBasicCheck(Integer weight,Double bmi,
			Double bmr,Double bFat,Double waisyline,
			Date checkDate,Integer memno){
		
		BasicCheckVO basicCheckVO = new BasicCheckVO();
		
		basicCheckVO.setWeight(weight);
		basicCheckVO.setBmi(bmi);
		basicCheckVO.setBmr(bmr);
		basicCheckVO.setbFat(bFat);
		basicCheckVO.setWaisyline(waisyline);
		basicCheckVO.setCheckDate(checkDate);
		basicCheckVO.setMemno(memno);
		dao.insert(basicCheckVO);
				
		return basicCheckVO;
	}
	
	public BasicCheckVO updateBasicCheck(Integer basicCheckno ,Integer weight,Double bmi,
			Double bmr,Double bFat,Double waisyline,
			Date checkDate,Integer memno){
		
		BasicCheckVO basicCheckVO = new BasicCheckVO();
		
		basicCheckVO.setBasicCheckno(basicCheckno);
		basicCheckVO.setWeight(weight);
		basicCheckVO.setBmi(bmi);
		basicCheckVO.setBmr(bmr);
		basicCheckVO.setbFat(bFat);
		basicCheckVO.setWaisyline(waisyline);
		basicCheckVO.setCheckDate(checkDate);
		basicCheckVO.setMemno(memno);
		dao.update(basicCheckVO);
				
		return basicCheckVO;
	}
	
	public void deleteBasicCheck(Integer basicCheckno) {
		dao.delete(basicCheckno);
	}

	public BasicCheckVO getOneBasicCheck(Integer basicCheckno) {
		return dao.findByPrimaryKey(basicCheckno);
	}
	
	public BasicCheckVO getByDateMemBasicCheck(Integer memno,java.sql.Date checkDate) {
		return dao.getByDateMem(memno,checkDate);
	}

	public List<BasicCheckVO> getAllBasicCheck() {
		return dao.getAll();
	}
	//µ¹¦¶¦¶¥Îªº	
	public List<BasicCheckVO> getAllByMemBasicCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
	public BasicCheckVO getOneAVGBasicCheck_NEWEST_WEIGHT2(Integer memno, java.sql.Date stdate){
		return dao.findAVG_NEWEST_WEIGHT2(memno, stdate);
	}
	public Double getOneAVGBasicCheck_NEWEST_WAIST2(Integer memno, java.sql.Date stdate){
		return dao.findAVG_NEWEST_WAIST2(memno, stdate);
	}
}
