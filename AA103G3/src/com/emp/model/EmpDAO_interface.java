package com.emp.model;

import java.util.List;

public interface EmpDAO_interface {
	public void insert(EmpVO empVo);
	public void update(EmpVO empVO);
	public void delete(Integer empno);
	public EmpVO findByPrimaryKey(Integer empno);
	public List<EmpVO> getAll();
	public byte[] getImg(Integer empno);
	public Integer getEmpno(String acc);
	public EmpVO getOneByAcc(String acc);
//	public void updateImg(EmpVO empVO);
}
