package iii.ron.server.spots;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class GroupListDAO implements GroupListDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
		"INSERT INTO GROUP_LIST (group_no,memno) "
		+ "VALUES (? , ?)";
	private static final String DELETE = 
		"DELETE FROM GROUP_LIST where group_no = ? and memno = ?";
	
	
	@Override
	public int insert(int group_no, int memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, memno);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			return 0;
		} finally {
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
		return 1;
	}


	@Override
	public int delete(int group_no, int memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, memno);
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
			return 0;
		} finally {
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
		return 1;
	}
	
}