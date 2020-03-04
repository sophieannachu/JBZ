package iii.jbz.mobile.healthplan;

import idv.ron.server.main.Common;
import java.sql.Timestamp;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthPlanDaoOracleImpl implements HealthPlanDao {

	public HealthPlanDaoOracleImpl() 
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
	public int insert(HealthPlanVO healthPlanVO) {
		int count = 0;
		String sql = "INSERT INTO hplist (hpno, memno, hpname, hpstdate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg, hpeddate) VALUES (hplist_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, healthPlanVO.getMemno());
			ps.setString(2, healthPlanVO.getHpname());
			ps.setTimestamp(3, healthPlanVO.getHpstdate());
			ps.setInt(4, healthPlanVO.getHpstep());
			ps.setInt(5, healthPlanVO.getHpexer());
			ps.setInt(6, healthPlanVO.getHpbmi());
			ps.setDouble(7, healthPlanVO.getHpwaist());
			ps.setInt(8, healthPlanVO.getHpcal());
			ps.setInt(9, healthPlanVO.getHpbp());
			ps.setInt(10, healthPlanVO.getHpbg());
			ps.setTimestamp(11, healthPlanVO.getHpeddate());
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
	public int update(HealthPlanVO healthPlanVO) {
		int count = 0;
//		String sql = "UPDATE CLOCK SET clockno = ?, clocktime = ?, clockinfo = ?, clocksche = ?, clockring = ?, clocktype = ? WHERE id = ?";
//		Connection connection = null;
//		PreparedStatement ps = null;
//		try {
//			connection = DriverManager.getConnection(Common.URL, Common.USER,
//					Common.PASSWORD);
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, clock.getClockNo());
//			ps.setTimestamp(2, clock.getClockTime());
//			ps.setString(3, clock.getClockInfo());
//			ps.setString(4, clock.getClockSche());
//			ps.setString(5, clock.getClockring());
//			ps.setInt(6, clock.getClocktype());
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					// When a Statement object is closed,
//					// its current ResultSet object is also closed
//					ps.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
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
	public HealthPlanVO findById(int memno) 
	{
//		String sql = "SELECT clockno, clocktime, clockinfo, clocksche, clockring, clocktype FROM CLOCK WHERE memno = ?";
//		Connection conn = null;
//		PreparedStatement ps = null;
//		HealthPlan clock = null;
//		try {
//			conn = DriverManager.getConnection(Common.URL, Common.USER,
//					Common.PASSWORD);
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, memno);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) 
//			{
//				int clockno = rs.getInt(2);
//				Timestamp clocktime = rs.getTimestamp(3);
//				String clockinfo = rs.getString(4);
//				String clocksche = rs.getString(5);
//				String clockring = rs.getString(6);
//				int clocktype = rs.getInt(7);
//				clock = new HealthPlan(memno, clockno, clocktime, clockinfo, clocksche, clockring, clocktype);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return null;
	}

	@Override
	public List<HealthPlanVO> getAllByMemno(int memno) {
		String sql = "SELECT hpno, memno, hpname, to_char(hpstdate,'yyyy-mm-dd hh:mm:ss') hpstdate, to_char(hpeddate,'yyyy-mm-dd hh:mm:ss') hpeddate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg "
				+ "FROM HPLIST WHERE memno = ? and trunc(hpeddate, 'DD') >= TRUNC(SYSDATE, 'DD') ORDER BY hpno";
		Connection connection = null;
		PreparedStatement ps = null;
		List<HealthPlanVO> healthplanList = new ArrayList<HealthPlanVO>();
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int hpno = rs.getInt(1);
				int memno2 = rs.getInt(2);
				String hpname = rs.getString(3);
				Timestamp hpstdate = rs.getTimestamp(4);
				Timestamp hpeddate = rs.getTimestamp(5);
				int hpstep = rs.getInt(6);
				int hpexer = rs.getInt(7);
				int hpbmi = rs.getInt(8);
				int hpwaist = rs.getInt(9);
				int hpcal = rs.getInt(10);
				int hpbp = rs.getInt(11);
				int hpbg = rs.getInt(12);
				HealthPlanVO healthplan = new HealthPlanVO(hpno, memno, hpname, hpstdate, hpeddate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg);
				healthplanList.add(healthplan);
			}
			return healthplanList;
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
		return healthplanList;
	}
}
