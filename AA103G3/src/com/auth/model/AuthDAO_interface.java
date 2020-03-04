package com.auth.model;

import java.util.List;

public interface AuthDAO_interface {
	public void insert(AuthVO authVO);
	public void update(AuthVO authVO);
	public void delete(Integer empno, Integer funcno);
	public AuthVO findByPrimaryKey(Integer empno, Integer funcno);
	public List<AuthVO> getAll();
	public List<Integer> getOne(Integer empno);
	public void insertOne(Integer empno, String[] auth);
	public void insertOne(Integer empno, Integer[] auth);
	public void updateOne(Integer empno, String[] auth);
	public void updateOne(Integer empno, Integer[] auth);
	public void deleteOne(Integer empno);
}
