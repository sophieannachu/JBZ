package idv.ron.server.watchsport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import idv.ron.server.main.Common;

public class WatchSportDaoSqlImpl implements WatchSportDao_interface{

	@Override
	public List<WatchSport> getAll() {
		String sql = "SELECT watchrec_no, memno, watchtype_no, to_char(watch_date,'yyyy-mm-dd hh:mm:ss') watch_date, watch_duration, watch_cal FROM watchrec ORDER BY watch_date DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<WatchSport> sportList = new ArrayList<WatchSport>();
			while (rs.next()) {
				Integer watchrec_no = rs.getInt(1);
				Integer memno = rs.getInt(2);
				Integer watchtype_no = rs.getInt(3);
				Timestamp watch_date = rs.getTimestamp(4);
				Integer watch_duration = rs.getInt(5);
				Integer watch_cal = rs.getInt(6);
				
				WatchSport sport = new WatchSport(watchrec_no, memno, watchtype_no, watch_date, watch_duration, watch_cal);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException | ClassNotFoundException e) {
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
	public List<WatchSport> findByMemno(Integer memno) {
		String sql = "SELECT watchrec_no, memno, watchtype_no, to_char(watch_date,'yyyy-mm-dd hh:mm:ss') watch_date, watch_duration, watch_cal FROM watchrec WHERE memno = ? ORDER BY watch_date DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<WatchSport> sportList = new ArrayList<WatchSport>();
			while (rs.next()) {
				Integer watchrec_no = rs.getInt(1);
				Integer memno2 = rs.getInt(2);
				Integer watchtype_no = rs.getInt(3);
				Timestamp watch_date = rs.getTimestamp(4);
				Integer watch_duration = rs.getInt(5);
				Integer watch_cal = rs.getInt(6);
				
				WatchSport sport = new WatchSport(watchrec_no, memno2, watchtype_no, watch_date, watch_duration, watch_cal);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException | ClassNotFoundException e) {
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
	public int watchinsert(WatchSport sport) {
		int count = 0;
		String sql = "INSERT INTO watchrec"
				+ "(memno, watchtype_no, to_char(watch_date,'yyyy-mm-dd hh:mm:ss') watch_date, watch_duration, watch_cal) "
				+ "VALUES(?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Common.DRIVER);
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sport.getMemno());
			ps.setInt(2, sport.getWatchtype_no());
			ps.setTimestamp(3, sport.getWatch_date());
			ps.setInt(4, sport.getWatch_duration());
			ps.setInt(5, sport.getWatch_cal());
			count = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
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
