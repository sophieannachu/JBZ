package com.memanyl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemAnylDAO implements MemAnylDAO_interface{

	private static DataSource ds = null;
	static{
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT¡@INTO memanyl (anylno,anylDate,stepAvg,calTakeAvg,exeTimeAvg,calBurnAvg) VALUES (anylno_seq.NEXTVAL,?,?,?,?,?)";
//	private static final String UPDATE_STMT=
//			"UPDATE¡@memanyl SET stepAvg=?,calTakeAvg=?,exeTimeAvg=?,calBurnAvg=? where anylno=?";
	private static final String DELETE_STMT=
			"DELETE¡@memanyl where anylno=?";
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
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1, memAnylVO.getAnylDate());
			pstmt.setInt(2, memAnylVO.getStepAvg());
			pstmt.setInt(3, memAnylVO.getCalTakeAvg());
			pstmt.setInt(4, memAnylVO.getExeTimeAvg());
			pstmt.setInt(5, memAnylVO.getCalBurnAvg());
			
			pstmt.executeUpdate();
			
			
			
		}catch (SQLException se) {
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
//			con=ds.getConnection();
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
//		}catch (SQLException se) {
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
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, memAnylno);
			
			pstmt.executeUpdate();
				
			
		}catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memAnylno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ¤]ºÙ¬° Domain objects
				memAnylVO=new MemAnylVO();
				memAnylVO.setAnylno(rs.getInt("anylno"));
				memAnylVO.setAnylDate(rs.getDate("anyldate"));
				memAnylVO.setStepAvg(rs.getInt("stepavg"));
				memAnylVO.setCalTakeAvg(rs.getInt("caltakeavg"));
				memAnylVO.setExeTimeAvg(rs.getInt("exetimeavg"));
				memAnylVO.setCalBurnAvg(rs.getInt("calburnavg"));
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
			con=ds.getConnection();
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
				
			
		}catch (SQLException se) {
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
}
