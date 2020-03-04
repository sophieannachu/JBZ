package com.groupinfo.model;

import java.sql.Timestamp;
import java.util.List;

import com.groupList.model.GroupListVO;


public class GroupInfoService {

	private GroupInfoDAO_interface dao;

	public GroupInfoService() {
		dao = new GroupInfoDAO();
	}

	public List<GroupInfoVO> getAll() {
		return dao.getAll();
	}

	public GroupInfoVO getOneGroupInfo(Integer groupno) {
		return dao.findByPrimaryKey(groupno);
	}

//	public Set<EmpVO> getFoodsByFoodno(Integer fno) {
//		return dao.getEmpsByDeptno(deptno);
//	}
	public void addGroupInfo(GroupInfoVO groupInfoVO) {
		dao.insert(groupInfoVO);
	}
	
	public void deleteGroupInfo(Integer groupno) {
		dao.delete(groupno);
	}
	public void addGroupInfoOne(Integer group_check,Integer memno,String group_name,String group_loc,
			String group_detail,Integer group_count,byte[] groupPic,
			Timestamp group_time,String group_long,String group_lati) {
		GroupInfoVO GroupInfoVO = new GroupInfoVO();
		GroupInfoVO.setMemno(memno);
		GroupInfoVO.setGroup_name(group_name);
		GroupInfoVO.setGroup_check(group_check);
		GroupInfoVO.setGroup_loc(group_loc);
		GroupInfoVO.setGroup_detail(group_detail);
		GroupInfoVO.setGroup_count(group_count);
		GroupInfoVO.setGroup_photo(groupPic);
		GroupInfoVO.setGroup_time(group_time);
		GroupInfoVO.setGroup_long(group_long);
		GroupInfoVO.setGroup_lati(group_lati);
		dao.insert(GroupInfoVO);
	}
	public byte[] showGroupInfoPhoto(Integer groupno) {
		return dao.getImg(groupno);
	}
	public GroupInfoVO updateGroupInfoItem(Integer groupno,Integer group_check,
			Integer memno,String group_name,String group_loc,String group_detail,
			Integer group_count,byte[] groupPic,Timestamp group_time,String group_long,
			String group_lati) {
		GroupInfoVO GroupInfoVO = new GroupInfoVO();
		GroupInfoVO.setGroup_no(groupno);
		GroupInfoVO.setMemno(memno);
		GroupInfoVO.setGroup_name(group_name);
		GroupInfoVO.setGroup_check(group_check);
		GroupInfoVO.setGroup_loc(group_loc);
		GroupInfoVO.setGroup_detail(group_detail);
		GroupInfoVO.setGroup_count(group_count);
		GroupInfoVO.setGroup_photo(groupPic);
		GroupInfoVO.setGroup_time(group_time);
		GroupInfoVO.setGroup_long(group_long);
		GroupInfoVO.setGroup_lati(group_lati);
		dao.update(GroupInfoVO);
		return GroupInfoVO;
	}
	public GroupInfoVO updateGroupInfoItem_one(Integer groupno,
			String group_name,String group_detail,byte[] groupPic,Timestamp group_time) {
		GroupInfoVO GroupInfoVO = new GroupInfoVO();
		GroupInfoVO.setGroup_no(groupno);
		GroupInfoVO.setGroup_name(group_name);
		GroupInfoVO.setGroup_detail(group_detail);
		GroupInfoVO.setGroup_photo(groupPic);
		GroupInfoVO.setGroup_time(group_time);
		dao.update_item(GroupInfoVO);
		return GroupInfoVO;
	}
	public void updateGroupInfoEditGroupOK(Integer groupno){
		GroupInfoVO GroupInfoVO = new GroupInfoVO();
		GroupInfoVO.setGroup_no(groupno);
		dao.update_EditGroupOK(GroupInfoVO);
	}
	
	
	
	public List<GroupInfoVO> getOneNewsGroupSvc(Integer memno){
		return dao.getOneNewsGroup(memno);
	}
	
	public List<GroupInfoVO> getOneMyGroupSvc(Integer memno){
		return dao.getOneNewsGroup(memno);
	}
	
	public Integer getCountGroup(){
		return dao.getCountGroup();
	}
}
