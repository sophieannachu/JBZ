package idv.ron.server.mem;

import java.util.List;

public interface MemDao {
	int insert(Mem mem, byte[] photo);

	int update(Mem mem, byte[] photo);

	int delete(int id);
	
	Mem findByMemno(int id);

	List<Mem> getAll();

	byte[] getImage(int id);
	
	int updateRegId(int memno, String regId);
	
	int deleteRegId(int memno);
}
