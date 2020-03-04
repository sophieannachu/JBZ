package idv.ron.server.gpssport;

import java.util.List;

public interface GPSSportDao_interface {
	List<GPSSport> getAll();
	List<GPSSport> findByMemno(Integer memno);
	int GPSinsert(GPSSport gpssport);
}
