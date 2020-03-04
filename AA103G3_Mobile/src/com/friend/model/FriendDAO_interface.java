package com.friend.model;

import java.sql.Date;
import java.util.List;


public interface FriendDAO_interface {
	public void insert(FriendVO friendVO);
	public void update(FriendVO friendVO);
	public void delete(Integer memno,Integer frino);
	public FriendVO findByPrimaryKey(Integer memno,Integer frino);
	public List<FriendVO> getAllbymemno(Integer memno,String status);
	public List<FriendVO> getAllbyfrino(Integer frino,String status);
}
