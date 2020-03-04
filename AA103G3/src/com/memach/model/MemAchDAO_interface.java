package com.memach.model;

import java.util.List;


public interface MemAchDAO_interface {
	public void insert(MemAchVO memAchVO);
//	public void update(Friend friend);
	public void delete(Integer memno,Integer achno);
	public MemAchVO findByPrimaryKey(Integer memno,Integer achno);
	public List<MemAchVO> getAllbymemno(Integer memno);
	public List<MemAchVO> getAll();

}
