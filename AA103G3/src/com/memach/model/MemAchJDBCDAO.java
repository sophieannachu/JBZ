package com.memach.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.friend.model.FriendJDBCDAO;
import com.friend.model.FriendVO;

public class MemAchJDBCDAO implements MemAchDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jbzke";
	String passwd = "andy1226";
	
	private static final String INSERT_STMT=
			"INSERT　INTO memach (memno,achno,comdate) VALUES (?,?,?)";
	private static final String DELETE_STMT=
			"DELETE　memach where memno=? and achno=?";
	private static final String GET_ONE_STMT = 
			"SELECT memno,achno,comdate from memach where memno=? and achno=?";
	private static final String GET_ALLBYMEM_STMT=
			"SELECT memno,achno,comdate from memach where memno=? order by achno";
	private static final String GET_ALL_STMT=
			"SELECT memno,achno,comdate from memach order by memno";
	
	@Override
	public void insert(MemAchVO memAchVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memAchVO.getMemno());
			pstmt.setInt(2, memAchVO.getAchno());
			pstmt.setDate(3, memAchVO.getComDate());

			
			pstmt.executeUpdate();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public void delete(Integer memno, Integer achno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, achno);
			
			pstmt.executeUpdate();
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public MemAchVO findByPrimaryKey(Integer memno, Integer achno) {
		// TODO Auto-generated method stub
		MemAchVO memAchVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, achno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comdate"));
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
		return memAchVO;
	}

	@Override
	public List<MemAchVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		MemAchVO memAchVO=null;
		List<MemAchVO> list=new ArrayList<MemAchVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALLBYMEM_STMT);
			
			pstmt.setInt(1, memno);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comdate"));
				list.add(memAchVO);
				
			}
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	public List<MemAchVO> getAll() {
		// TODO Auto-generated method stub
		MemAchVO memAchVO=null;
		List<MemAchVO> list=new ArrayList<MemAchVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comdate"));
				list.add(memAchVO);
				
			}
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	
//	public static void main(String[] args) {
//
//		MemAchJDBCDAO dao = new MemAchJDBCDAO();
//
////		 //新增OK
//		 MemAchVO memAchVO = new MemAchVO();
//		 memAchVO.setMemno(10);
//		 memAchVO.setAchno(3);
//		 memAchVO.setComDate(java.sql.Date.valueOf("2016-08-15"));		 
// 		 dao.insert(memAchVO);
////		 		
////		 //刪除ok
//		 dao.delete(2,60);
////		
////		// 查詢主鍵ok
//		 MemAchVO memAchVO1 = dao.findByPrimaryKey(3, 60);
//		
//		System.out.print(memAchVO1.getMemno() + ", ");
//		System.out.print(memAchVO1.getAchno() + ", ");
//		System.out.print(memAchVO1.getComDate() + ", ");
//		System.out.println();
////		
////		
////		
////		// 查詢某會員全部ok
//		List<MemAchVO> list = dao.getAllbymemno(3);
//		for (MemAchVO aFriend : list) {
//			System.out.print(aFriend.getMemno() + ", ");
//			System.out.print(aFriend.getAchno() + ", ");
//			System.out.print(aFriend.getComDate() + ", ");
//			System.out.println();
//		}
//	}
}
