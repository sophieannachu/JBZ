package iii.jbz.server.analysis;

import java.sql.*;
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
	

	private static final String GET_ALL_STMT=
			"SELECT bgCheckno,bgbfmeat,bgafmeat,bgbfsleep,checkdate,memno from bgcheck where memno=? order by checkdate";
	


	@Override
	public List<BGCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO=null;
		List<BGCheckVO> list=new ArrayList<BGCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			
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
