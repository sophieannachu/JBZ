package com.heinfo.model;

import java.util.List;



public class HeinfoService {
	private HeinfoDAO_interface dao;

	public HeinfoService() {
		dao = new HeinfoDAO();
	}

	public HeinfoVO addHeinfo(String heinfoname, String heinfodetail, java.sql.Date heinfodate,
			byte[] heinfophoto) {

		HeinfoVO heinfoVO = new HeinfoVO();

		heinfoVO.setHeinfoname(heinfoname);
		heinfoVO.setHeinfodetail(heinfodetail);
		heinfoVO.setHeinfodate(heinfodate);
		heinfoVO.setHeinfophoto(heinfophoto);
		dao.insert(heinfoVO);

		return heinfoVO;
	}

	public HeinfoVO updateHeinfo(Integer heinfono, String heinfoname, String heinfodetail,
			java.sql.Date heinfodate, byte[] heinfophoto) {

		HeinfoVO heinfoVO = new HeinfoVO();

		heinfoVO.setHeinfono(heinfono);
		heinfoVO.setHeinfoname(heinfoname);
		heinfoVO.setHeinfodate(heinfodate);
		heinfoVO.setHeinfodetail(heinfodetail);
		heinfoVO.setHeinfophoto(heinfophoto);
		dao.update(heinfoVO);

		return heinfoVO;
	}

	public void deleteHeinfo(Integer heinfono) {
		dao.delete(heinfono);
	}

	public HeinfoVO getOneHeinfo(Integer heinfono) {
		return dao.findByPrimaryKey(heinfono);
	}

	public List<HeinfoVO> getAll() {
		return dao.getAll();
	}
}
