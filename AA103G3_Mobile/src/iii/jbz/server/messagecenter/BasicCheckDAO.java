package iii.jbz.server.messagecenter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;


public class BasicCheckDAO implements BasicCheckDAO_interface{

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
	
//	private static final String INSERT_STMT=
//			"INSERT¡@INTO basiccheck (basicCheckno,weight,bmi,bmr,bFat,waisyline,checkdate,memno) VALUES (basiccheckno_seq.NEXTVAL,?,?,?,?,?,?,?)";
//	private static final String UPDATE_STMT=
//			"UPDATE¡@basiccheck SET weight=?,bmi=?,bmr=?,bFat=?,waisyline=?,checkdate=? where basicCheckno=?";
//	private static final String DELETE_STMT=
//			"DELETE¡@basiccheck where basicCheckno=?";
//	private static final String GET_ONE_STMT = 
//			"SELECT basicCheckno,weight,bmi,bmr,bFat,waisyline,checkdate,memno from basiccheck where basicCheckno=?";
//	private static final String GET_AVG_STMT=
//			"SELECT AVG(weight) weight,AVG(bmi) bmi,AVG(bmr) bmr, AVG(bFat) bFat,AVG(waisyline) waisyline from basiccheck where memno=? and to_char(checkdate,'yyyy-mm-dd')=?";

	private static final String GET_ALL_STMT=
			"SELECT basicCheckno,weight,bmi,bmr,bFat,waisyline,checkdate,memno from basiccheck order by checkdate desc";
	private static final String GET_NEWEST_STMT=
			"SELECT basicCheckno,weight,bmi,bmr,bFat,waisyline,checkdate,memno from basiccheck where memno=? and TO_CHAR(checkdate,'YYYY-MM-DD') = TO_CHAR(SYSDATE-1,'YYYY-MM-DD')";
	

	@Override
	public List<BasicCheckVO> findNewestByDate(Integer memno) {
		// TODO Auto-generated method stub
		BasicCheckVO basicCheckVO=null;
		List<BasicCheckVO> list=new ArrayList<BasicCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
			
			pstmt.setInt(1, memno);

			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				basicCheckVO=new BasicCheckVO();
				basicCheckVO.setBasicCheckno(rs.getInt("basicCheckno"));
				basicCheckVO.setWeight(rs.getInt("weight"));
				basicCheckVO.setBmi(rs.getDouble("bmi"));
				basicCheckVO.setBmr(rs.getDouble("bmr"));
				basicCheckVO.setbFat(rs.getDouble("bFat"));
				basicCheckVO.setWaisyline(rs.getDouble("waisyline"));
				basicCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				basicCheckVO.setMemno(rs.getInt("memno"));
				list.add(basicCheckVO);
				
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
	public List<BasicCheckVO> getAll(Integer memno) {
		// TODO Auto-generated method stub
		BasicCheckVO basicCheckVO=null;
		List<BasicCheckVO> list=new ArrayList<BasicCheckVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT);
						
			pstmt.setInt(1, memno);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				basicCheckVO=new BasicCheckVO();
				basicCheckVO.setBasicCheckno(rs.getInt("basicCheckno"));
				basicCheckVO.setWeight(rs.getInt("weight"));
				basicCheckVO.setBmi(rs.getDouble("bmi"));
				basicCheckVO.setBmr(rs.getDouble("bmr"));
				basicCheckVO.setbFat(rs.getDouble("bFat"));
				basicCheckVO.setWaisyline(rs.getDouble("waisyline"));
				basicCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
				basicCheckVO.setMemno(rs.getInt("memno"));
				list.add(basicCheckVO);
				
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
