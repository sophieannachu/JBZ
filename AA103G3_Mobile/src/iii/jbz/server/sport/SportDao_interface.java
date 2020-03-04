package iii.jbz.server.sport;

import java.util.List;

public interface SportDao_interface {
	List<Sport> getAll();
	List<Sport> findByMemno(Integer memno);
	int sportInsert(Sport sport);
	int sportDelete(Integer sportrec_no);
}
