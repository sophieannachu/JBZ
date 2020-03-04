package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService(){
		dao=new MemJNDIDAO();
	}
	public MemVO addMem(String id, String acc, String pwd, String name, String sex, Date bir, Integer height, Integer weight, String phone, String email, String act_type, String sos1, String sos2, String sos3, String blood, String sugar, String oil, String bone, String breathe, String heart, String stom, byte[] photo){
		
		MemVO memVO=new MemVO();
		memVO.setId(id);
		memVO.setAcc(acc);
		memVO.setPwd(pwd);
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBir(bir);
		memVO.setHeight(height);
		memVO.setWeight(weight);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAct_type(act_type);
		memVO.setSos1(sos1);
		memVO.setSos2(sos2);
		memVO.setSos3(sos3);
		memVO.setBlood(blood);
		memVO.setSugar(sugar);
		memVO.setOil(oil);
		memVO.setBone(bone);
		memVO.setBreathe(breathe);
		memVO.setHeart(heart);
		memVO.setStom(stom);
		memVO.setPhoto(photo);
		dao.insert(memVO);
		return memVO;
	}
	public MemVO updateMem(Integer memno, String id, String acc, String pwd, String name, String sex, Date bir, Integer height, Integer weight, String phone, String email, String act_type, String sos1, String sos2, String sos3, String blood, String sugar, String oil, String bone, String breathe, String heart, String stom, byte[] photo){
		
		MemVO memVO=new MemVO();
		memVO.setMemno(memno);
		memVO.setId(id);
		memVO.setAcc(acc);
		memVO.setPwd(pwd);
		memVO.setName(name);
		memVO.setSex(sex);
		memVO.setBir(bir);
		memVO.setHeight(height);
		memVO.setWeight(weight);
		memVO.setPhone(phone);
		memVO.setEmail(email);
		memVO.setAct_type(act_type);
		memVO.setSos1(sos1);
		memVO.setSos2(sos2);
		memVO.setSos3(sos3);
		memVO.setBlood(blood);
		memVO.setSugar(sugar);
		memVO.setOil(oil);
		memVO.setBone(bone);
		memVO.setBreathe(breathe);
		memVO.setHeart(heart);
		memVO.setStom(stom);
		memVO.setPhoto(photo);
		dao.update(memVO);
		return memVO;
	}
	public void deleteMem(Integer memno){
		
		dao.delete(memno);
	}
	public MemVO getOneMem(Integer memno){
		
		return dao.findByPrimaryKey(memno);
	}
	public List<MemVO> getAllMem(){
		return dao.getAll();
	}
	
	public byte[] getImg(Integer memno){
		return dao.getImg(memno);
	}
	
	public MemVO getOneByAccMem(String acc){		
		return dao.findByAcc(acc);
	}
	public List<MemVO> findByKeyWord(String keyWord){
		return dao.findByKeyPoint(keyWord);
	}
	
	public void updatePhoto(Integer memno, byte[] photo){
		dao.updatePhoto(memno, photo);
	}
	
	public void updatecpt(Integer memno, String cpt){
		dao.updatecpt(memno, cpt);
	}
}
