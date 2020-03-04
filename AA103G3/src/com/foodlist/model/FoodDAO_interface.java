package com.foodlist.model;

import java.util.*;

import com.mem.model.MemVO;

public interface FoodDAO_interface {
          public void insert(FoodVO foodVO);
          public void update(FoodVO foodVO);
          public void delete(Integer foodno);
          public FoodVO findByPrimaryKey(Integer foodno);
          public FoodVO findByFood_Name(String fd_name);
          public List<FoodVO> getAll();
          public List<FoodVO> findByKeyWord(String fd_name);
          public List<FoodVO> findByKeyPoint(String keyWord);
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<foodVo> getAll(Map<String, String[]> map); 
}
