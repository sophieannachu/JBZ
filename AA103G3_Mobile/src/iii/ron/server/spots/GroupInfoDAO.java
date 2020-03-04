package iii.ron.server.spots;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;

public class GroupInfoDAO implements GroupInfoDAO_interface {
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
		
	}
	
	private static final String GET_ALL_STMT ="SELECT * FROM GROUP_INFO ORDER BY GROUP_NO";
//	private static final String GET_ALL_STMT = "SELECT group_no, memno, group_name, group_loc, group_detail, group_count, "
//			+ "to_char(group_time,'yyyy/mm/dd hh:mi'), group_long, group_lati FROM GROUP_INFO order by group_no";
	private static final String INSERT_STMT = "INSERT INTO GROUP_INFO (group_no,memno,group_name,group_loc,group_detail,group_count,"
			+ "group_check,group_photo)" + "VALUES (GROUP_INFO_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT_PIC = "INSERT INTO GROUP_INFO (group_no,"
			+ "group_photo)" + "VALUES (GROUP_INFO_seq.NEXTVAL, ? )";
	private static final String GET_ONE_STMT = "select * From GROUP_INFO where memno = ?";
	private static final String GET_ONE_IMG = "SELECT group_photo FROM GROUP_INFO WHERE group_no =?";
	private static final String GET_ONEGROUP_STMT = "select * From GROUP_INFO where group_no = ?";
	private static final String GET_JOIN_STMT = "select a.group_no, a.memno, a.group_name, a.group_loc, a.group_detail, a.group_time, a.group_long, a.group_lati "
			+ "FROM Group_Info a JOIN Group_List b ON a.group_no = b.group_no where b.memno=?";
	
	// private static final String GET_ONE_STMT =
	// "SELECT fd_no,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,"
	// +"fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal"
	// +" FROM FD_FOOD where fd_no = ?";
	private static final String DELETE = "DELETE FROM GROUP_INFO where group_no = ?";
	// private static final String UPDATE =
	// "UPDATE FD_FOOD set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=?
	// where empno = ?";
	private static final String UPDATE_STMT = "UPDATE GROUP_INFO set memno=?,group_name=?,group_loc=?,group_detail=?,"
			+ "group_count=?,group_check=?,group_photo=?" + " where group_no=?";

//	@Override
//	public int insert(GroupInfoVO groupInfoVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		int count;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setLong(1, groupInfoVO.getMemno());
//			pstmt.setString(2, groupInfoVO.getGroup_name());
//			pstmt.setString(3, groupInfoVO.getGroup_loc());
//			pstmt.setString(4, groupInfoVO.getGroup_detail());
//			pstmt.setLong(5, groupInfoVO.getGroup_count());
//			pstmt.setLong(6, groupInfoVO.getGroup_check());
//			pstmt.setBytes(7, groupInfoVO.getGroup_photo());
//			pstmt.setTimestamp(8, groupInfoVO.getGroup_time());
//			pstmt.setString(9, groupInfoVO.getGroup_long());
//			pstmt.setString(10, groupInfoVO.getGroup_lati());
//			count=pstmt.executeUpdate();
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
//		return count;
//
//	}

	@Override
	public int delete(int group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, group_no);
			count = pstmt.executeUpdate();
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
return count;
		

	}

	@Override
	public List<GroupInfoVO> findByForeignKey(int memno) {
		List<GroupInfoVO> list = new ArrayList<GroupInfoVO>();
		GroupInfoVO groupInfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_JOIN_STMT);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupInfoVO = new GroupInfoVO();
				groupInfoVO.setGroup_no(rs.getInt("group_no"));
				groupInfoVO.setMemno(rs.getInt("memno"));
				groupInfoVO.setGroup_name(rs.getString("group_name"));
				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
//				groupInfoVO.setGroup_count(rs.getInt("group_count"));
//				groupInfoVO.setGroup_check(rs.getInt("group_check"));
//				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setGroup_long(rs.getString("group_long"));
				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
				list.add(groupInfoVO);
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
//				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
				groupInfoVO.setGroup_long(rs.getString("group_long"));
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
//	public void insertOne(String groupno,byte[] groupInfoPic) {
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
//
	@Override
	public byte[] getImg(int group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] buf =null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_IMG);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				buf = rs.getBytes("group_photo");
			}

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
//
//	@Override
//	public GroupInfoVO getOneGroup(int group_no) {
//		GroupInfoVO groupInfoVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONEGROUP_STMT);
//			pstmt.setInt(1, group_no);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				groupInfoVO = new GroupInfoVO();
//				groupInfoVO.setGroup_no(rs.getInt("group_no"));
//				groupInfoVO.setMemno(rs.getInt("memno"));
//				groupInfoVO.setGroup_name(rs.getString("group_name"));
//				groupInfoVO.setGroup_loc(rs.getString("group_loc"));
//				groupInfoVO.setGroup_detail(rs.getString("group_detail"));
//				groupInfoVO.setGroup_count(rs.getInt("group_count"));
//				groupInfoVO.setGroup_check(rs.getInt("group_check"));
//				groupInfoVO.setGroup_photo(rs.getBytes("group_photo"));
//				groupInfoVO.setGroup_time(rs.getTimestamp("group_time"));
//				groupInfoVO.setGroup_long(rs.getString("group_long"));
//				groupInfoVO.setGroup_lati(rs.getString("group_lati"));
//			}			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return groupInfoVO;
//	}
//
}

