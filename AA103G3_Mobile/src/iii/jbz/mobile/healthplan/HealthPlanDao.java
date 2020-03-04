package iii.jbz.mobile.healthplan;

import java.util.List;

public interface HealthPlanDao {
	int insert(HealthPlanVO healthPlanVO);

	int update(HealthPlanVO healthPlanVO);

	int delete(int id);

	HealthPlanVO findById(int id);

	List<HealthPlanVO> getAllByMemno(int memno);

	byte[] getImage(int id);
}
