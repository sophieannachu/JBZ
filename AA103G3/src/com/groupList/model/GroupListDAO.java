package com.groupList.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupinfo.model.GroupInfoVO;

import java.sql.*;

public class GroupListDAO implements GroupListDAO_interface {
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

	private static final String GET_ALL_STMT = "SELECT * FROM GROUP_LIST order by GROUP_NO";
	private static final String INSERT_STMT = "INSERT INTO GROUP_LIST (group_no,memno) " + "VALUES (?,?)";
	private static final String GET_ONE_STMT = "select * From GROUP_LIST where group_no = ? and c = ?";
	private static final String GET_ONE_MY_GROUP = "SELECT *  FROM GROUP_LIST where memno=? order by GROUP_NO";
	private static final String GET_COUNT_MY_GROUP = "SELECT count(*)  FROM GROUP_LIST where memno=?";

	// private static final String GET_ONE_STMT =
	// "SELECT fd_no,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,"
	// +"fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal"
	// +" FROM FD_FOOD where fd_no = ?";
	//
	private static final String DELETE = "DELETE FROM GROUP_LIST where group_no = ? and memno = ?";

	// private static final String UPDATE =
	// "UPDATE FD_FOOD set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=?
	// where empno = ?";
	// private static final String UPDATE_STMT =
	// "UPDATE GROUP_LIST set memno=?"
	// +" where group_no=?" ;

	@Override
	public void insert(GroupListVO GroupListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setLong(1, GroupListVO.getGroup_no());
			pstmt.setLong(2, GroupListVO.getMemno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// @Override
	// public void update(GroupListVO GroupListVO) {
	// Connection con = null;
	// PreparedStatement pstmt = null;
	//
	// try {
	//
	// con = ds.getConnection();
	// pstmt = con.prepareStatement(UPDATE_STMT);
	// pstmt.setLong(1, GroupListVO.getMemno());
	// pstmt.setLong(2, GroupListVO.getGroup_no());
	// pstmt.executeUpdate();
	//
	//
	//
	//
	//
	// // Handle any driver errors
	// } catch (SQLException se) {
	// throw new RuntimeException("A database error occured. "
	// + se.getMessage());
	// // Clean up JDBC resources
	// } finally {
	// if (pstmt != null) {
	// try {
	// pstmt.close();
	// } catch (SQLException se) {
	// se.printStackTrace(System.err);
	// }
	// }
	// if (con != null) {
	// try {
	// con.close();
	// } catch (Exception e) {
	// e.printStackTrace(System.err);
	// }
	// }
	// }
	//
	// }

	@Override
	public void delete(Integer Groupno, Integer memno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, Groupno);
			pstmt.setInt(2, memno);
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public GroupListVO findByPrimaryKey(Integer Groupno, Integer memno) {
		GroupListVO GroupListVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Groupno);
			pstmt.setInt(2, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				GroupListVO1 = new GroupListVO();
				GroupListVO1.setGroup_no(rs.getInt("group_no"));
				GroupListVO1.setMemno(rs.getInt("memno"));

			}
			// Handle any driver errors
		} catch (SQLException se) {
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
		return GroupListVO1;
	}

	@Override
	public List<GroupListVO> getAll() {
		List<GroupListVO> list = new ArrayList<GroupListVO>();
		// FoodVO empVO = null;
		GroupListVO GroupListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				GroupListVO = new GroupListVO();
				GroupListVO.setGroup_no(rs.getInt("group_no"));
				GroupListVO.setMemno(rs.getInt("memno"));
				list.add(GroupListVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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

	public static void main(String[] args) {

		GroupListDAO dao = new GroupListDAO();

		// 新增
		GroupListVO GroupListVO = new GroupListVO();
		// GroupListVO.setMemno(1201);
		// dao.insert(GroupInfoVO1);

		// 修改
		// GroupListVO.setMemno(100);
		// GroupListVO.setGroup_no(1);
		// dao.update(GroupListVO);

		// 刪除
		// dao.delete(7,8);

		// 查詢
		// GroupListVO GroupListVO3 = dao.findByPrimaryKey(6,45);
		// System.out.print(GroupListVO3.getGroup_no()+ ",");
		// System.out.print(GroupListVO3.getMemno()+ ",");
		// System.out.println("---------------------");

		// 查詢
		// List<GroupListVO> list = dao.getAll();
		// for (GroupListVO aGroupListVO : list) {
		// System.out.print(aGroupListVO.getGroup_no());
		// System.out.print(aGroupListVO.getMemno());
		// System.out.println();
		//
		// }
	}

	@Override
	public List<GroupListVO> getOneMyGroup(Integer memno) {
		List<GroupListVO> list = new ArrayList<GroupListVO>();
		GroupListVO GroupListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MY_GROUP);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				GroupListVO = new GroupListVO();
				GroupListVO.setGroup_no(rs.getInt("group_no"));
				list.add(GroupListVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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

	@Override
	public Integer getCountMyGroup(Integer memno) {
		Integer count = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_MY_GROUP);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return count;

	}

}