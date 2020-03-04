package iii.jbz.server.messagecenter;

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

public class BPCheckDAO implements BPCheckDAO_interface{
	
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
			"INSERT¡@INTO bpcheck (bpCheckno,sPressure,dPressure,checkdate,memno) VALUES (bpcheckno_seq.NEXTVAL,?,?,?,?)";
	private static final String UPDATE_STMT=
			"UPDATE¡@bpcheck SET sPressure=?,dPressure=? where bpCheckno=?";
	private static final String DELETE_STMT=
			"DELETE¡@bpcheck where bpCheckno=?";
	private static final String GET_ONE_STMT = 
			"SELECT bpCheckno,sPressure,dPressure,checkdate,memno from bpcheck where bpCheckno=?";
	private static final String GET_NEWEST_STMT=
			"SELECT bpCheckno,sPressure,dPressure,checkdate,memno from bpcheck where memno=? and TO_CHAR(checkdate,'YYYY-MM-DD') = TO_CHAR(SYSDATE-1,'YYYY-MM-DD')";
	private static final String GET_ALL_STMT=
			"SELECT bpCheckno,sPressure,dPressure,checkdate,memno from bpcheck where memno=? order by checkdate desc";	
	
	@Override
	public List<BPCheckVO> findNewestByDate(Integer memno) {
		// TODO Auto-generated method stub
		BPCheckVO bpCheckVO=null;
		List<BPCheckVO> list = new ArrayList<BPCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
			pstmt.setInt(1, memno);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bpCheckVO=new BPCheckVO();
				bpCheckVO.setBpCheckno(rs.getInt("bpcheckno"));
				bpCheckVO.setsPressure(rs.getInt("sPressure"));
				bpCheckVO.setdPressure(rs.getInt("dPressure"));
				bpCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				bpCheckVO.setMemno(rs.getInt("memno"));
				list.add(bpCheckVO);
				
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
	public List<BPCheckVO> getAll(Integer memno) {
		// TODO Auto-generated method stub
		BPCheckVO bpCheckVO=null;
		List<BPCheckVO> list=new ArrayList<BPCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
			
			pstmt.setInt(1, memno);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bpCheckVO=new BPCheckVO();
				bpCheckVO.setBpCheckno(rs.getInt("bpcheckno"));
				bpCheckVO.setsPressure(rs.getInt("sPressure"));
				bpCheckVO.setdPressure(rs.getInt("dPressure"));
				bpCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				bpCheckVO.setMemno(rs.getInt("memno"));
				list.add(bpCheckVO);
				
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
