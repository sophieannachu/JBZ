package com.bgcheck.model;

import java.sql.Date;
import java.util.*;

public interface BGCheckDAO_interface {
		public void insert(BGCheckVO bgCheckVO);
		public void update(BGCheckVO bgCheckVO);
		public void delete(Integer bgCheckno);
		public BGCheckVO findByPrimaryKey(Integer bgCheckno);

		//µ¹¦¶¦¶¥Îªº
		public List<BGCheckVO> getAllbymemno(Integer memno);
		
		public BGCheckVO getAllAvgNewest(Integer memno);
		public Integer getTotalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate);
		public Integer getGoalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate, Integer value);
		public Integer getTodayTimes(Integer memno);
}
