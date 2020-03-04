package com.emp.model;

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

public class EmpJNDIDAO implements EmpDAO_interface {
	private static DataSource ds=null;
	
	static{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
			con = ds.getConnection();
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
			System.out.println("新增成功!!!");

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
			con = ds.getConnection();
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
			System.out.println("更新成功!!!");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

			pstmt.executeUpdate();
			System.out.println("刪除成功!!!");
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_IMG);

			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				photo = rs.getBytes("photo");
			}

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_EMPNO);

			pstmt.setString(1, acc);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empno = rs.getInt("empno");
			}

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
			con = ds.getConnection();
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

}
