package idv.ron.server.gpssport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import idv.ron.server.main.Common;
import sun.rmi.runtime.Log;

public class GPSSportDao implements GPSSportDao_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<GPSSport> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GPSSport> findByMemno(Integer memno) {
		String sql = "SELECT gpsrec_no, memno, gpstype_no, to_char(gps_date,'yyyy-mm-dd hh:mm:ss') gps_date, gps_duration, gps_distance, gps_cal, gps_track FROM gpsrec WHERE memno = ? ORDER BY gps_date DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<GPSSport> gpssportList = new ArrayList<GPSSport>();
			while (rs.next()) {
				Integer gpsrec_no = rs.getInt(1);
				Integer memno2 = rs.getInt(2);
				Integer gpstype_no = rs.getInt(3);
				Timestamp gps_date = rs.getTimestamp(4);
				Integer gps_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double gps_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);
				
				GPSSport gpssport = new GPSSport(gpsrec_no, memno2, gpstype_no, gps_date, gps_duration, gps_distance, gps_cal,gps_track);
				gpssportList.add(gpssport);
			}
			return gpssportList;
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

	@Override
	public int GPSinsert(GPSSport gpssport) {
		int count = 0;
		String sql = "INSERT INTO gpsrec"
				+ "(gpsrec_no, memno, gpstype_no, gps_date, gps_duration, gps_distance, gps_cal, gps_track)"
				+ "VALUES(gpsrec_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, gpssport.getMemno());
			ps.setInt(2, gpssport.getGpstype_no());
			ps.setTimestamp(3, gpssport.getGps_date());
			ps.setInt(4, gpssport.getGps_duration());
			ps.setFloat(5, gpssport.getGps_distance());
			ps.setDouble(6, gpssport.getGps_cal());
			ps.setString(7, gpssport.getGps_track());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

}
