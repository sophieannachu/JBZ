package com.sporttype.model;

import java.util.List;

public class SportTypeService {
	private SportTypeDAO_interface dao;
	
	public SportTypeService(){
		dao=new SportTypeDAO();
	}
	
	public List<SportTypeVO> getAll(){
		return dao.getAll();
	}
}
