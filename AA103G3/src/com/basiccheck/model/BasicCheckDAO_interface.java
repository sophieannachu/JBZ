package com.basiccheck.model;

import java.sql.Date;
import java.util.List;


public interface BasicCheckDAO_interface {
	public void insert(BasicCheckVO basicCheckVO);
	public void update(BasicCheckVO basicCheckVO);
	public void delete(Integer basicCheckno);
	public BasicCheckVO findByPrimaryKey(Integer basicCheckno);
	//µ¹¦¶¦¶¥Îªº
	public List<BasicCheckVO> getAllbymemno(Integer memno);
	public BasicCheckVO getByDateMem(Integer memno,Date date);
	public List<BasicCheckVO> getAll();
	public BasicCheckVO findAVG_NEWEST_WEIGHT2(Integer memno, Date stdate);
	public Double findAVG_NEWEST_WAIST2(Integer memno, Date stdate);
}
