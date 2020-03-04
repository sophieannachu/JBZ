package iii.jbz.mobile.clock;

import idv.ron.server.main.Common;
import java.sql.Timestamp;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClockDaoOracleImpl implements ClockDao {

	public ClockDaoOracleImpl() 
	{
		super();
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Clock clock) {
		int count = 0;
		String sql = "INSERT INTO CLOCK"
				+ "(memno, clockno, clocktime, clockinfo, clocksche, clockring, clocktype)"
				+ "VALUES(?, Clock_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, clock.getMemNo());
			ps.setTimestamp(2, clock.getClockTime());
			ps.setString(3, clock.getClockInfo());
			ps.setString(4, clock.getClockSche());
			ps.setString(5, clock.getClockring());
			ps.setInt(6, clock.getClocktype());
	
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
	public int update(Clock clock) {
		int count = 0;
		String sql = "UPDATE CLOCK SET clockno = ?, clocktime = ?, clockinfo = ?, clocksche = ?, clockring = ?, clocktype = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, clock.getClockNo());
			ps.setTimestamp(2, clock.getClockTime());
			ps.setString(3, clock.getClockInfo());
			ps.setString(4, clock.getClockSche());
			ps.setString(5, clock.getClockring());
			ps.setInt(6, clock.getClocktype());
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
	public int delete(int clockno) {
		int count = 0;
		String sql = "DELETE FROM CLOCK WHERE clockno = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, clockno);
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
	public byte[] getImage(int memno) {
		String sql = "SELECT image FROM CLOCK WHERE id = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] typeimage = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				typeimage = rs.getBytes(1);
			}
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
		return typeimage;
	}

	@Override
	public Clock findById(int memno) {
		String sql = "SELECT clockno, clocktime, clockinfo, clocksche, clockring, clocktype FROM CLOCK WHERE memno = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		Clock clock = null;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				int clockno = rs.getInt(2);
				Timestamp clocktime = rs.getTimestamp(3);
				String clockinfo = rs.getString(4);
				String clocksche = rs.getString(5);
				String clockring = rs.getString(6);
				int clocktype = rs.getInt(7);
				clock = new Clock(memno, clockno, clocktime, clockinfo, clocksche, clockring, clocktype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clock;
	}

	@Override
	public List<Clock> getAll() {
		String sql = "SELECT memno, clockno, clocktime, clockinfo, clocksche, clockring, clocktype "
				+ "FROM CLOCK ORDER BY clocktime DESC";
		Connection connection = null;
		PreparedStatement ps = null;
		List<Clock> clockList = new ArrayList<Clock>();
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int memno = rs.getInt(1);
				int clockno = rs.getInt(2);
				Timestamp clocktime = rs.getTimestamp(3);
				String clockinfo = rs.getString(4);
				String clocksche = rs.getString(5);
				String clockring = rs.getString(6);
				int clocktype = rs.getInt(7);
				Clock clock = new Clock(memno, clockno, clocktime, clockinfo, clocksche, clockring, clocktype);
				clockList.add(clock);
			}
			return clockList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clockList;
	}
}
