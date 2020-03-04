package com.foodlist.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






public class FoodlistJDBCDAO implements Foodlist_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "David";
	String passwd = "lucky006";
	
	    private static final String INSERT_STMT = 
			"INSERT INTO foodlist (fdrecno,fddesc,fddate,fdqua,memno,fd_no) VALUES (foodlist_seq.NEXTVAL, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT fdrecno,fddesc,to_char(fddate,'yyyy-mm-dd') fddate,fdqua,memno,fd_no FROM foodlist order by fdrecno";
		private static final String GET_ONE_STMT = 
			"SELECT fdrecno,fddesc,to_char(fddate,'yyyy-mm-dd') fddate,fdqua,memno,fd_no FROM foodlist where fdrecno = ?";
		private static final String DELETE = 
			"DELETE FROM foodlist where fdrecno = ?";
		private static final String UPDATE = 
			"UPDATE foodlist set fddesc=?, fddate=?,fdqua=?, memno=?, fd_no=? where fdrecno = ?";
		private static final String GET_ONEDATEANDMEMNO_STMT =
			"SELECT fdrecno,fddesc,to_char(fddate,'yyyy-mm-dd') fddate,fdqua,memno,fd_no FROM foodlist where fddate = ? and memno=?";
		private static final String GET_ALLDATEBYMEMNO_STMT =
				"SELECT distinct to_char(fddate,'yyyy-mm-dd') fddate FROM foodlist where memno=?";
	
	
	@Override
	public void insert(FoodlistVO foodlistVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,foodlistVO.getFddesc());
			pstmt.setDate(2,foodlistVO.getFddate());
			pstmt.setInt(3,foodlistVO.getFdqua());
			pstmt.setInt(4,foodlistVO.getMemno());
			pstmt.setInt(5,foodlistVO.getFd_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(FoodlistVO foodlistVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodlistVO.getFddesc());
			pstmt.setDate(2, foodlistVO.getFddate());
			pstmt.setInt(3, foodlistVO.getFdqua());
			pstmt.setInt(4, foodlistVO.getMemno());
			pstmt.setInt(5, foodlistVO.getFd_no());
			pstmt.setInt(6, foodlistVO.getFdrecno());
			
			
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer fdrecno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, fdrecno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public FoodlistVO findByPrimaryKey(Integer fdrenco) {
		// TODO Auto-generated method stub
		FoodlistVO foodlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fdrenco);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				foodlistVO = new FoodlistVO();
				foodlistVO.setFdrecno(rs.getInt("fdrecno"));
				foodlistVO.setFddesc(rs.getString("fddesc"));
				foodlistVO.setFddate(rs.getDate("fddate"));
				foodlistVO.setFdqua(rs.getInt("fdqua"));
				foodlistVO.setMemno(rs.getInt("memno"));
				foodlistVO.setFd_no(rs.getInt("fd_no"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return foodlistVO;
		
	}

	@Override
	public List<FoodlistVO> getAll() {
		// TODO Auto-generated method stub
		List<FoodlistVO> list = new ArrayList<FoodlistVO>();
		FoodlistVO foodlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				foodlistVO = new FoodlistVO();
				foodlistVO.setFdrecno(rs.getInt("fdrecno"));
				foodlistVO.setFddesc(rs.getString("fddesc"));
				foodlistVO.setFddate(rs.getDate("fddate"));
				foodlistVO.setFdqua(rs.getInt("fdqua"));
				foodlistVO.setMemno(rs.getInt("memno"));
				foodlistVO.setFd_no(rs.getInt("fd_no"));
				list.add(foodlistVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	@Override
	public List<FoodlistVO> findByDateAndMemno(Date fddate,Integer memno) {
		
		List<FoodlistVO> list1 = new ArrayList<FoodlistVO>();
		
		FoodlistVO foodlistVO5 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONEDATEANDMEMNO_STMT);

			pstmt.setDate(1, fddate);
			pstmt.setInt(2, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				foodlistVO5 = new FoodlistVO();
				foodlistVO5.setFdrecno(rs.getInt("fdrecno"));
				foodlistVO5.setFddesc(rs.getString("fddesc"));
				foodlistVO5.setFddate(rs.getDate("fddate"));
				foodlistVO5.setFdqua(rs.getInt("fdqua"));
				foodlistVO5.setMemno(rs.getInt("memno"));
				foodlistVO5.setFd_no(rs.getInt("fd_no"));
				list1.add(foodlistVO5);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list1;
		
	}
	
	@Override
	public List<FoodlistVO> findDateByMemno(Integer memno) {
		// TODO Auto-generated method stub
		List<FoodlistVO> list1 = new ArrayList<FoodlistVO>();
		
		FoodlistVO foodlistVO5 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALLDATEBYMEMNO_STMT);
			
			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				foodlistVO5 = new FoodlistVO();
				foodlistVO5.setFddate(rs.getDate("fddate"));
				list1.add(foodlistVO5);
			}

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
		return list1;
	}
	
	public static void main(String[] args) {

		FoodlistJDBCDAO dao = new FoodlistJDBCDAO();

		// 新增
//		FoodlistVO foodlistVO1 = new FoodlistVO();
//		
//		foodlistVO1.setFddesc("午餐");
//		foodlistVO1.setFddate(java.sql.Date.valueOf("2016-02-02"));
//		foodlistVO1.setFdqua(2);
//		foodlistVO1.setMemno(2);
//		foodlistVO1.setFd_no(6);
//		dao.insert(foodlistVO1);

		// 修改
//		FoodlistVO foodlistVO2 = new FoodlistVO();
//		foodlistVO2.setFdrecno(1);
//		foodlistVO2.setFddesc("晚餐");
//		foodlistVO2.setFddate(java.sql.Date.valueOf("2016-01-01"));
//		foodlistVO2.setFdqua(1);
//		foodlistVO2.setMemno(1);
//		foodlistVO2.setFd_no(1);
//		dao.update(foodlistVO2);

//		// 刪除
//		dao.delete(7);
//
//		// 查詢
//		FoodlistVO foodlistVO3 = dao.findByPrimaryKey(3);
//		System.out.print(foodlistVO3.getFdrecno() + ",");
//		System.out.print(foodlistVO3.getFddesc() + ",");
//		System.out.print(foodlistVO3.getFddate() + ",");
//		System.out.print(foodlistVO3.getFdqua() + ",");
//		System.out.print(foodlistVO3.getMemno() + ",");
//		System.out.println(foodlistVO3.getFd_no());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<FoodlistVO> list = dao.getAll();
//		for (FoodlistVO afoodlist : list) {
//			System.out.print(afoodlist.getFdrecno() + ",");
//			System.out.print(afoodlist.getFddesc() + ",");
//			System.out.print(afoodlist.getFddate() + ",");
//			System.out.print(afoodlist.getFdqua() + ",");
//			System.out.print(afoodlist.getMemno() + ",");
//			System.out.print(afoodlist.getFd_no());
//			System.out.println();
//		}
		// 查詢日期及會員
//		List<FoodlistVO> list1 = dao.findByDateAndMemno(java.sql.Date.valueOf("2016-08-17"),1);
////		FoodlistVO foodlistVO4 = dao.findByDate(java.sql.Date.valueOf("2016-08-15"));
//		for (FoodlistVO afoodlist1 : list1) {
//		System.out.print(afoodlist1.getFdrecno() + ",");
//		System.out.print(afoodlist1.getFddesc() + ",");
//		System.out.print(afoodlist1.getFddate() + ",");
//		System.out.print(afoodlist1.getFdqua() + ",");
//		System.out.print(afoodlist1.getMemno() + ",");
//		System.out.println(afoodlist1.getFd_no());
//		System.out.println("---------------------");
//		}
	}
}
