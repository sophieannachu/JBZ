package com.emp.model;

import java.sql.Date;
import java.util.List;

public class EmpService {
	private EmpDAO_interface dao;
	
	public EmpService(){
		dao=new EmpJNDIDAO();
	}
	public EmpVO addEmp(String name, String acc, String pwd, String sex, Date birth, String phone, String email, byte[] photo){
		EmpVO empVO=new EmpVO();
		empVO.setName(name);
		empVO.setAcc(acc);
		empVO.setPwd(pwd);
		empVO.setSex(sex);
		empVO.setBirth(birth);
		empVO.setPhone(phone);
		empVO.setEmail(email);
		empVO.setPhoto(photo);
		dao.insert(empVO);
		return empVO;
	}
	
	public EmpVO updateEmp(Integer empno, String name, String acc, String pwd, String sex, Date birth, String phone, String email, byte[] photo){
		EmpVO empVO=new EmpVO();		
		empVO.setEmpno(empno);
		empVO.setName(name);
		empVO.setAcc(acc);
		empVO.setPwd(pwd);
		empVO.setSex(sex);
		empVO.setBirth(birth);
		empVO.setPhone(phone);
		empVO.setEmail(email);
		empVO.setPhoto(photo);
		dao.update(empVO);
		return empVO;
	}

	public void deleteEmp(Integer empno){
		
		dao.delete(empno);

	}
	
	public EmpVO getOneEmp(Integer empno){
		
		return dao.findByPrimaryKey(empno);
		
	}
	
	public List<EmpVO> getAllEmp(){
		
		return dao.getAll();
		
	}
	
	public byte[] getImg(Integer empno){
		return dao.getImg(empno);
	}
	
	public Integer getEmpno(String acc){
		return dao.getEmpno(acc);
	}
	
	public EmpVO getOneByAcc(String acc){
		return dao.getOneByAcc(acc);
	}
}
