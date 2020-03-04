package iii.jbz.server.analysis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SleepCheckDAO implements SleepCheckDAO_interface{
	
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
	
	private static final String GET_ALL_STMT=
			"SELECT sleepCheckno,bedTime,wakeTime,sleepTime,memno from sleepcheck order by wakeTime ";
	private static final String GET_ALLBYMEM_STMT=
			"SELECT sleepCheckno,bedTime,wakeTime,sleepTime,memno from sleepcheck where memno=? order by wakeTime";
	

	@Override
	public List<SleepCheckVO> getAll() {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO=null;
		List<SleepCheckVO> list=new ArrayList<SleepCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
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
	public List<SleepCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO=null;
		List<SleepCheckVO> list=new ArrayList<SleepCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
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
