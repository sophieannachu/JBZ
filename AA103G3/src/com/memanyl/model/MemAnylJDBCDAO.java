package com.memanyl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemAnylJDBCDAO implements MemAnylDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jbzke";
	String passwd = "andy1226";
	
	private static final String INSERT_STMT=
			"INSERT　INTO memanyl (anylno,anylDate,stepAvg,calTakeAvg,exeTimeAvg,calBurnAvg) VALUES (anylno_seq.NEXTVAL,?,?,?,?,?)";
	private static final String DELETE_STMT=
			"DELETE　memanyl where anylno=?";
	private static final String GET_ONE_STMT = 
			"SELECT anylno,to_char(anylDate,'yyyy-mm-dd') anylDate,stepAvg,calTakeAvg,exeTimeAvg,calBurnAvg from memanyl where anylno=?";
	private static final String GET_ALL_STMT=
			"SELECT anylno,to_char(anylDate,'yyyy-mm-dd') anylDate,stepAvg,calTakeAvg,exeTimeAvg,calBurnAvg from memanyl order by anyldate desc";
	@Override
	public void insert(MemAnylVO memAnylVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1, memAnylVO.getAnylDate());
			pstmt.setInt(2, memAnylVO.getStepAvg());
			pstmt.setInt(3, memAnylVO.getCalTakeAvg());
			pstmt.setInt(4, memAnylVO.getExeTimeAvg());
			pstmt.setInt(5, memAnylVO.getCalBurnAvg());
			
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
//	@Override
//	public void update(MemAnylVO memAnylVO) {
//		// TODO Auto-generated method stub
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		
//
//		try {
//			Class.forName(driver);
//			con=DriverManager.getConnection(url,userid,passwd);
//			pstmt=con.prepareStatement(UPDATE_STMT);
//			
//			pstmt.setInt(1, memAnylVO.getStepAvg());
//			pstmt.setInt(2, memAnylVO.getCalTakeAvg());
//			pstmt.setInt(3, memAnylVO.getExeTimeAvg());
//			pstmt.setInt(4, memAnylVO.getCalBurnAvg());
//			pstmt.setInt(5, memAnylVO.getAnylno());
//			
//			
//			pstmt.executeUpdate();
//				
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//		} catch (SQLException se) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		}finally{
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}	
//		
//	}
	@Override
	public void delete(Integer memAnylno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, memAnylno);
			
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
	public MemAnylVO findByPrimaryKey(Integer memAnylno) {
		// TODO Auto-generated method stub
		MemAnylVO memAnylVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memAnylno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memAnylVO=new MemAnylVO();
				memAnylVO.setAnylno(rs.getInt("anylno"));
				memAnylVO.setAnylDate(rs.getDate("anyldate"));
				memAnylVO.setStepAvg(rs.getInt("stepavg"));
				memAnylVO.setCalTakeAvg(rs.getInt("caltakeavg"));
				memAnylVO.setExeTimeAvg(rs.getInt("exetimeavg"));
				memAnylVO.setCalBurnAvg(rs.getInt("calburnavg"));
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
		return memAnylVO;
	}
	@Override
	public List<MemAnylVO> getAll() {
		// TODO Auto-generated method stub
		MemAnylVO memAnylVO=null;
		List<MemAnylVO> list=new ArrayList<MemAnylVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				memAnylVO=new MemAnylVO();
				memAnylVO.setAnylno(rs.getInt("anylno"));
				memAnylVO.setAnylDate(rs.getDate("anyldate"));
				memAnylVO.setStepAvg(rs.getInt("stepavg"));
				memAnylVO.setCalTakeAvg(rs.getInt("caltakeavg"));
				memAnylVO.setExeTimeAvg(rs.getInt("exetimeavg"));
				memAnylVO.setCalBurnAvg(rs.getInt("calburnavg"));
				list.add(memAnylVO);				
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
//		MemAnylJDBCDAO dao = new MemAnylJDBCDAO();
//
//		 //新增OK
//		 
//		 MemAnylVO memAnylVO=new MemAnylVO();
//		 memAnylVO.setAnylDate(java.sql.Date.valueOf("2016-08-15"));
//		 memAnylVO.setStepAvg(7521);
//		 memAnylVO.setCalTakeAvg(1520);
//		 memAnylVO.setExeTimeAvg(2100);
//		 memAnylVO.setCalBurnAvg(953);
// 		 dao.insert(memAnylVO);
//
//		 //修改ok
//		 MemAnylVO memAnylVO1=new MemAnylVO();
//		 memAnylVO1.setStepAvg(6621);
//		 memAnylVO1.setCalTakeAvg(1420);
//		 memAnylVO1.setExeTimeAvg(1900);
//		 memAnylVO1.setCalBurnAvg(1053);
//		 memAnylVO1.setAnylno(5);
//		 dao.update(memAnylVO1);
//
//			
//		 //刪除ok
//		 dao.delete(4);
//		
//		 
//		// 查詢主鍵ok
//		 MemAnylVO memAnylVO2 = dao.findByPrimaryKey(3);
//		
//		System.out.print(memAnylVO2.getAnylno() + ",");
//		System.out.print(memAnylVO2.getAnylDate() + ",");
//		System.out.print(memAnylVO2.getStepAvg() + ",步");
//		System.out.print(memAnylVO2.getCalTakeAvg() + ",Kcal");
//		System.out.print(memAnylVO2.getExeTimeAvg() + ",分");
//		System.out.print(memAnylVO2.getCalBurnAvg() + "Kcal,");
//		System.out.println();
//		System.out.println("---------------------");				
//
//		
//		// 查詢全部ok
//		List<MemAnylVO> list = dao.getAll();
//		for (MemAnylVO aMemAnyl : list) {
//			System.out.print(aMemAnyl.getAnylno() + ",");
//			System.out.print(aMemAnyl.getAnylDate() + ",");
//			System.out.print(aMemAnyl.getStepAvg() + ",步");
//			System.out.print(aMemAnyl.getCalTakeAvg() + ",Kcal");
//			System.out.print(aMemAnyl.getExeTimeAvg() + ",分");
//			System.out.print(aMemAnyl.getCalBurnAvg() + "Kcal,");
//			System.out.println();
//		}
//	
//	}

}
