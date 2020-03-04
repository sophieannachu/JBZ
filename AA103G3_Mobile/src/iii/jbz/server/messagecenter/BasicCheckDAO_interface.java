package iii.jbz.server.messagecenter;

import java.util.List;

public interface BasicCheckDAO_interface {
	public List<BasicCheckVO> getAll(Integer memno);
	public List<BasicCheckVO> findNewestByDate(Integer memno);
}
