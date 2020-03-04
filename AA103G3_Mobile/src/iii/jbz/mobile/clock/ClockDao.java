package iii.jbz.mobile.clock;

import java.util.List;

public interface ClockDao {
	int insert(Clock clock);

	int update(Clock clock);

	int delete(int id);

	Clock findById(int id);

	List<Clock> getAll();

	byte[] getImage(int id);
}
