package com.clock.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;



public class ClockService {
	private ClockDAO_interface dao;
	public ClockService() {
		dao = new ClockDAO();
	}
	public ClockVO addClock(Timestamp clocktime, String clockinfo,String clocksche,
			String clockring, Integer clocktype,Integer memno) {

		ClockVO clockVO = new ClockVO();

		clockVO.setClocktime(clocktime);
		clockVO.setClockinfo(clockinfo);
		clockVO.setClocksche(clocksche);
		clockVO.setClockring(clockring);
		clockVO.setClocktype(clocktype);
		clockVO.setMemno(memno);
		dao.insert(clockVO);

		return clockVO;
	}

	public ClockVO updateClock(Integer clockno, Timestamp clocktime, String clockinfo,String clocksche,
			String clockring, Integer clocktype,Integer memno) {

		ClockVO clockVO = new ClockVO();

		clockVO.setClockno(clockno);
		clockVO.setClocktime(clocktime);
		clockVO.setClockinfo(clockinfo);
		clockVO.setClocksche(clocksche);
		clockVO.setClockring(clockring);
		clockVO.setMemno(memno);
		dao.update(clockVO);

		return clockVO;
	}

	public void deleteClock(Integer clockno) {
		dao.delete(clockno);
	}

	public ClockVO getOneClock(Integer clockno) {
		return dao.findByPrimaryKey(clockno);
	}

	public List<ClockVO> getAll() {
		return dao.getAll();
	}
//	public List<FoodlistVO> findByDateAndMemno(Date fddate,Integer memno) {
//		return dao.findByDateAndMemno(fddate,memno);
//	}
	
}
