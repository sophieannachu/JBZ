package com.friend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendJDBCDAO implements FriendDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jbzke";
	String passwd = "andy1226";
	
	private static final String INSERT_STMT=
			"INSERT　INTO friend (memno,frino,adddate) VALUES (?,?,?)";
	private static final String UPDATE_STMT=
			"UPDATE friend set status = ?,adddate=? where memno = ? frino = ?";
	private static final String DELETE_STMT=
			"DELETE　friend where memno=? and frino=?";
	private static final String GET_ONE_STMT = 
			"SELECT memno,frino,adddate,status from friend where memno=? and frino=?";
	private static final String GET_ALL_STMT=
			"SELECT  memno,frino,adddate,status from friend where memno=? and status=?order by adddate desc";
	
	
	@Override
	public void insert(FriendVO friendVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, friendVO.getMemno());
			pstmt.setInt(2, friendVO.getFrino());
			pstmt.setDate(3, friendVO.getAddDate());

			
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
	public void update(FriendVO friendVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, friendVO.getStatus());
			pstmt.setDate(2, friendVO.getAddDate());
			pstmt.setInt(3, friendVO.getMemno());
			pstmt.setInt(4, friendVO.getFrino());

			
			pstmt.executeUpdate();
			
			
			
		}catch (SQLException |ClassNotFoundException se) {
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
	public void delete(Integer memno, Integer frino) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, frino);
			
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
	public FriendVO findByPrimaryKey(Integer memno, Integer frino) {
		// TODO Auto-generated method stub
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, frino);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				friendVO=new FriendVO();
				friendVO.setMemno(rs.getInt("memno"));
				friendVO.setFrino(rs.getInt("frino"));
				friendVO.setAddDate(rs.getDate("adddate"));
				friendVO.setStatus(rs.getString("status"));
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
		return friendVO;
	}

	@Override
	public List<FriendVO> getAllbymemno(Integer memno,String status) {
		// TODO Auto-generated method stub
		FriendVO friendVO=null;
		List<FriendVO> list=new ArrayList<FriendVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setString(2, status);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				friendVO=new FriendVO();
				friendVO.setMemno(rs.getInt("memno"));
				friendVO.setFrino(rs.getInt("frino"));
				friendVO.setAddDate(rs.getDate("adddate"));
				friendVO.setStatus(rs.getString("status"));
				list.add(friendVO);
				
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
	public List<FriendVO> getAllbyfrino(Integer frino, String status) {
		// TODO Auto-generated method stub
		FriendVO friendVO=null;
		List<FriendVO> list=new ArrayList<FriendVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setInt(1, frino);
			pstmt.setString(2, status);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				friendVO=new FriendVO();
				friendVO.setMemno(rs.getInt("memno"));
				friendVO.setFrino(rs.getInt("frino"));
				friendVO.setAddDate(rs.getDate("adddate"));
				friendVO.setStatus(rs.getString("status"));
				list.add(friendVO);
				
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
//		FriendJDBCDAO dao = new FriendJDBCDAO();
//
//		 //新增OK
//		 FriendVO friendVO1 = new FriendVO();
//		 friendVO1.setMemno(10);
//		 friendVO1.setFrino(3);
//		 friendVO1.setAddDate(java.sql.Date.valueOf("2016-08-15"));		 
// 		 dao.insert(friendVO1);
//		 		
//		 //刪除ok
//		 dao.delete(4,2);
//		
//		// 查詢主鍵ok
//		FriendVO friendVO2 = dao.findByPrimaryKey(1, 3);
//		
//		System.out.print(friendVO2.getMemno() + ", ");
//		System.out.print(friendVO2.getFrino() + ", ");
//		System.out.print(friendVO2.getAddDate() + ", ");
//		System.out.println();
//		
//		
//		
//		// 查詢某會員全部ok
//		List<FriendVO> list = dao.getAllbymemno(3);
//		for (FriendVO aFriend : list) {
//			System.out.print(aFriend.getMemno() + ", ");
//			System.out.print(aFriend.getFrino() + ", ");
//			System.out.print(aFriend.getAddDate() + ", ");
//			System.out.println();
//		}
//	}
}
