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
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<foodVo> getAll(Map<String, String[]> map); 
}
