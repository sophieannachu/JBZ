package com.groupList.model;

import java.util.*;

public interface GroupListDAO_interface {
          public void insert(GroupListVO groupListVO);
//          public void update(GroupListVO groupListVO);
          public void delete(Integer groupno,Integer memno);
          public GroupListVO findByPrimaryKey(Integer groupno,Integer memno);
          public List<GroupListVO> getAll();
          public List<GroupListVO> getOneMyGroup(Integer memno);
          public Integer getCountMyGroup(Integer memno);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<foodVo> getAll(Map<String, String[]> map); 
}
