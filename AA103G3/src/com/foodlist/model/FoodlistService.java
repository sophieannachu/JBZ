package com.foodlist.model;

import java.sql.Date;
import java.util.List;



public class FoodlistService {
	private Foodlist_interface dao;
	
	public FoodlistService() {
		dao = new FoodlistDAO();
	}

	public FoodlistVO addFoodlist(String fddesc, java.sql.Date fddate,int fdqua,
			int memno, int fd_no) {

		FoodlistVO foodlistVO = new FoodlistVO();

		foodlistVO.setFddesc(fddesc);
		foodlistVO.setFddate(fddate);
		foodlistVO.setFdqua(fdqua);
		foodlistVO.setMemno(memno);
		foodlistVO.setFd_no(fd_no);
		dao.insert(foodlistVO);

		return foodlistVO;
	}

	public FoodlistVO updateFoodlist(Integer fdrecno, String fddesc, java.sql.Date fddate,int fdqua,
			int memno, int fd_no) {

		FoodlistVO foodlistVO = new FoodlistVO();

		foodlistVO.setFdrecno(fdrecno);
		foodlistVO.setFddesc(fddesc);
		foodlistVO.setFddate(fddate);
		foodlistVO.setFdqua(fdqua);
		foodlistVO.setMemno(memno);
		foodlistVO.setFd_no(fd_no);
		dao.update(foodlistVO);

		return foodlistVO;
	}

	public void deleteFoodlist(Integer fdrecno) {
		dao.delete(fdrecno);
	}

	public FoodlistVO getOneFoodlist(Integer fdrecno) {
		return dao.findByPrimaryKey(fdrecno);
	}

	public List<FoodlistVO> getAll() {
		return dao.getAll();
	}
	public List<FoodlistVO> findByDateAndMemno(Date fddate,Integer memno) {
		return dao.findByDateAndMemno(fddate,memno);
	}
	public List<FoodlistVO> findDateByMemno(Integer memno) {
		return dao.findDateByMemno(memno);
	}
}
