package com.emp.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSE3";
	String pwd = "s0931126800";

	private static final String INSERT = "INSERT INTO emp (empno, name, acc, pwd, sex, birth, phone, email, photo) VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE emp SET name=?, acc=?, pwd=?, sex=?, birth=?, phone=?, email=?, photo=? WHERE empno=?";
	private static final String DELETE = "DELETE FROM emp WHERE empno=?";
	private static final String SELECT_ONE = "SELECT empno, name, acc, pwd, sex, to_char(birth, 'YYYY-MM-DD') birth, phone, email, photo FROM emp WHERE empno=?";
	private static final String SELECT_ALL = "SELECT empno, name, acc, pwd, sex, to_char(birth, 'YYYY-MM-DD') birth, phone, email, photo FROM emp ORDER BY empno";
	private static final String SELECT_IMG = "SELECT photo FROM emp WHERE empno=?";
	private static final String SELECT_EMPNO = "SELECT empno FROM emp WHERE acc in ?";
	private static final String SELECT_ACC = "SELECT empno, name, acc, pwd, sex, to_char(birth, 'YYYY-MM-DD') birth, phone, email, photo FROM emp WHERE acc in ?";
	

	@Override
	public void insert(EmpVO empVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, empVo.getName());
			pstmt.setString(2, empVo.getAcc());
			pstmt.setString(3, empVo.getPwd());
			pstmt.setString(4, empVo.getSex());
			pstmt.setDate(5, empVo.getBirth());
			pstmt.setString(6, empVo.getPhone());
			pstmt.setString(7, empVo.getEmail());
			pstmt.setBytes(8, empVo.getPhoto());

			pstmt.executeUpdate();
			System.out.println("穝糤Θ!!!");

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
	public void update(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getName());
			pstmt.setString(2, empVO.getAcc());
			pstmt.setString(3, empVO.getPwd());
			pstmt.setString(4, empVO.getSex());
			pstmt.setDate(5, empVO.getBirth());
			pstmt.setString(6, empVO.getPhone());
			pstmt.setString(7, empVO.getEmail());
			pstmt.setBytes(8, empVO.getPhoto());
			pstmt.setInt(9, empVO.getEmpno());

			pstmt.executeUpdate();
			System.out.println("穝Θ!!!");
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
	public void delete(Integer empno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

			pstmt.executeUpdate();
			System.out.println("埃Θ!!!");
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
	public EmpVO findByPrimaryKey(Integer empno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO empVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setName(rs.getString("name"));
				empVO.setAcc(rs.getString("acc"));
				empVO.setPwd(rs.getString("pwd"));
				empVO.setSex(rs.getString("sex"));
				empVO.setBirth(rs.getDate("birth"));
				empVO.setPhone(rs.getString("phone"));
				empVO.setEmail(rs.getString("email"));
				empVO.setPhoto(rs.getBytes("photo"));

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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpVO> list = new ArrayList();
		EmpVO empVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setName(rs.getString("name"));
				empVO.setAcc(rs.getString("acc"));
				empVO.setPwd(rs.getString("pwd"));
				empVO.setSex(rs.getString("sex"));
				empVO.setBirth(rs.getDate("birth"));
				empVO.setPhone(rs.getString("phone"));
				empVO.setEmail(rs.getString("email"));
				empVO.setPhoto(rs.getBytes("photo"));

				list.add(empVO);
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
	public byte[] getImg(Integer empno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] photo = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_IMG);

			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				photo = rs.getBytes("photo");
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
		return photo;
		
	}
	
	@Override
	public Integer getEmpno(String acc) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer empno = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_EMPNO);

			pstmt.setString(1, acc);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empno = rs.getInt("empno");
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
		return empno;
	}
	
	@Override
	public EmpVO getOneByAcc(String acc) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO empVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ACC);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setName(rs.getString("name"));
				empVO.setAcc(rs.getString("acc"));
				empVO.setPwd(rs.getString("pwd"));
				empVO.setSex(rs.getString("sex"));
				empVO.setBirth(rs.getDate("birth"));
				empVO.setPhone(rs.getString("phone"));
				empVO.setEmail(rs.getString("email"));
				empVO.setPhoto(rs.getBytes("photo"));

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
		return empVO;
	}

	public static void main(String[] args) throws Exception{
		EmpJDBCDAO dao=new EmpJDBCDAO();
		EmpVO empVO=new EmpVO();
		List<EmpVO> list=new ArrayList();
		
//		int n=10;
//		for(int i=1;i<=n;i++){
//			InputStream in = new FileInputStream("c:/img/0" + i + ".jpg");
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			empVO.setEmpno(i);
//			empVO.setPhoto(buf);
//			dao.updateImg(empVO);
//			in.close();
//		}
		
//		穝糤
//		InputStream in = new FileInputStream("c:/pic/tomcat.gif");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		in.close();
//		empVO.setName("朝11");
//		empVO.setAcc("s80091901");
//		empVO.setPwd("123456");
//		empVO.setSex("1");
//		empVO.setBirth(java.sql.Date.valueOf("1991-09-19"));
//		empVO.setPhone("0931126800");
//		empVO.setEmail("s80091901@gmail.com");
//		empVO.setPhoto(buf);
//		dao.insert(empVO);
		
		
//琩高
//		empVO=dao.findByPrimaryKey(2);
//		System.out.println("琩高= ");
//		System.out.println("Empno: " + empVO.getEmpno());
//		System.out.println("Name: " + empVO.getName());
//		System.out.println("Acc: " + empVO.getAcc());
//		System.out.println("Pwd: " + empVO.getPwd());
//		System.out.println("Sex: " + empVO.getSex());
//		System.out.println("Birth: " + empVO.getBirth());
//		System.out.println("Phone: " + empVO.getPhone());
//		System.out.println("Email: " + empVO.getEmail());
//		System.out.println("Photo: " + empVO.getPhoto());
		
		
//		琩高场
//		list=dao.getAll();
//		for(EmpVO emp:list){
//			System.out.println("***************" + emp.getEmpno() + "***************");
//			System.out.println("Empno: " + emp.getEmpno());
//			System.out.println("Name: " + emp.getName());
//			System.out.println("Acc: " + emp.getAcc());
//			System.out.println("Pwd: " + emp.getPwd());
//			System.out.println("Sex: " + emp.getSex());
//			System.out.println("Birth: " + emp.getBirth());
//			System.out.println("Phone: " + emp.getPhone());
//			System.out.println("Email: " + emp.getEmail());
//			System.out.println("Photo: " + emp.getPhoto());
//		}
		
		
//		穝
//		empVO.setEmpno(11);
//		empVO.setName("朝緼");
//		empVO.setAcc("xxxxxxxx");
//		empVO.setPwd("xxxxxxxx");
//		empVO.setSex("1");
//		empVO.setBirth(java.sql.Date.valueOf("1991-09-19"));
//		empVO.setPhone("0931126800");
//		empVO.setEmail("xxxxxxxxxx@gmail.com");
//		empVO.setPhoto(null);
//		dao.update(empVO);
		
		
//		埃
//		dao.delete(11);
		
	}


	public void updateImg(EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement("UPDATE emp SET photo=? WHERE empno=?");

			pstmt.setBytes(1, empVO.getPhoto());
			pstmt.setInt(2, empVO.getEmpno());

			pstmt.executeUpdate();
			System.out.println("穝Θ!!!");
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

}
