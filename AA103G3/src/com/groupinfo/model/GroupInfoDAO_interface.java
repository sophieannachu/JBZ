package com.groupinfo.model;

import java.util.*;

import com.groupList.model.GroupListVO;

public interface GroupInfoDAO_interface {
          public void insert(GroupInfoVO groupInfoVO);
          public void update(GroupInfoVO groupInfoVO);
          public void update_item(GroupInfoVO groupInfoVO);
          public void update_EditGroupOK(GroupInfoVO groupInfoVO);
          public void delete(Integer groupno);
//          public void getOneMyGroupName(GroupListVO groupListVO);
//          public void insertOne(GroupInfoVO groupInfoVo);
          public byte[] getImg(Integer groupInfoPic);
          public GroupInfoVO findByPrimaryKey(Integer groupno);
          public List<GroupInfoVO> getOneNewsGroup(Integer memno);
          public List<GroupInfoVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<foodVo> getAll(Map<String, String[]> map); 
		Integer getCountGroup();
}
