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

	
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
				// empVo �]�٬� Domain objects
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
				// empVO �]�٬� Domain objects
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
//		// �s�W
//		HeinfoVO heinfoVO1 = new HeinfoVO();
//		
//		heinfoVO1.setHeinfoname("�Ѧ~�H���קK�ܩ@�ضܡH");
//		heinfoVO1.setHeinfodetail("����קK�ܩ@��");
//		heinfoVO1.setHeinfodate(java.sql.Date.valueOf("2015-09-01"));
//		heinfoVO1.setHeinfophoto(bfile);
//		
//		dao.insert(heinfoVO1);
//
//		// �ק�
//		HeinfoVO heinfoVO2 = new HeinfoVO();
//		heinfoVO2.setHeinfono(1);
//		heinfoVO2.setHeinfoname("�Ѧ~�H���קK�ܩ@�ضܡH");
//		heinfoVO2.setHeinfodetail("������|��i�v���j���X�A�ܧ��@�ؤ���45��50�����A�夤�@�ئ]�@�׳̰��A�į���i����3��4�Ӥp�ɡC"
//				+ "�@��ӻ��A�h�Ʀ��~�H���@�ئ]���@���׸��n�A���H�ۦ~�ּW���A�i��]���H��N�¤O�U���A"
//				+ "�쥻�ܴX�M�@�ؤ����v�T�ίv���H�A�]�i��]���@�M�@�ئ��������v�C����A�Ѧ~�H���קK�ܩ@�ضܡH�x�_�a�`���g������v������{���A"
//				+ "�~�֦h��ä��O���ܩ@�ت��D�n�]���A�Ӻݬݳo�ӤH����O�_�D�ܩ@�ءA�p�G��`�w�����βߺD�A�N��O���֤]��������A���p�G���ӨS�ܡA"
//				+ "�]���ݬ��F�@�ظ̧t�F�鶴�馳�q���ܮ�ƪ��Ӷ}�l�ܩ@�ءC���ꥬ�Ԥj�Ǫ��լd�]��I���A�C�ѳܤ���@�M�@�ت����Ѧ~�H�A�p�G�@���_�ɴN�ܩ@�ءA"
//				+ "�i��|��E���g�A�ɭP��ު���޵o�����A���v�񥭱`�H��6���A���L���`�ܩ@�ء]��s�������O�C�ѳ�4�M�@�ء^���H�ӻ��A�������v�o���|�����C");
//		heinfoVO2.setHeinfodate(java.sql.Date.valueOf("2015-09-01"));
////		heinfoVO1.setHeinfophoto();
//		
//		dao.update(heinfoVO2);

		// �R��
//		dao.delete(1);

		// �d��
//		HeinfoVO heinfoVO3 = dao.findByPrimaryKey(3);
//		System.out.print(heinfoVO3.getHeinfono() + ",");
//		System.out.print(heinfoVO3.getHeinfoname() + ",");
//		System.out.print(heinfoVO3.getHeinfodetail() + ",");
//		System.out.print(heinfoVO3.getHeinfodate() + ",");
//		System.out.println(heinfoVO3.getHeinfophoto());
//		System.out.println("---------------------");

		// �d��
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
