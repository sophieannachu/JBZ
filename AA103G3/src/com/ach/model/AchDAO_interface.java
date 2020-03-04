package com.ach.model;

import java.util.List;
import java.util.Set;

import com.memach.model.MemAchVO;

public interface AchDAO_interface {
	public void insert(AchVO achVO);
	public void update(AchVO achVO);
	public void delete(Integer achno);
	public AchVO findByPrimaryKey(Integer achno);
	public Set<MemAchVO> getMemsByAchno(Integer achno);
	public Set<MemAchVO> getMemsByAchName(String aName);
	public List<AchVO> getAll();
}
