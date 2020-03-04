package com.groupinfo.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupList.model.GroupListService;
import com.groupList.model.GroupListVO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;

public class GroupInfoDAO implements GroupInfoDAO_interface {
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
//			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JBZDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
		
	}
	

	private static final String GET_ALL_STMT = "SELECT * FROM GROUP_INFO order by GROUP_NO";
	private static final String INSERT_STMT = "INSERT INTO GROUP_INFO (group_no,memno,group_name,group_loc,group_detail,group_count,"
			+ "group_check,group_photo,group_time,group_long,group_lati)" + "VALUES (GROUP_INFO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String INSERT_STMT_PIC = "INSERT INTO GROUP_INFO (group_no,"
			+ "group_photo)" + "VALUES (GROUP_INFO_seq.NEXTVAL, ? )";
	private static final String GET_ONE_STMT = "select * From GROUP_INFO where group_no = ?";
	private static final String GET_ONE_IMG = "SELECT group_photo FROM GROUP_INFO WHERE group_no =?";
//	private static final String GET_FIVE_NEWS_GROUP = "SELECT group_no,group_name,group_loc,group_detail,group_count,group_time,memno,group_check FROM GROUP_INFO where MEMNO <> ? order by group_time desc";
	private static final String GET_FIVE_NEWS_GROUP = "SELECT group_no,group_name,group_loc,group_detail,group_count,group_time,memno,group_check FROM GROUP_INFO where MEMNO  <> ?  and  group_no  not in (select group_no from group_list where memno = ? group by group_no)";
	

	// private static final String GET_ONE_STMT =
	// "SELECT fd_no,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,"
	// +"fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal"
	// +" FROM FD_FOOD where fd_no = ?";
	//
	private static final String DELETE = "DELETE FROM GROUP_INFO where group_no = ?";

	// private static final String UPDATE =
	// "UPDATE FD_FOOD set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=?
	// where empno = ?";
	private static final String UPDATE_STMT = "UPDATE GROUP_INFO set memno=?,group_name=?,group_loc=?,group_detail=?,"
			+ "group_count=?,group_check=?,group_photo=?,group_time=?,group_long=?,group_lati=?" + " where group_no=?";
	private static final String UPDATE_STMT_ITEM = "UPDATE GROUP_INFO set group_name=?,group_detail=?,"
			+ "group_photo=?,group_time=?" + " where group_no=?";
	private static final String UPDATE_EDIT_GROUPOK_ITEM = "UPDATE GROUP_INFO set group_check=?"
			+ " where group_no=?";
	
	private static final String GET_COUNT_GROUP = "SELECT count(*)  FROM GROUP_INFO";

	@Override
	public void insert(GroupInfoVO groupInfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String cols[] = { "group_no" };
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setLong(1, groupInfoVO.getMemno());
			pstmt.setString(2, groupInfoVO.getGroup_name());
			pstmt.setString(3, groupInfoVO.getGroup_loc());
			pstmt.setString(4, groupInfoVO.getGroup_detail());
			pstmt.setLong(5, groupInfoVO.getGroup_count());
			pstmt.setLong(6, groupInfoVO.getGroup_check());
			pstmt.setBytes(7, groupInfoVO.getGroup_photo());
			pstmt.setTimestamp(8, groupInfoVO.getGroup_time());
			pstmt.setString(9, groupInfoVO.getGroup_long());
			pstmt.setString(10, groupInfoVO.getGroup_lati());
			pstmt.executeUpdate();
			
			ResultSet rsKeys = pstmt.getGeneratedKeys();
			rsKeys.next();
			Integer key = rsKeys.getInt(1);
			
			GroupListService groupListSvc = new GroupListService();
			groupListSvc.addGroupList(key,groupInfoVO.getMemno());

			// Handle any driver errors
		}  catch (SQLException se) {
			
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
	public void update(GroupInfoVO groupInfoVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setLong(1, groupInfoVo.getMemno());
			pstmt.setString(2, groupInfoVo.getGroup_name());
			pstmt.setString(3, groupInfoVo.getGroup_loc());
			pstmt.setString(4, groupInfoVo.getGroup_detail());
			pstmt.setLong(5, groupInfoVo.getGroup_count());
			pstmt.setLong(6, groupInfoVo.getGroup_check());
			pstmt.setBytes(7, groupInfoVo.getGroup_photo());
			pstmt.setTimestamp(8, groupInfoVo.getGroup_time());
			pstmt.setString(9, groupInfoVo.getGroup_long());
			pstmt.setString(10, groupInfoVo.getGroup_lati());
			pstmt.setInt(11, groupInfoVo.getGroup_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(Integer groupno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, groupno);
			pstmt.executeUpdate();
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public GroupInfoVO findByPrimaryKey(Integer groupno) {
		GroupInfoVO groupInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, groupno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupInfoVO = new GroupInfoVO();
				groupInfoVO.setGroup_no(rs.getInt("group_no"));
				groupInfoVO.setMemno(rs.getInt("memno"));
				groupInfoVO.setGroup_name(rs.getString("group_name"));
				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
				groupInfoVO.setGroup_count(rs.getInt("group_count"));
				groupInfoVO.setGroup_check(rs.getInt("group_check"));
				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setGroup_long(rs.getString("group_long"));
				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
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
		return groupInfoVO;
	}

	@Override
	public List<GroupInfoVO> getAll() {
		List<GroupInfoVO> list = new ArrayList<GroupInfoVO>();
		// FoodVO empVO = null;
		//GroupInfoVO
		GroupInfoVO groupInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groupInfoVO = new GroupInfoVO();
				groupInfoVO.setGroup_no(rs.getInt("group_no"));
				groupInfoVO.setGroup_name(rs.getString("group_name"));
				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
				groupInfoVO.setGroup_count(rs.getInt("group_count"));
				groupInfoVO.setGroup_check(rs.getInt("group_check"));
				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setGroup_lati(rs.getString("group_long"));
				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
				groupInfoVO.setMemno(rs.getInt("memno"));
				list.add(groupInfoVO); // Store the row in the list

			}

			// Handle any driver errors
		}catch (SQLException se) {
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


	public static void main(String[] args) throws Exception{

		GroupInfoDAO dao = new GroupInfoDAO();

		// 新增
		GroupInfoVO groupInfoVO = new GroupInfoVO();

//		InputStream in = new FileInputStream("C:/pic/1.jpg");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		in.close();
//		
//		
//		 groupInfoVO.setMemno(1201);
//		 groupInfoVO.setGroup_name("男人幫1");
//		 groupInfoVO.setGroup_loc("地點");
//		 groupInfoVO.setGroup_detail("祥係內容");
//		 groupInfoVO.setGroup_count(120);
//		 groupInfoVO.setGroup_check(1);
//		 groupInfoVO.setGroup_photo(buf);
//		 dao.insert(groupInfoVO);

		// 修改

//		 groupInfoVO.setGroup_no(9);
//		 groupInfoVO.setMemno(1201);
//		 groupInfoVO.setGroup_name("女人人幫1");
//		 groupInfoVO.setGroup_loc("地點");
//		 groupInfoVO.setGroup_detail("祥係內容");
//		 groupInfoVO.setGroup_count(120);
//		 groupInfoVO.setGroup_check(1);
//		 groupInfoVO.setGroup_photo(null);
//		
//		 dao.update(groupInfoVO);

		// 刪除
		// dao.delete(9);

		// 查詢
//		 GroupInfoVO groupInfoFkVO = dao.findByPrimaryKey(8);
//		 System.out.print(groupInfoFkVO.getGroup_no()+ ",");
//		 System.out.print(groupInfoFkVO.getMemno()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_name()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_loc()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_detail()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_count()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_check()+ ",");
//		 System.out.print(groupInfoFkVO.getGroup_photo()+ ",");
		// System.out.println("---------------------");

		// 查詢
//		List<GroupInfoVO> list = dao.getAll();
//		for (GroupInfoVO aGroupInfo : list) {
//			System.out.print(aGroupInfo.getGroup_name());
//			System.out.print(aGroupInfo.getGroup_loc());
//			System.out.println();
//
//		}
	}

//	@Override
//	public void insertOne(GroupInfoVO groupInfoVoc) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT_PIC);
//			pstmt.setBytes(1, groupInfoPic);
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		}  catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//	}

	@Override
	public byte[] getImg(Integer groupno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] buf =null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_IMG);
			pstmt.setInt(1, groupno);
			rs = pstmt.executeQuery();
			rs.next();
			buf = rs.getBytes("group_photo");

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return buf;
		
	}

	@Override
	public List<GroupInfoVO> getOneNewsGroup(Integer memno) {
		System.out.println("memno::::"+memno);
		List<GroupInfoVO> list = new ArrayList<GroupInfoVO>();
		GroupInfoVO groupInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FIVE_NEWS_GROUP);
			pstmt.setInt(1, memno);
			pstmt.setInt(2, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groupInfoVO = new GroupInfoVO();
				groupInfoVO.setGroup_no(rs.getInt("group_no"));
				groupInfoVO.setGroup_name(rs.getString("group_name"));
				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
				groupInfoVO.setGroup_count(rs.getInt("group_count"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setMemno(rs.getInt("memno"));
				groupInfoVO.setGroup_check(rs.getInt("group_check"));
				list.add(groupInfoVO); // Store the row in the list

			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	public void update_item(GroupInfoVO groupInfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT_ITEM);
			pstmt.setString(1, groupInfoVO.getGroup_name());
			pstmt.setString(2, groupInfoVO.getGroup_detail());
			pstmt.setBytes(3, groupInfoVO.getGroup_photo());
			pstmt.setTimestamp(4, groupInfoVO.getGroup_time());
			pstmt.setInt(5, groupInfoVO.getGroup_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void update_EditGroupOK( GroupInfoVO groupInfoVO ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EDIT_GROUPOK_ITEM);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, groupInfoVO.getGroup_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public Integer getCountGroup() {
		Integer count = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_GROUP);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				count = rs.getInt(1);
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	
//	@Override
//	public void getOneMyGroupName(Integer groupno) {
//		List<GroupInfoVO> list = new ArrayList<GroupInfoVO>();
//		// FoodVO empVO = null;
//		//GroupInfoVO
//		GroupInfoVO groupInfoVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				groupInfoVO = new GroupInfoVO();
//				groupInfoVO.setGroup_no(rs.getInt("group_no"));
//				groupInfoVO.setGroup_name(rs.getString("group_name"));
//				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
//				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
//				groupInfoVO.setGroup_count(rs.getInt("group_count"));
//				groupInfoVO.setGroup_check(rs.getInt("group_check"));
//				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
//				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
//				groupInfoVO.setGroup_lati(rs.getString("group_long"));
//				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
//				groupInfoVO.setMemno(rs.getInt("memno"));
//				list.add(groupInfoVO); // Store the row in the list
//			}
//			// Handle any driver errors
//		}  catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	

}

