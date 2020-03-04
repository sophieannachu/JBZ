package iii.jbz.mobile.bgcheck;

import java.util.List;



public class BGCheckService {
	private BGCheckDAO_interface dao;
	
	public BGCheckService(){
		dao = new BGCheckDAO();
	}
	
	public int addBGCheck(BGCheckVO bgCheckVO){
		int count = 0;
		count = dao.insert(bgCheckVO);	
		return count;
	}
	
	public int updateBGCheck(BGCheckVO bgCheckVO){
		int count = 0;
		count = dao.update(bgCheckVO);	
		return count;
	}
	
	public int deleteBGCheck(Integer bgCheckno) {
		int count = 0;
		count = dao.delete(bgCheckno);
		return count;
	}


	public List<BGCheckVO> getAllByMemBGCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
}
