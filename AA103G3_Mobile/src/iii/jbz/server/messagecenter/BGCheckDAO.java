package iii.jbz.server.messagecenter;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BGCheckDAO implements BGCheckDAO_interface{
	
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
			"INSERT¡@INTO bgcheck (bgCheckno,bgbfmeat,bgafmeat,bgbfsleep,checkdate,memno) VALUES (bgcheckno_seq.NEXTVAL,?,?,?,?,?)";
	private static final String UPDATE_STMT=
			"UPDATE¡@bgcheck SET bgbfmeat=?,bgafmeat=?,bgbfsleep=? where bgCheckno=?";
	private static final String DELETE_STMT=
			"DELETE¡@bgcheck where bgCheckno=?";
	private static final String GET_ONE_STMT = 
			"SELECT bgCheckno,bgbfmeat,bgafmeat,bgbfsleep,checkdate,memno from bgcheck where bgCheckno=?";
	private static final String GET_AVG_STMT=
			"SELECT AVG(bgbfmeat) bgbfmeat,AVG(bgafmeat) bgafmeat,AVG(bgbfsleep) bgbfsleep from bgcheck where memno=? and to_char(checkdate,'yyyy-mm-dd')=TO_CHAR(SYSDATE-1,'YYYY-MM-DD')";
	private static final String GET_NEWEST_STMT=
			"SELECT bgCheckno,bgbfmeat,bgafmeat,bgbfsleep,checkdate,memno from bgcheck where memno=? and TO_CHAR(checkdate,'YYYY-MM-DD') = TO_CHAR(SYSDATE-1,'YYYY-MM-DD')";
	private static final String GET_ALL_STMT=
			"SELECT bgCheckno,bgbfmeat,bgafmeat,bgbfsleep,checkdate,memno from bgcheck where memno=? order by checkdate desc";
	

	@Override
	public List<BGCheckVO> findAVGByDate(Integer memno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO=null;
		List<BGCheckVO> list=new ArrayList<BGCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_AVG_STMT);
			
			pstmt.setInt(1, memno);
//			pstmt.setString(2, checkDate);
//			pstmt.setString(2, checkDate.toString());
			rs=pstmt.executeQuery();
			
			rs.next();
			bgCheckVO=new BGCheckVO();
//			bgCheckVO.setBgCheckno(rs.getInt("bgcheckno"));
			bgCheckVO.setBgBfMeat(rs.getInt("bgbfmeat"));
			bgCheckVO.setBgAfMeat(rs.getInt("bgafmeat"));
			bgCheckVO.setBgBfSleep(rs.getInt("bgbfsleep"));
//			bgCheckVO.setCheckDate(checkDate);
			bgCheckVO.setMemno(memno);
				
					
			
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
	public List<BGCheckVO> findNewestByDate(Integer memno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO=null;
		List<BGCheckVO> list=new ArrayList<BGCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
			
			pstmt.setInt(1, memno);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bgCheckVO=new BGCheckVO();
				bgCheckVO.setBgCheckno(rs.getInt("bgcheckno"));
				bgCheckVO.setBgBfMeat(rs.getInt("bgbfmeat"));
				bgCheckVO.setBgAfMeat(rs.getInt("bgafmeat"));
				bgCheckVO.setBgBfSleep(rs.getInt("bgbfsleep"));
				bgCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				bgCheckVO.setMemno(rs.getInt("memno"));
				list.add(bgCheckVO);
				
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
	public List<BGCheckVO> getAll(Integer memno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO=null;
		List<BGCheckVO> list=new ArrayList<BGCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
			
			pstmt.setInt(1, memno);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bgCheckVO=new BGCheckVO();
				bgCheckVO.setBgCheckno(rs.getInt("bgcheckno"));
				bgCheckVO.setBgBfMeat(rs.getInt("bgbfmeat"));
				bgCheckVO.setBgAfMeat(rs.getInt("bgafmeat"));
				bgCheckVO.setBgBfSleep(rs.getInt("bgbfsleep"));
				bgCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				bgCheckVO.setMemno(rs.getInt("memno"));
				list.add(bgCheckVO);
				
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
