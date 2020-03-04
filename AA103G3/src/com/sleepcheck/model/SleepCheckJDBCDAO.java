package com.sleepcheck.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SleepCheckJDBCDAO implements SleepCheckDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jbzke";
	String passwd = "andy1226";
	
	private static final String INSERT_STMT=
			"INSERT　INTO sleepcheck (sleepCheckno,bedTime,wakeTime,sleepTime,memno) VALUES (sleepcheckno_seq.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT=
			"UPDATE　sleepcheck SET bedTime=?,wakeTime=?, sleepTime=? where sleepCheckno=?";
	private static final String DELETE_STMT=
			"DELETE　sleepcheck where sleepCheckno=?";
	private static final String GET_ONE_STMT = 
			"SELECT sleepCheckno,bedTime,wakeTime,sleepTime,memno from sleepcheck where sleepCheckno=?";
	private static final String GET_ALL_STMT=
			"SELECT sleepCheckno,bedTime,wakeTime,sleepTime,memno from sleepcheck order by wakeTime desc";
	private static final String GET_ALLBYMEM_STMT=
			"SELECT sleepCheckno,bedTime,wakeTime,sleepTime,memno from sleepcheck where memno=? order by wakeTime desc";
	
	@Override
	public void insert(SleepCheckVO sleepCheckVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setTimestamp(1, sleepCheckVO.getBedTime());
			pstmt.setTimestamp(2, sleepCheckVO.getWakeTime());
			pstmt.setInt(3, sleepCheckVO.getSleepTime());
			pstmt.setInt(4, sleepCheckVO.getMemno());
			
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
	public void update(SleepCheckVO sleepCheckVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE_STMT);
			
			pstmt.setTimestamp(1, sleepCheckVO.getBedTime());
			pstmt.setTimestamp(2, sleepCheckVO.getWakeTime());
			pstmt.setInt(3, sleepCheckVO.getSleepTime());
			pstmt.setInt(4, sleepCheckVO.getSleepCheckno());
			
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
	public void delete(Integer sleepCheckno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, sleepCheckno);
			
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
	public SleepCheckVO findByPrimaryKey(Integer sleepCheckno) {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sleepCheckno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				sleepCheckVO=new SleepCheckVO();
				sleepCheckVO.setSleepCheckno(rs.getInt("sleepcheckno"));
				sleepCheckVO.setBedTime(rs.getTimestamp("bedtime"));
				sleepCheckVO.setWakeTime(rs.getTimestamp("waketime"));
				sleepCheckVO.setSleepTime(rs.getInt("sleepTime"));
				sleepCheckVO.setMemno(rs.getInt("memno"));
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
		return sleepCheckVO;
	}

	@Override
	public List<SleepCheckVO> getAll() {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO=null;
		List<SleepCheckVO> list=new ArrayList<SleepCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				sleepCheckVO=new SleepCheckVO();
				sleepCheckVO.setSleepCheckno(rs.getInt("sleepcheckno"));
				sleepCheckVO.setBedTime(rs.getTimestamp("bedtime"));
				sleepCheckVO.setWakeTime(rs.getTimestamp("waketime"));
				sleepCheckVO.setSleepTime(rs.getInt("sleepTime"));
				sleepCheckVO.setMemno(rs.getInt("memno"));
				list.add(sleepCheckVO);				
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
	public List<SleepCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO=null;
		List<SleepCheckVO> list=new ArrayList<SleepCheckVO>();
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
				sleepCheckVO=new SleepCheckVO();
				sleepCheckVO.setSleepCheckno(rs.getInt("sleepcheckno"));
				sleepCheckVO.setBedTime(rs.getTimestamp("bedtime"));
				sleepCheckVO.setWakeTime(rs.getTimestamp("waketime"));
				sleepCheckVO.setSleepTime(rs.getInt("sleepTime"));
				sleepCheckVO.setMemno(rs.getInt("memno"));
				list.add(sleepCheckVO);				
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
//		SleepCheckJDBCDAO dao = new SleepCheckJDBCDAO();
//
//		 //新增OK
//		 
//		 SleepCheckVO sleepCheckVO = new SleepCheckVO();
//		 sleepCheckVO.setBedTime(java.sql.Timestamp.valueOf("2016-8-16 20:53:00"));
//		 sleepCheckVO.setWakeTime(java.sql.Timestamp.valueOf("2016-8-17 08:15:00"));
//		 sleepCheckVO.setSleepTime(650);
//		 sleepCheckVO.setMemno(10);
// 		 dao.insert(sleepCheckVO);
//
//		 //修改ok
// 		 SleepCheckVO sleepCheckVO1 = new SleepCheckVO();
//		 sleepCheckVO1.setBedTime(java.sql.Timestamp.valueOf("2016-8-15 20:53:00"));
//		 sleepCheckVO1.setWakeTime(java.sql.Timestamp.valueOf("2016-8-16 08:15:00"));
//		 sleepCheckVO1.setSleepTime(665);
//		 sleepCheckVO1.setSleepCheckno(1);
//		 dao.update(sleepCheckVO1);
//
//				
//
//		 //刪除ok
//		 dao.delete(2);
//		
//		 
//		// 查詢主鍵ok 
//		SleepCheckVO sleepCheckVO2 = dao.findByPrimaryKey(3);
//		
//		System.out.print(sleepCheckVO2.getSleepCheckno() + ",");
//		System.out.print(sleepCheckVO2.getBedTime() + ",");
//		System.out.print(sleepCheckVO2.getWakeTime() + ",");
//		System.out.print(sleepCheckVO2.getSleepTime() + ",");
//		System.out.print(sleepCheckVO2.getMemno() + ",");
//		System.out.println();
//		System.out.println("---------------------");				
//
//		
//		// 查詢全部ok
//		List<SleepCheckVO> list = dao.getAll();
//		for (SleepCheckVO aSleepCheck : list) {
//			System.out.print(aSleepCheck.getSleepCheckno() + ",");
//			System.out.print(aSleepCheck.getBedTime() + ",");
//			System.out.print(aSleepCheck.getWakeTime() + ",");
//			System.out.print(aSleepCheck.getSleepTime() + ",");
//			System.out.print(aSleepCheck.getMemno() + ",");
//			System.out.println();
//		}
//	
//	}

}
