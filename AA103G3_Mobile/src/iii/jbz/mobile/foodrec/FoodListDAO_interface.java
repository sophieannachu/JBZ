package iii.jbz.mobile.foodrec;

import java.util.List;

public interface FoodListDAO_interface {
	public int insert(FoodListVO foodListVO);
	public int delete(Integer fdrecno);
	public List<FoodListVO> getAllbymemno(Integer memno);
	public List<FoodVO> getAllFoods();
	public List<FoodVO> findByKeyWord(String fd_name);

}
