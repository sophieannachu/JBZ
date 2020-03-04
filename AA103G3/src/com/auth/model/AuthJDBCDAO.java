package com.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.func.model.FuncJDBCDAO;
import com.func.model.FuncJNDIDAO;
import com.func.model.FuncService;


public class AuthJDBCDAO implements AuthDAO_interface{
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="TSE3";
	String pwd="s0931126800";
	
//	FuncJDBCDAO sv=new FuncJDBCDAO();
//	List<Integer> list=sv.getFuncNo();
	
	private static final String INSERT ="INSERT INTO auth (empno, funcno, permit) VALUES (?, ?, ?)";
	private static final String UPDATE ="UPDATE auth SET permit=? WHERE empno=? and funcno=?";
	private static final String DELETE ="DELETE FROM auth WHERE empno=? and funcno=?";
	private static final String SELECT_ONE ="SELECT empno, funcno, permit FROM auth WHERE empno=? and funcno=?";
	private static final String SELECT_ALL ="SELECT empno, funcno, permit FROM auth ORDER BY empno, funcno";
	private static final String INSERT2 ="INSERT INTO auth (empno, funcno) VALUES (?, ?)";
	private static final String DELETE2 ="DELETE FROM auth WHERE empno=?";
	private static final String UPDATE2 ="UPDATE auth SET permit=1 WHERE empno=? and funcno=?";
	private static final String SELECT_ONE_EMPNO ="SELECT funcno FROM auth WHERE empno=? ORDER BY funcno";
	private static final String EMPTY ="UPDATE auth SET permit=0 WHERE empno=? and funcno=?";


	@Override
	public void insert(AuthVO authVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, authVO.getEmpno());
			pstmt.setInt(2, authVO.getFuncno());
			pstmt.setString(3, authVO.getPermit());

			pstmt.executeUpdate();
			System.out.println("�s�W���!!!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(AuthVO authVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getPermit());
			pstmt.setInt(2, authVO.getEmpno());
			pstmt.setInt(3, authVO.getFuncno());

			pstmt.executeUpdate();
			System.out.println("��s���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer empno, Integer funcno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);
			pstmt.setInt(2, funcno);

			pstmt.executeUpdate();
			System.out.println("�R�����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public AuthVO findByPrimaryKey(Integer empno, Integer funcno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuthVO authVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, empno);
			pstmt.setInt(2, funcno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				authVO = new AuthVO();
				authVO.setEmpno(rs.getInt("empno"));
				authVO.setFuncno(rs.getInt("funcno"));
				authVO.setPermit(rs.getString("permit"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return authVO;
	}

	@Override
	public List<AuthVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuthVO> list = new ArrayList();
		AuthVO authVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmpno(rs.getInt("empno"));
				authVO.setFuncno(rs.getInt("funcno"));
				authVO.setPermit(rs.getString("permit"));
				list.add(authVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Integer> getOne(Integer empno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList();
//		AuthVO authVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ONE_EMPNO);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
//				authVO = new AuthVO();
//				authVO.setEmpno(rs.getInt("empno"));
//				authVO.setFuncno(rs.getInt("funcno"));
				list.add(rs.getInt("funcno"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	

	@Override
	public void insertOne(Integer empno, String[] auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT2);

			
			deleteOne(empno);
			if(auth != null){
				for(String s:auth){
					pstmt.setInt(1, empno);
					pstmt.setInt(2, Integer.parseInt(s));
					System.out.println(Integer.parseInt(s));
					pstmt.executeUpdate();
				}
				System.out.println("�s�W���");
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void insertOne(Integer empno, Integer[] auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT2);

			
			deleteOne(empno);
			if(auth != null){
				for(Integer i:auth){
					pstmt.setInt(1, empno);
					pstmt.setInt(2, i);
					pstmt.executeUpdate();
				}
				System.out.println("�s�W���");
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateOne(Integer empno, String[] auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			
			pstmt = con.prepareStatement(EMPTY);
//			for(Integer i:list){
//				pstmt.setInt(1, empno);
//				pstmt.setInt(2, i);
//				pstmt.executeUpdate();
//			}
			System.out.println("�M�Ŧ��");
			
			if(auth == null){
				return;
			}
			pstmt = con.prepareStatement(UPDATE2);
			for(String s:auth){
				pstmt.setInt(1, empno);
				pstmt.setInt(2, Integer.parseInt(s));
				pstmt.executeUpdate();
			}
			System.out.println("��s���");
			


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateOne(Integer empno, Integer[] auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			FuncJDBCDAO sv=new FuncJDBCDAO();
			List<Integer> list=sv.getFuncNo();
			
			pstmt = con.prepareStatement(EMPTY);
//			for(Integer i:list){
//				pstmt.setInt(1, empno);
//				pstmt.setInt(2, i);
//				pstmt.executeUpdate();
//			}
			System.out.println("�M�Ŧ��");
			
			if(auth == null){
				return;
			}
			pstmt = con.prepareStatement(UPDATE2);
			for(Integer i:auth){
				pstmt.setInt(1, empno);
				pstmt.setInt(2, i);
				pstmt.executeUpdate();
			}
			System.out.println("��s���");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void deleteOne(Integer empno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(DELETE2);
			pstmt.setInt(1, empno);
			pstmt.executeUpdate();

			System.out.println("�R�����");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public static void main(String[] args) {
		AuthJDBCDAO dao=new AuthJDBCDAO();
		AuthVO authVO=new AuthVO();
		List<AuthVO> list=new ArrayList();

//�d��
//		authVO=dao.findByPrimaryKey(1, 5);
//		System.out.println("�d��= ");
//		System.out.println("Empno: " + authVO.getEmpno());
//		System.out.println("Funcno: " + authVO.getFuncno());
//		System.out.println("Permit: " + authVO.getPermit());
		
		
//		�d�ߥ���
//		list=dao.getAll();
//		for(AuthVO auth:list){
//			System.out.println("******************************");
//			System.out.println("Empno: " + auth.getEmpno());
//			System.out.println("Funcno: " + auth.getFuncno());
//			System.out.println("Permit: " + auth.getPermit());
//		}
		
//		�d�߭��u
//		list=dao.getOne(1);
//		for(AuthVO auth:list){
//			System.out.println("******************************");
//			System.out.println("Empno: " + auth.getEmpno());
//			System.out.println("Funcno: " + auth.getFuncno());
//			System.out.println("Permit: " + auth.getPermit());
//		}
		
		
//		�s�W
//		authVO.setEmpno(2);
//		authVO.setFuncno(1);
//		authVO.setPermit("0");
//		dao.insert(authVO);
		
		
//		��s
//		authVO.setEmpno(2);
//		authVO.setFuncno(1);
//		authVO.setPermit("1");
//		dao.update(authVO);
		
		
//		�R��
//		dao.delete(2, 1);
		
	}
}
