package com.foodlist.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import com.foodlist.model.FoodVO;
import com.mem.model.MemVO;

public class FoodService {

	private FoodDAO_interface dao;

	public FoodService() {
		dao = new FoodDAO();
	}

	public List<FoodVO> getAll() {
		return dao.getAll();
	}

	public FoodVO getOneFood(Integer fd_no) {
		return dao.findByPrimaryKey(fd_no);
	}
	public FoodVO getOneFood_Name(String fd_name) {
		return dao.findByFood_Name(fd_name);
	}
	public FoodVO updateFood(Integer fd_no ,String fd_name,Integer fd_spone,Integer fd_sptwo,Integer fd_spthr,Integer fd_spfor,Integer fd_spfir,Integer fd_spsix,Integer prot,Integer fat,Integer mono,Integer poly,Integer sfa,Integer trans,Integer cho,Integer carb,Integer sugar,Integer df,Integer na,Integer ca,Integer k,Integer cal) {

		FoodVO foodVO = new FoodVO();
		foodVO.setFd_no(fd_no);
		foodVO.setFd_name(fd_name);
		foodVO.setFd_spone(fd_spone);
		foodVO.setFd_sptwo(fd_sptwo);
		foodVO.setFd_spthr(fd_spthr);
		foodVO.setFd_spfor(fd_spfor);
		foodVO.setFd_spfir(fd_spfir);
		foodVO.setFd_spsix(fd_spsix);
		foodVO.setProt(prot);
		foodVO.setFat(fat);
		foodVO.setMono(mono);
		foodVO.setPoly(poly);
		foodVO.setSfa(sfa);
		foodVO.setTrans(trans);
		foodVO.setCho(cho);
		foodVO.setCarb(carb);
		foodVO.setSugar(sugar);
		foodVO.setDf(df);
		foodVO.setNa(na);
		foodVO.setCa(ca);
		foodVO.setK(k);
		foodVO.setCal(cal);
		
		
		dao.update(foodVO);
		
		return foodVO;
	}
//	public Set<EmpVO> getFoodsByFoodno(Integer fno) {
//		return dao.getEmpsByDeptno(deptno);
//	}
	public FoodVO addFood(String fd_name,Integer fd_spone,Integer fd_sptwo,Integer fd_spthr,Integer fd_spfor,Integer fd_spfir,Integer fd_spsix,Integer prot,Integer fat,Integer mono,Integer poly,Integer sfa,Integer trans,Integer cho,Integer carb,Integer sugar,Integer df,Integer na,Integer ca,Integer k,Integer cal) {
		FoodVO foodVO = new FoodVO();

		foodVO.setFd_name(fd_name);
		foodVO.setFd_spone(fd_spone);
		foodVO.setFd_sptwo(fd_sptwo);
		foodVO.setFd_spthr(fd_spthr);
		foodVO.setFd_spfor(fd_spfor);
		foodVO.setFd_spfir(fd_spfir);
		foodVO.setFd_spsix(fd_spsix);
		foodVO.setProt(prot);
		foodVO.setFat(fat);
		foodVO.setMono(mono);
		foodVO.setPoly(poly);
		foodVO.setSfa(sfa);
		foodVO.setTrans(trans);
		foodVO.setCho(cho);
		foodVO.setCarb(carb);
		foodVO.setSugar(sugar);
		foodVO.setDf(df);
		foodVO.setNa(na);
		foodVO.setCa(ca);
		foodVO.setK(k);
		foodVO.setCal(cal);
		
		
		dao.insert(foodVO);
		
		return foodVO;
	}
	
	public void deleteFood(Integer foodno) {
		dao.delete(foodno);
	}
	
	public List<FoodVO> findByKeyWord(String food_name) {
		return dao.findByKeyWord(food_name);
	}
	public List<FoodVO> findByKeyWord2(String keyWord){
		return dao.findByKeyPoint(keyWord);
	}
}
