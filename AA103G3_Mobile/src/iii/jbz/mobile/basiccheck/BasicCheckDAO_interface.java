package iii.jbz.mobile.basiccheck;

import java.sql.Date;
import java.util.List;


public interface BasicCheckDAO_interface {
	public int insert(BasicCheckVO basicCheckVO);
	public int update(BasicCheckVO basicCheckVO);
	public int delete(Integer basicCheckno);
	public List<BasicCheckVO> getAllbymemno(Integer memno);
	
}
