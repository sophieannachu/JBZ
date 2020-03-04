package com.func.model;

import java.util.List;

public class FuncService {
	private FuncDAO_interface dao;

	public FuncService() {
		dao=new FuncJNDIDAO();
	}
	
	public List<Integer> getFuncNo(){
		return dao.getFuncNo();
	}
	
	public List<FuncVO> getAllFunc(){
		return dao.getAll();
	}
	
	
}
