package iii.jbz.mobile.bgcheck;

import java.util.List;

public interface BGCheckDAO_interface {
	public int insert(BGCheckVO bgCheckVO);
	public int update(BGCheckVO bgCheckVO);
	public int delete(Integer bgCheckno);
	public List<BGCheckVO> getAllbymemno(Integer memno);
}
