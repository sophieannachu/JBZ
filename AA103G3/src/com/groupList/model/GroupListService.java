package com.groupList.model;

import java.util.List;
import java.util.Set;

import com.groupinfo.model.GroupInfoVO;

public class GroupListService {

	private GroupListDAO_interface dao;

	public GroupListService() {
		dao = new GroupListDAO();
	}

	public List<GroupListVO> getAll() {
		return dao.getAll();
	}

	public GroupListVO getOneGroupList(Integer groupno,Integer memno) {
		return dao.findByPrimaryKey(groupno,memno);
	}
	//取的我參加的糾團
	public List<GroupListVO> getOneMyGroupList(Integer memno) {
		return dao.getOneMyGroup(memno);
	}

//	public Set<EmpVO> getFoodsByFoodno(Integer fno) {
//		return dao.getEmpsByDeptno(deptno);
//	}
	public String addGroupList(Integer groupno,Integer memno) {
		GroupListVO groupListVO = new GroupListVO();
		groupListVO.setGroup_no(groupno);
		groupListVO.setMemno(memno);
		dao.insert(groupListVO);
		return "sucessAdd";
	}
	
	public void deleteGroupList(Integer groupno,Integer memno) {
		dao.delete(groupno,memno);
	}
	public Integer getCountMyGroup(Integer memno) {
		return dao.getCountMyGroup(memno);
	}
}
