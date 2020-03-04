package iii.jbz.server.analysis;

import java.sql.Date;
import java.util.List;


public interface BasicCheckDAO_interface {
	public List<BasicCheckVO> getAllbymemno(Integer memno);
	public List<BasicCheckVO> getAll();
}
