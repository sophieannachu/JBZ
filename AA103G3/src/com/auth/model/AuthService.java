package com.auth.model;

import java.util.List;

public class AuthService {
	private AuthDAO_interface dao;
	
	public AuthService(){
		dao=new AuthJNDIDAO();
	}
	
	public List<Integer> getOne(Integer empno){
		return dao.getOne(empno);
	}
	
	public void insertOne(Integer empno, String[] auth){
		dao.insertOne(empno, auth);
	}
	
	public void insertOne(Integer empno, Integer[] auth){
		dao.insertOne(empno, auth);
	}
	
	public void updateOne(Integer empno, String[] auth){
		dao.updateOne(empno, auth);
	}
	
	public void updateOne(Integer empno, Integer[] auth){
		dao.updateOne(empno, auth);
	}
	
	public void deleteOne(Integer empno){
		dao.deleteOne(empno);
	}
}
