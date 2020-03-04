package iii.jbz.server.pedometer;

import java.sql.Timestamp;
import java.util.List;

public class PedometerService {
	private PedometerDao_interface dao;
	
	public PedometerService(){
		dao = new PedometerDao();
	}
	
	public List<Pedometer> findByMemno(Integer memno) {
		return dao.findByMemno(memno);
	}
	
	public List<Pedometer> findByMemnoToday(Integer memno){
		System.out.println("memnoService:"+memno);
		return dao.findByMemnoToday(memno);
	}
	
	public int pedoInsert(Pedometer pedometer) {
		int count = 0;
		count = dao.pedoInsert(pedometer);
		return count;
	}

}
