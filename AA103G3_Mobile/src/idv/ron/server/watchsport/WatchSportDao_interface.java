package idv.ron.server.watchsport;

import java.util.List;

public interface WatchSportDao_interface {
	List<WatchSport> getAll();
	List<WatchSport> findByMemno(Integer memno);
	int watchinsert(WatchSport watchsport);
}

