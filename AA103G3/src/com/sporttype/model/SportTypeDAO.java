package com.sporttype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SportTypeDAO implements SportTypeDAO_interface{
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
			"SELECT type_no,sport_name,type_pic from sporttype";
	@Override
	public List<SportTypeVO> getAll() {
		// TODO Auto-generated method stub
		SportTypeVO sportTypeVO=null;
		List<SportTypeVO> list=new ArrayList<SportTypeVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
						
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				sportTypeVO=new SportTypeVO();
				sportTypeVO.setType_no(rs.getInt("type_no"));
				sportTypeVO.setSport_name(rs.getString("sport_name"));
				sportTypeVO.setType_pic(rs.getBytes("type_pic"));
				list.add(sportTypeVO);				
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
