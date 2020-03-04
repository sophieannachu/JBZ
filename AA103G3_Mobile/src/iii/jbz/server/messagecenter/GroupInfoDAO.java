package iii.jbz.server.messagecenter;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;

public class GroupInfoDAO implements GroupInfoDAO_interface {
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
		
	}
	

	private static final String GET_ALL_STMT = "SELECT group_no, memno, group_name, group_loc, group_detail, group_count, "
			+ "to_char(group_time,'yyyy/mm/dd hh:mi'), group_long, group_lati FROM GROUP_INFO order by group_no";
	private static final String INSERT_STMT = "INSERT INTO GROUP_INFO (group_no,memno,group_name,group_loc,group_detail,group_count,"
			+ "group_check,group_photo)" + "VALUES (GROUP_INFO_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "select * From GROUP_INFO where memno = ?";
	private static final String GET_NEWEST_STMT = "SELECT group_no, memno, group_name, group_loc, group_detail, group_count, "
			+ "group_time, group_long, group_lati FROM GROUP_INFO where memno=? and TO_CHAR(group_time,'YYYY-MM-DD') = TO_CHAR(SYSDATE+1,'YYYY-MM-DD')";
	



	@Override
	public List<GroupInfoVO> getAll(Integer memno) {
		List<GroupInfoVO> list = new ArrayList<GroupInfoVO>();
		// FoodVO empVO = null;
		//GroupInfoVO
		GroupInfoVO groupInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEWEST_STMT);
			
			pstmt.setInt(1,memno);
			
			rs = pstmt.executeQuery();


			while (rs.next()) {
				groupInfoVO = new GroupInfoVO();
				groupInfoVO.setGroup_no(rs.getInt("group_no"));
				groupInfoVO.setGroup_name(rs.getString("group_name"));
				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
				groupInfoVO.setGroup_count(rs.getInt("group_count"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setGroup_long(rs.getString("group_long"));
				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
				groupInfoVO.setMemno(rs.getInt("memno"));
				list.add(groupInfoVO);

			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

}

