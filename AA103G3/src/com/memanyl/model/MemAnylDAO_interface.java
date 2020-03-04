package com.memanyl.model;

import java.util.List;

public interface MemAnylDAO_interface {
	public void insert(MemAnylVO memAnylVO);
//	public void update(MemAnylVO memAnylVO);
	public void delete(Integer memAnylno);
	public MemAnylVO findByPrimaryKey(Integer memAnylno);
	public List<MemAnylVO> getAll();
}
