package com.func.model;

import java.util.List;

public interface FuncDAO_interface {
	public void insert(FuncVO funcVO);
	public void update(FuncVO funcVO);
	public void delete(Integer funcno);
	public FuncVO findByPrimaryKey(Integer funcno);
	public List<FuncVO> getAll();
	public List<Integer> getFuncNo();
}
