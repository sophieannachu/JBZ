package com.ach.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.memach.model.MemAchVO;
import com.sleepcheck.model.SleepCheckJDBCDAO;
import com.sleepcheck.model.SleepCheckVO;

public class AchJDBCDAO implements AchDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jbzke";
	String passwd = "andy1226";
	
	private static final String INSERT_STMT=
			"INSERT　INTO ach (achno,aname,achcond) VALUES (achno_seq.NEXTVAL,?,?)";
	private static final String UPDATE_STMT=
			"UPDATE　ach SET aname=?,achcond=? where achno=?";
	private static final String DELETE_STMT=
			"DELETE　ach where achno=?";
	private static final String GET_ONE_STMT = 
			"SELECT achno,aname,achcond from ach where achno=?";
	private static final String GET_ALL_STMT=
			"SELECT achno,aname,achcond from ach order by achno";
	private static final String GET_MEMS_BYACHNO_STMT=
			"SELECT memno,achno,comDate from memach where achno=? order by memno";
	
	
	@Override
	public void insert(AchVO achVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, achVO.getaName());
			pstmt.setInt(2, achVO.getAchCond());

			
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
	public void update(AchVO achVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, achVO.getaName());
			pstmt.setInt(2, achVO.getAchCond());
			pstmt.setInt(3, achVO.getAchno());
			
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
	public void delete(Integer achno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, achno);
			
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
	public AchVO findByPrimaryKey(Integer achno) {
		// TODO Auto-generated method stub
		AchVO achVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, achno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				achVO=new AchVO();
				achVO.setAchno(rs.getInt("achno"));
				achVO.setaName(rs.getString("aname"));
				achVO.setAchCond(rs.getInt("achcond"));

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
		return achVO;
	}

	@Override
	public List<AchVO> getAll() {
		// TODO Auto-generated method stub
		AchVO achVO=null;
		List<AchVO> list=new ArrayList<AchVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				achVO=new AchVO();
				achVO.setAchno(rs.getInt("achno"));
				achVO.setaName(rs.getString("aname"));
				achVO.setAchCond(rs.getInt("achcond"));
				list.add(achVO);				
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
	public Set<MemAchVO> getMemsByAchno(Integer achno) {
		// TODO Auto-generated method stub
		Set<MemAchVO> set = new LinkedHashSet<MemAchVO>();
		MemAchVO memAchVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_MEMS_BYACHNO_STMT);
			pstmt.setInt(1, achno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comDate"));
				set.add(memAchVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
//	For 關鍵字查詢
	public Set<MemAchVO> getMemsByAchName(String aName) {
		// TODO Auto-generated method stub
		Set<MemAchVO> set = new LinkedHashSet<MemAchVO>();
		MemAchVO memAchVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			String query = "SELECT memno,achno,comDate from memach where achno in "
					+ "(select achno from ach where aname like '%"
					+ aName
					+ "%') order by memno";			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comDate"));
				set.add(memAchVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException |ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
	public static void main(String[] args) {

		AchJDBCDAO dao = new AchJDBCDAO();

		 //新增OK
		 java.util.Date date=new java.util.Date();
		 
		 AchVO achVO = new AchVO();
		 achVO.setaName("累積揪團數");
		 achVO.setAchCond(10);
 		 dao.insert(achVO);

		 //修改ok
		 AchVO achVO1 = new AchVO();
		 achVO1.setaName("累積揪團數");
		 achVO1.setAchCond(15);
		 achVO1.setAchno(110);
		 dao.update(achVO1);

				

		 //刪除ok
		 dao.delete(20);
		
		 
		// 查詢主鍵ok 
		 AchVO achVO2 = dao.findByPrimaryKey(30);
		
		System.out.print(achVO2.getAchno() + ",");
		System.out.print(achVO2.getaName() + ",");
		System.out.print(achVO2.getAchCond() + ",");
		System.out.println();
		System.out.println("---------------------");				

		
		// 查詢全部ok
		List<AchVO> list = dao.getAll();
		for (AchVO aAch : list) {
			System.out.print(aAch.getAchno() + ",");
			System.out.print(aAch.getaName() + ",");
			System.out.print(aAch.getAchCond() + ",");
			System.out.println();
		}
	
	}
}
