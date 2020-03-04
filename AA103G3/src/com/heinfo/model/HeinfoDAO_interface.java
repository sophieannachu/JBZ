package com.heinfo.model;

import java.util.*;

public interface  HeinfoDAO_interface {
	public void insert(HeinfoVO heinfoVO);
	public void update(HeinfoVO heinfoVO);
	public void delete(Integer heinfono);
	public HeinfoVO findByPrimaryKey(Integer heinfono);
	public List<HeinfoVO> getAll();
	
}
