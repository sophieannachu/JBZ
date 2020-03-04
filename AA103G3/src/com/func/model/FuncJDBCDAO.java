package com.func.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuncJDBCDAO implements FuncDAO_interface{
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="TSE3";
	String pwd="s0931126800";
	
	private static final String INSERT ="INSERT INTO func (funcno, name) VALUES (func_seq.NEXTVAL, ?)";
	private static final String UPDATE ="UPDATE func SET name=? WHERE funcno=?";
	private static final String DELETE ="DELETE FROM func WHERE funcno=?";
	private static final String SELECT_ONE ="SELECT funcno, name FROM func WHERE funcno=?";
	private static final String SELECT_ALL ="SELECT funcno, name FROM func ORDER BY funcno";
	private static final String SELECT_FUNCNO ="SELECT funcno FROM func ORDER BY funcno";


	@Override
	public void insert(FuncVO funcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, funcVO.getName());

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
	public void update(FuncVO funcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funcVO.getName());
			pstmt.setInt(2, funcVO.getFuncno());

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
	public void delete(Integer funcno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, funcno);

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
	public FuncVO findByPrimaryKey(Integer funcno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FuncVO funcVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, funcno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setName(rs.getString("name"));
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
		return funcVO;
	}

	@Override
	public List<FuncVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FuncVO> list = new ArrayList();
		FuncVO funcVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setName(rs.getString("name"));
				list.add(funcVO);
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
	public List<Integer> getFuncNo() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(SELECT_FUNCNO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
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

	public static void main(String[] args) {
		FuncJDBCDAO dao=new FuncJDBCDAO();
		FuncVO funcVO=new FuncVO();
		List<FuncVO> list=new ArrayList();
		
//琩高
//		funcVO=dao.findByPrimaryKey(3);
//		System.out.println("琩高= ");
//		System.out.println("Funcno: " + funcVO.getFuncno());
//		System.out.println("Name: " + funcVO.getName());
		
		
//		琩高场
//		list=dao.getAll();
//		for(FuncVO func:list){
//			System.out.println("***************" + func.getFuncno() + "***************");
//			System.out.println("Empno: " + func.getFuncno());
//			System.out.println("Name: " + func.getName());
//		}
		
		
//		穝糤
//		funcVO.setName("xxxxxx");
//		dao.insert(funcVO);
		
		
//		穝
//		funcVO.setFuncno(6);
//		funcVO.setName("aaaaaaaaa");
//		dao.update(funcVO);
		
		
//		埃
//		dao.delete(6);
	}
}
