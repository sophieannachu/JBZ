package com.heinfo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class HeinfoDAO implements HeinfoDAO_interface{

	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = 
			"INSERT INTO heinfo (heinfono,heinfoname,heinfodetail,heinfodate,heinfophoto) VALUES (heinfo_seq.NEXTVAL,?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT heinfono,heinfoname,heinfodetail,to_char(heinfodate,'yyyy-mm-dd') heinfodate,heinfophoto FROM heinfo order by heinfono";
		private static final String GET_ONE_STMT = 
			"SELECT heinfono,heinfoname,heinfodetail,to_char(heinfodate,'yyyy-mm-dd') heinfodate,heinfophoto FROM heinfo where heinfono = ?";
		private static final String DELETE = 
			"DELETE FROM heinfo where heinfono = ?";
		private static final String UPDATE = 
			"UPDATE heinfo set heinfoname=?, heinfodetail=?, heinfodate=?, heinfophoto=? where heinfono = ?";
	
	
	@Override
	public void insert(HeinfoVO heinfoVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setString(1, heinfoVO.getHeinfoname());
			pstmt.setString(2, heinfoVO.getHeinfodetail());
			pstmt.setDate(3, heinfoVO.getHeinfodate());
			pstmt.setBytes(4, heinfoVO.getHeinfophoto());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	}

	@Override
	public void update(HeinfoVO heinfoVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, heinfoVO.getHeinfoname());
			pstmt.setString(2, heinfoVO.getHeinfodetail());
			pstmt.setDate(3, heinfoVO.getHeinfodate());
			pstmt.setBytes(4, heinfoVO.getHeinfophoto());
			pstmt.setInt(5, heinfoVO.getHeinfono());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	}

	@Override
	public void delete(Integer heinfono) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, heinfono);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	}

	@Override
	public HeinfoVO findByPrimaryKey(Integer heinfono) {
		// TODO Auto-generated method stub
		HeinfoVO heinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, heinfono);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				heinfoVO = new HeinfoVO();
				heinfoVO.setHeinfono(rs.getInt("heinfono"));
				heinfoVO.setHeinfoname(rs.getString("heinfoname"));
				heinfoVO.setHeinfodetail(rs.getString("heinfodetail"));
				heinfoVO.setHeinfodate(rs.getDate("heinfodate"));
				heinfoVO.setHeinfophoto(rs.getBytes("heinfophoto"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return heinfoVO;
	}

	@Override
	public List<HeinfoVO> getAll() {
		// TODO Auto-generated method stub
		List<HeinfoVO> list = new ArrayList<HeinfoVO>();
		HeinfoVO heinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
 
		try {

		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				heinfoVO = new HeinfoVO();
				heinfoVO.setHeinfono(rs.getInt("heinfono"));
				heinfoVO.setHeinfoname(rs.getString("heinfoname"));
				heinfoVO.setHeinfodetail(rs.getString("heinfodetail"));
				heinfoVO.setHeinfodate(rs.getDate("heinfodate"));
				heinfoVO.setHeinfophoto(rs.getBytes("heinfophoto"));
				list.add(heinfoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return list;
	}
	
//public static void main(String[] args) {
//
//		HeinfoDAO dao = new HeinfoDAO();
//		File pic = new File("C:\\AA103_webApp\\eclipse_WTP_workspace1\\AA103G3\\WebContent\\IMAGES","a4.jpg");
//		byte[] bfile = new byte[(int)pic.length()];
//		FileInputStream fin;
//		try {
//			fin = new FileInputStream(pic);
//			fin.read(bfile);
//			fin.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		// 新增
//		HeinfoVO heinfoVO1 = new HeinfoVO();
//		
//		heinfoVO1.setHeinfoname("老年人該避免喝咖啡嗎？");
//		heinfoVO1.setHeinfodetail("對該避免喝咖啡");
//		heinfoVO1.setHeinfodate(java.sql.Date.valueOf("2015-09-01"));
//		heinfoVO1.setHeinfophoto(bfile);
//		
//		dao.insert(heinfoVO1);
//
//		// 修改
//		HeinfoVO heinfoVO2 = new HeinfoVO();
//		heinfoVO2.setHeinfono(1);
//		heinfoVO2.setHeinfoname("老年人該避免喝咖啡嗎？");
//		heinfoVO2.setHeinfodetail("馬偕醫院營養師趙強指出，喝完咖啡之後45～50分鐘，血中咖啡因濃度最高，效能約可維持3～4個小時。"
//				+ "一般來說，多數成年人對於咖啡因的耐受度較好，但隨著年齡增長，可能因為人體代謝力下降，"
//				+ "原本喝幾杯咖啡仍不影響睡眠的人，也可能因為一杯咖啡而輾轉難眠。那麼，老年人該避免喝咖啡嗎？台北榮總神經內科醫師王培寧認為，"
//				+ "年齡多寡並不是停喝咖啡的主要因素，而端看這個人平日是否慣喝咖啡，如果日常已有飲用習慣，就算是高齡也不必停止，但如果本來沒喝，"
//				+ "也不需為了咖啡裡含了對身體有益的抗氧化物而開始喝咖啡。美國布朗大學的調查也能呼應，每天喝不到一杯咖啡的中老年人，如果一早起床就喝咖啡，"
//				+ "可能會刺激神經，導致血管阻塞引發中風，機率比平常人高6倍，不過對於常喝咖啡（研究中指的是每天喝4杯咖啡）的人來說，中風機率卻不會提高。");
//		heinfoVO2.setHeinfodate(java.sql.Date.valueOf("2015-09-01"));
////		heinfoVO1.setHeinfophoto();
//		
//		dao.update(heinfoVO2);

		// 刪除
//		dao.delete(1);

		// 查詢
//		HeinfoVO heinfoVO3 = dao.findByPrimaryKey(3);
//		System.out.print(heinfoVO3.getHeinfono() + ",");
//		System.out.print(heinfoVO3.getHeinfoname() + ",");
//		System.out.print(heinfoVO3.getHeinfodetail() + ",");
//		System.out.print(heinfoVO3.getHeinfodate() + ",");
//		System.out.println(heinfoVO3.getHeinfophoto());
//		System.out.println("---------------------");

		// 查詢
//		List<HeinfoVO> list = dao.getAll();
//		for (HeinfoVO aHeinfo : list) {
//			System.out.print(aHeinfo.getHeinfono() + ",");
//			System.out.print(aHeinfo.getHeinfoname() + ",");
//			System.out.print(aHeinfo.getHeinfodetail() + ",");
//			System.out.print(aHeinfo.getHeinfodate() + ",");
//			System.out.print(aHeinfo.getHeinfophoto());
//			System.out.println();
//		}
//	}
}
