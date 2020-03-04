package com.friend.model;

import java.sql.Date;
import java.util.List;


public class FriendService {
	private FriendDAO_interface dao;
	
	public FriendService(){
		dao = new FriendDAO();
	}
	
	public FriendVO addFriend(Integer memno,Integer frino,
			Date addDate,String status){
		
		FriendVO friendVO = new FriendVO();
		
		friendVO.setMemno(memno);
		friendVO.setFrino(frino);
		friendVO.setAddDate(addDate);
		friendVO.setStatus(status);
		dao.insert(friendVO);
				
		return friendVO;
	}
	
	public FriendVO updateFriend(Integer memno,Integer frino,
			Date addDate,String status){
		
		FriendVO friendVO = new FriendVO();
		
		friendVO.setMemno(memno);
		friendVO.setFrino(frino);
		friendVO.setAddDate(addDate);
		friendVO.setStatus(status);
		dao.update(friendVO);
				
		return friendVO;
	}
	
	public void deleteFriend(Integer memno,Integer frino) {
		dao.delete(memno,frino);
	}

	public FriendVO getOneFriend(Integer memno,Integer frino) {
		return dao.findByPrimaryKey(memno,frino);
	}
	
	public List<FriendVO> getAllByMemFriend(Integer memno,String status){
		return dao.getAllbymemno(memno,status);
	}
	
	public List<FriendVO> getAllByFriFriend(Integer frino,String status){
		return dao.getAllbyfrino(frino,status);
	}
}
