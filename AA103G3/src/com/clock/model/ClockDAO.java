package com.clock.model;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Timestamp;

public class ClockDAO implements ClockDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可\
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO clock (clockno,clocktime,clockinfo,clocksche,clockring,clocktype,memno) VALUES (clock_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT clockno,clocktime,clockinfo,clocksche,clockring,clocktype,memno FROM clock order by clocktime";
	private static final String GET_ONE_STMT = 
		"SELECT clockno,clocktime,clockinfo,clocksche,clockring,clocktype,memno FROM clock where clockno = ?";
	private static final String DELETE = 
		"DELETE FROM clock where clockno = ?";
	private static final String UPDATE = 
		"UPDATE clock set clocktime=?, clockinfo=?, clocksche=?, clockring=?, clocktype=?, memno=? where clockno = ?";

	@Override
	public void insert(ClockVO clockVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, clockVO.getClocktime());
			pstmt.setString(2, clockVO.getClockinfo());
			pstmt.setString(3, clockVO.getClocksche());
			pstmt.setString(4, clockVO.getClockring());
			pstmt.setInt(5, clockVO.getClocktype());
			pstmt.setInt(6, clockVO.getMemno());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ClockVO clockVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, clockVO.getClocktime());
			pstmt.setString(2, clockVO.getClockinfo());
			pstmt.setString(3, clockVO.getClocksche());
			pstmt.setString(4, clockVO.getClockring());
			pstmt.setDouble(5, clockVO.getClocktype());
			pstmt.setInt(6, clockVO.getMemno());
			pstmt.setInt(7, clockVO.getClockno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer clockno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clockno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ClockVO findByPrimaryKey(Integer clockno) {

		ClockVO clockVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clockno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clockVO = new ClockVO();
				clockVO.setClockno(rs.getInt("clockno"));
				clockVO.setClocktime(rs.getTimestamp("clocktime"));
				clockVO.setClockinfo(rs.getString("clockinfo"));
				clockVO.setClocksche(rs.getString("clocksche"));
				clockVO.setClockring(rs.getString("clockring"));
				clockVO.setClocktype(rs.getInt("clocktype"));
				clockVO.setMemno(rs.getInt("memno"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return clockVO;
	}

	@Override
	public List<ClockVO> getAll() {
		List<ClockVO> list = new ArrayList<ClockVO>();
		ClockVO clockVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clockVO = new ClockVO();
				clockVO.setClockno(rs.getInt("clockno"));
				
				clockVO.setClocktime(rs.getTimestamp("clocktime"));
				clockVO.setClockinfo(rs.getString("clockinfo"));
				clockVO.setClocksche(rs.getString("clocksche"));
				clockVO.setClockring(rs.getString("clockring"));
				clockVO.setClocktype(rs.getInt("clocktype"));
				clockVO.setMemno(rs.getInt("memno"));
				list.add(clockVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
