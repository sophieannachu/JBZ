package com.sport.model;

import java.sql.Date;
import java.util.List;

public class SportService {
private SportDao_interface dao;
	
	public SportService(){
		dao = new SportDao();
	}
	
	public void deleteSport(Integer sportrec_no) {
		dao.sportDelete(sportrec_no);
	}

	public Sport getOneGPSSport(Integer sportrec_no) {
		return dao.findByPrimaryKey(sportrec_no);
	}

	public List<Sport> getAllGPSByDate(Integer memno,String date){
		return dao.getAllGPSbydate(memno,date);
	}
	public List<Sport> getAllWatchByMemno(Integer memno){
		return dao.getAllWatchByMemno(memno);
	}
	public List<Sport> findGPSByMemno(Integer memno){
		return dao.findGPSByMemno(memno);
	}
	public List<Sport> getGPSSumbydate(Integer memno){
		return dao.getGPSSumbydate(memno);
	}
//	給朱朱、文澤用，其他參考
	public List<Sport> getAllSumbydate(Integer memno){
		return dao.getAllSumbydate(memno);
	}
	public Sport getAllSum(){
		return dao.getAllSum();
	}
	
	public Sport getSumByMem(Integer memno){
		return dao.getSumByMem(memno);
	}
	
	public List<Sport> getAllAvgbydate(){
		return dao.getAllAvgbydate();
	}
	public List<Sport> getWatchSumbydate(Integer memno){
		return dao.getWatchSumbydate(memno);
	}
	
	public List<String> groupbydateGPS(Integer memno){
		return dao.groupbydateGPS(memno);
	}
	
	public List<Sport> getAllByMemno(Integer memno){
		return dao.getAllByMemno(memno);
	}
	
	public Integer getDurWeek(Integer memno, Date date){
		return dao.getDurWeek(memno, date);
	}
	public Integer getDurCount(Integer memno, Date stdate, Date eddate){
		return dao.getDurCount(memno, stdate, eddate);
	}
}
