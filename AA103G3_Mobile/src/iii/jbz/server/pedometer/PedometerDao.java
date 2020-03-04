package iii.jbz.server.pedometer;

import java.sql.Connection;
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

public class PedometerDao implements PedometerDao_interface{
	
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
	public List<Pedometer> findByMemno(Integer memno) {
		String sql = "SELECT pedorec_no, memno, to_char(pedo_start,'yyyy-mm-dd hh:mm:ss') pedo_start, "
				+ "to_char(pedo_end,'yyyy-mm-dd hh:mm:ss') pedo_end, pedo_total, "
				+ "pedo_acttime FROM pedorec WHERE memno = ? ORDER BY pedo_end DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Pedometer> pedometerList = new ArrayList<Pedometer>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer pedorec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Timestamp pedo_start = rs.getTimestamp(3);
				Timestamp pedo_end = rs.getTimestamp(4);
				Integer pedo_total = rs.getInt(5);
				Integer pedo_acttime = rs.getInt(6);
				
				Pedometer pedometer = new Pedometer(pedorec_no, memnoOut, pedo_start, pedo_end, pedo_total, pedo_acttime);
				pedometerList.add(pedometer);
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
		return pedometerList;
	}

	@Override
	public List<Pedometer> findByMemnoToday(Integer memno) {
		String sql = "SELECT pedorec_no, memno, to_char(pedo_start,'yyyy-mm-dd hh:mm:ss') pedo_start, "
				+ " to_char(pedo_end,'yyyy-mm-dd hh:mm:ss') pedo_end, pedo_total, "
				+ " pedo_acttime FROM pedorec WHERE memno = ? "
				+ " AND TO_CHAR(pedo_start,'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Pedometer> pedometerList = new ArrayList<Pedometer>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer pedorec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Timestamp pedo_start = rs.getTimestamp(3);
				Timestamp pedo_end = rs.getTimestamp(4);
				Integer pedo_total = rs.getInt(5);
				Integer pedo_acttime = rs.getInt(6);
				
				Pedometer pedometer = new Pedometer(pedorec_no, memnoOut, pedo_start, pedo_end, pedo_total, pedo_acttime);
				pedometerList.add(pedometer);
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
		System.out.println("size"+pedometerList.size());
		return pedometerList;
	}

	@Override
	public int pedoInsert(Pedometer pedometer) {
		int count = 0;
		String sql = "INSERT INTO pedorec"
				+ "(pedorec_no, memno, pedo_start, pedo_end, pedo_total, pedo_acttime)"
				+ "VALUES(pedorec_no_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, pedometer.getMemno());
			ps.setTimestamp(2, pedometer.getPedo_start());
			ps.setTimestamp(3, pedometer.getPedo_end());
			ps.setInt(4, pedometer.getPedo_total());
			ps.setInt(5, pedometer.getPedo_acttime());
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
