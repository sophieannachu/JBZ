package com.memach.model;

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

import com.friend.model.FriendJDBCDAO;
import com.friend.model.FriendVO;

public class MemAchDAO implements MemAchDAO_interface{

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
			"INSERT¡@INTO memach (memno,achno,comdate) VALUES (?,?,?)";
	private static final String DELETE_STMT=
			"DELETE¡@memach where memno=? and achno=?";
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
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memAchVO.getMemno());
			pstmt.setInt(2, memAchVO.getAchno());
			pstmt.setDate(3, memAchVO.getComDate());

			
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
	public void delete(Integer memno, Integer achno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, achno);
			
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
	public MemAchVO findByPrimaryKey(Integer memno, Integer achno) {
		// TODO Auto-generated method stub
		MemAchVO memAchVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, achno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ¤]ºÙ¬° Domain objects
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comdate"));
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
			con=ds.getConnection();
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
	
	@Override
	public List<MemAchVO> getAll() {
		// TODO Auto-generated method stub
		MemAchVO memAchVO=null;
		List<MemAchVO> list=new ArrayList<MemAchVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				memAchVO=new MemAchVO();
				memAchVO.setMemno(rs.getInt("memno"));
				memAchVO.setAchno(rs.getInt("achno"));
				memAchVO.setComDate(rs.getDate("comdate"));
				list.add(memAchVO);
				
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
