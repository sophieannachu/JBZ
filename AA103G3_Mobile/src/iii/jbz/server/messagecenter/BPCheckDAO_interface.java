package iii.jbz.server.messagecenter;

import java.sql.Date;
import java.util.List;

public interface BPCheckDAO_interface {

	public List<BPCheckVO> getAll(Integer memno);
	public List<BPCheckVO> findNewestByDate(Integer memno);
}
