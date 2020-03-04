package iii.jbz.server.sport;

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

public class SportDao implements SportDao_interface{

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
	public List<Sport> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sport> findByMemno(Integer memno) {
		String sql = "SELECT sportrec_no, memno, type_no, to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, sport_duration, gps_distance, sport_cal, gps_track FROM sportrec WHERE memno = ? ORDER BY sport_date DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Sport> sportList = new ArrayList<Sport>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);
				
				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance, sport_cal, gps_track);
				sportList.add(sport);
			}
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
		return sportList;
	}

	@Override
	public int sportInsert(Sport sport) {
		int count = 0;
		String sql = "INSERT INTO sportrec"
				+ "(sportrec_no, memno, type_no, sport_date, sport_duration, gps_distance, sport_cal, gps_track)"
				+ "VALUES(sportrec_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sport.getMemno());
			ps.setInt(2, sport.getType_no());
			ps.setTimestamp(3, sport.getSport_date());
			ps.setInt(4, sport.getSport_duration());
			ps.setFloat(5, sport.getGps_distance());
			ps.setDouble(6, sport.getSport_cal());
			ps.setString(7, sport.getGps_track());
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

	@Override
	public int sportDelete(Integer sportrec_no) {
		int count = 0;
		String sql = "DELETE FROM sportrec WHERE sportrec_no = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sportrec_no);
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
