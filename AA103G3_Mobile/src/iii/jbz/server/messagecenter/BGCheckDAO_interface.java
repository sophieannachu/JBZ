package iii.jbz.server.messagecenter;

import java.sql.Date;
import java.util.*;

public interface BGCheckDAO_interface {

	public List<BGCheckVO> getAll(Integer memno);
	public List<BGCheckVO> findNewestByDate(Integer memno);
	public List<BGCheckVO> findAVGByDate(Integer memno);

}