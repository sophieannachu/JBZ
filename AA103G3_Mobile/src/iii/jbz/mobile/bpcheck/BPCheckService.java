package iii.jbz.mobile.bpcheck;

import java.util.List;

public class BPCheckService {
	private BPCheckDAO_interface dao;
	
	public BPCheckService(){
		dao = new BPCheckDAO();
	}
	
	public int addBPCheck(BPCheckVO bpCheckVO){
		int count = 0;
		count = dao.insert(bpCheckVO);	
		return count;
	}
	
	public int updateBPCheck(BPCheckVO bpCheckVO){
		int count = 0;
		count = dao.update(bpCheckVO);	
		return count;
	}
	
	public int deleteBPCheck(Integer bpCheckno) {
		int count = 0;
		count = dao.delete(bpCheckno);
		return count;
	}


	public List<BPCheckVO> getAllByMemBPCheck(Integer memno){
		return dao.getAllbymemno(memno);
	}
}
