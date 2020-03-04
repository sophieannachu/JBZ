package com.hpl.model;

import java.util.List;

public interface HplDAO_interface {
	public void insert(HplVO hplVO);
	public void delete(Integer hpno);
	public HplVO getOne(Integer hpno);
	public List<HplVO> getAll_Now(Integer memno);
	public List<HplVO> getAll_Past(Integer memno);
//	public void update(HplVO hplVO);
//	public byte[] getImg(Integer memno);
}
