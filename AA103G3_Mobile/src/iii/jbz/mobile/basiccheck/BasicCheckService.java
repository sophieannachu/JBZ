package iii.jbz.mobile.basiccheck;

import java.util.Date;
import java.util.List;

public class BasicCheckService {
		
	private BasicCheckDAO_interface dao;
	
	public BasicCheckService(){
		dao = new BasicCheckDAO();
	}
	
	public int addBasicCheck(BasicCheckVO basicCheckVO){
		int count = 0;
		count = dao.insert(basicCheckVO);	
		return count;
	}
	
	public int updateBasicCheck(BasicCheckVO basicCheckVO){
		int count = 0;
		count = dao.update(basicCheckVO);	
		return count;
	}
	
	public int deleteBasicCheck(Integer basicCheckno) {
		int count = 0;
		count = dao.delete(basicCheckno);
		return count;
	}


	public List<BasicCheckVO> getAllByMemBasicCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
}
