package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public void delete(Integer memno);
	public MemVO findByPrimaryKey(Integer memno);
	public List<MemVO> getAll();
	public byte[] getImg(Integer memno);
	public MemVO findByAcc(String acc);
	public List<MemVO> findByKeyPoint(String keyWord);
	void updatePhoto(Integer memno, byte[] photo);
	void updatecpt(Integer memno, String cpt);
}
