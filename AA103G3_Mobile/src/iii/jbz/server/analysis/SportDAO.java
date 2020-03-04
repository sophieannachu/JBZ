package iii.jbz.server.analysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SportDAO implements SportDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String GET_ALLBYMEM_STMT = "SELECT sportrec_no, memno, type_no, to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, "
			+ "sport_duration, gps_distance, sport_cal, gps_track FROM sportrec WHERE "
			+ "memno =  ? ORDER BY sport_date";
	
	
	@Override
	public List<Sport> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_ALLBYMEM_STMT);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<Sport> sportList = new ArrayList<Sport>();
			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);

				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance,
						sport_cal, gps_track);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

