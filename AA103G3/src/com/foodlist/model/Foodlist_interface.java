package com.foodlist.model;

import java.sql.Date;
import java.util.List;



public interface Foodlist_interface {
	public void insert(FoodlistVO foodlistVO);
	public void update(FoodlistVO foodlistVO);
	public void delete(Integer fdrenco);
	public FoodlistVO findByPrimaryKey(Integer fdrenco);
	public List<FoodlistVO> getAll();
	public List<FoodlistVO> findByDateAndMemno(Date fddate,Integer memno);
	public List<FoodlistVO> findDateByMemno(Integer memno);
}
