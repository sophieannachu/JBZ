package iii.jbz.mobile.foodrec;

import java.util.List;

public class FoodListService {
	
	private FoodListDAO_interface dao;
	
	public FoodListService(){
		dao = new FoodListDAO();
	}
	
	public int addFoodList(FoodListVO foodListVO){
		int count = 0;
		count = dao.insert(foodListVO);	
		return count;
	}
	
	public int deleteFoodList(Integer fdrecno) {
		int count = 0;
		count = dao.delete(fdrecno);
		return count;
	}
	
	public List<FoodListVO> getAllByMemFoodList(Integer memno){
		return dao.getAllbymemno(memno);
	}
	
	public List<FoodVO> getAllFoods(){
		return dao.getAllFoods();
	}
	
	public List<FoodVO> getByKeyWord(String fd_name){
		return dao.findByKeyWord(fd_name);
	}
	
}
