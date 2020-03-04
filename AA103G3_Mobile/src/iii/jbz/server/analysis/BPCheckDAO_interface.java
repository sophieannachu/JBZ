package iii.jbz.server.analysis;

import java.sql.Date;
import java.util.List;

import iii.jbz.server.analysis.BGCheckVO;

public interface BPCheckDAO_interface {
	
	public List<BPCheckVO> getAllbymemno(Integer memno);

}
