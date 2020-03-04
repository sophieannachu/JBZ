package iii.jbz.mobile.bpcheck;

import java.util.List;

public interface BPCheckDAO_interface {
	public int insert(BPCheckVO bpCheckVO);
	public int update(BPCheckVO bpCheckVO);
	public int delete(Integer bpCheckno);
	public List<BPCheckVO> getAllbymemno(Integer memno);

}
