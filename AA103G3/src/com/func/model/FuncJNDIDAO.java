package com.func.model;

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

public class FuncJNDIDAO implements FuncDAO_interface{
	private static DataSource ds = null;
	
	static {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, funcVO.getName());

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
	public void update(FuncVO funcVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funcVO.getName());
			pstmt.setInt(2, funcVO.getFuncno());

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
	public void delete(Integer funcno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, funcno);

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
	public FuncVO findByPrimaryKey(Integer funcno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FuncVO funcVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, funcno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setName(rs.getString("name"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setName(rs.getString("name"));
				list.add(funcVO);
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
	public List<Integer> getFuncNo() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_FUNCNO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("funcno"));
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

}
