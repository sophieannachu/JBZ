package com.foodlist.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemVO;

import java.sql.*;

public class FoodDAO implements FoodDAO_interface {
	
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

	private static final String GET_ALL_STMT = "SELECT * FROM FD_FOOD order by FD_NO";
	private static final String INSERT_STMT = "INSERT INTO FD_FOOD (fd_no,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,"
			+ "					fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal) "
			+ "					VALUES (fd_food_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "					 ?, ?, ?, ?, ?, ?, ?)";

	// private static final String GET_ALL_STMT =
	// "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd')
	// hiredate,sal,comm,deptno FROM emp2 order by empno";
	private static final String GET_ONE_STMT = "select * From FD_FOOD where fd_no = ?";
	private static final String GET_ONE_NAME = "select fd_name From FD_FOOD where fd_name like = '?'";

	// private static final String GET_ONE_STMT =
	// "SELECT fd_no,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,"
	// +"fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal"
	// +" FROM FD_FOOD where fd_no = ?";
	//
	private static final String DELETE = "DELETE FROM FD_FOOD where fd_no = ?";
//	private static final String GETKEYWORD = "select * From FD_FOOD where fd_name like ? order by fd_no";
	
	// private static final String UPDATE =
	// "UPDATE FD_FOOD set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=?
	// where empno = ?";
	private static final String UPDATE_STMT = "UPDATE FD_FOOD set fd_name=?,fd_spone=?,fd_sptwo=?,fd_spthr=?,fd_spfor=?,fd_spfir=?,"
			+ "fd_spsix=?,prot=?,fat=?,mono=?,poly=?,sfa=?,trans=?,cho=?,carb=?,sugar=?,df=?,na=?,ca=?,k=?,cal=? where fd_no=?";

	@Override
	public void insert(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, foodVO.getFd_name());
			pstmt.setInt(2, foodVO.getFd_spone());
			pstmt.setInt(3, foodVO.getFd_sptwo());
			pstmt.setInt(4, foodVO.getFd_spthr());
			pstmt.setInt(5, foodVO.getFd_spfor());
			pstmt.setInt(6, foodVO.getFd_spfir());
			pstmt.setInt(7, foodVO.getFd_spsix());
			pstmt.setInt(8, foodVO.getProt());
			pstmt.setInt(9, foodVO.getFat());
			pstmt.setInt(10, foodVO.getMono());
			pstmt.setInt(11, foodVO.getPoly());
			pstmt.setInt(12, foodVO.getSfa());
			pstmt.setInt(13, foodVO.getTrans());
			pstmt.setInt(14, foodVO.getCho());
			pstmt.setInt(15, foodVO.getCarb());
			pstmt.setInt(16, foodVO.getSugar());
			pstmt.setInt(17, foodVO.getDf());
			pstmt.setInt(18, foodVO.getNa());
			pstmt.setInt(19, foodVO.getCa());
			pstmt.setInt(20, foodVO.getK());
			pstmt.setInt(21, foodVO.getCal());
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
	public void update(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, foodVO.getFd_name());
			pstmt.setInt(2, foodVO.getFd_spone());
			pstmt.setInt(3, foodVO.getFd_sptwo());
			pstmt.setInt(4, foodVO.getFd_spthr());
			pstmt.setInt(5, foodVO.getFd_spfor());
			pstmt.setInt(6, foodVO.getFd_spfir());
			pstmt.setInt(7, foodVO.getFd_spsix());
			pstmt.setInt(8, foodVO.getProt());
			pstmt.setInt(9, foodVO.getFat());
			pstmt.setInt(10, foodVO.getMono());
			pstmt.setInt(11, foodVO.getPoly());
			pstmt.setInt(12, foodVO.getSfa());
			pstmt.setInt(13, foodVO.getTrans());
			pstmt.setInt(14, foodVO.getCho());
			pstmt.setInt(15, foodVO.getCarb());
			pstmt.setInt(16, foodVO.getSugar());
			pstmt.setInt(17, foodVO.getDf());
			pstmt.setInt(18, foodVO.getNa());
			pstmt.setInt(19, foodVO.getCa());
			pstmt.setInt(20, foodVO.getK());
			pstmt.setInt(21, foodVO.getCal());
			pstmt.setInt(22, foodVO.getFd_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void delete(Integer fd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, fd_no);
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

	// @Override
	public FoodVO findByPrimaryKey(Integer fd_no) {

		FoodVO foodVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, fd_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				foodVO1 = new FoodVO();
				foodVO1.setFd_no(rs.getInt("fd_no"));
				foodVO1.setFd_name(rs.getString("fd_name"));
				foodVO1.setFd_spone(rs.getInt("fd_spone"));
				foodVO1.setFd_sptwo(rs.getInt("fd_sptwo"));
				foodVO1.setFd_spthr(rs.getInt("fd_spthr"));
				foodVO1.setFd_spfor(rs.getInt("fd_spfor"));
				foodVO1.setFd_spfir(rs.getInt("fd_spfir"));
				foodVO1.setFd_spsix(rs.getInt("fd_spsix"));
				foodVO1.setProt(rs.getInt("fd_spfir"));
				foodVO1.setFat(rs.getInt("fd_spsix"));
				foodVO1.setMono(rs.getInt("prot"));
				foodVO1.setPoly(rs.getInt("fat"));
				foodVO1.setSfa(rs.getInt("mono"));
				foodVO1.setTrans(rs.getInt("poly"));
				foodVO1.setCho(rs.getInt("sfa"));
				foodVO1.setCarb(rs.getInt("trans"));
				foodVO1.setSugar(rs.getInt("cho"));
				foodVO1.setDf(rs.getInt("carb"));
				foodVO1.setNa(rs.getInt("sugar"));
				foodVO1.setCa(rs.getInt("df"));
				foodVO1.setK(rs.getInt("na"));
				foodVO1.setK(rs.getInt("ca"));
				foodVO1.setK(rs.getInt("k"));
				foodVO1.setCal(rs.getInt("cal"));
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return foodVO1;
	}

	@Override
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		// FoodVO empVO = null;
		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				foodVO = new FoodVO();
				foodVO.setFd_name(rs.getString("fd_name"));
				foodVO.setFd_no(rs.getInt("fd_no"));
				foodVO.setFd_spone(rs.getInt("fd_spone"));
				foodVO.setFd_sptwo(rs.getInt("fd_sptwo"));
				foodVO.setFd_spthr(rs.getInt("fd_spthr"));
				foodVO.setFd_spfor(rs.getInt("fd_spfor"));
				foodVO.setFd_spfir(rs.getInt("fd_spfir"));
				foodVO.setFd_spsix(rs.getInt("fd_spsix"));
				foodVO.setProt(rs.getInt("prot"));
				foodVO.setFat(rs.getInt("fat"));
				foodVO.setMono(rs.getInt("mono"));
				foodVO.setPoly(rs.getInt("poly"));
				foodVO.setSfa(rs.getInt("sfa"));
				foodVO.setTrans(rs.getInt("trans"));
				foodVO.setCho(rs.getInt("cho"));
				foodVO.setCarb(rs.getInt("carb"));
				foodVO.setSugar(rs.getInt("sugar"));
				foodVO.setDf(rs.getInt("df"));
				foodVO.setNa(rs.getInt("na"));
				foodVO.setCa(rs.getInt("ca"));
				foodVO.setK(rs.getInt("k"));
				foodVO.setCal(rs.getInt("cal"));
				list.add(foodVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<FoodVO> findByKeyWord(String fd_name) {

		List<FoodVO> list3 = new ArrayList<FoodVO>();
		FoodVO foodVO5 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "select * From FD_FOOD where fd_name like '%"+fd_name+"%' order by fd_no";
			pstmt = con.prepareStatement(query);
		
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
			
				foodVO5 = new FoodVO();
				foodVO5.setFd_no(rs.getInt("fd_no"));
				foodVO5.setFd_name(rs.getString("fd_name"));
				foodVO5.setFd_spone(rs.getInt("fd_spone"));
				foodVO5.setFd_sptwo(rs.getInt("fd_sptwo"));
				foodVO5.setFd_spthr(rs.getInt("fd_spthr"));
				foodVO5.setFd_spsix(rs.getInt("fd_spfor"));
				foodVO5.setProt(rs.getInt("fd_spfir"));
				foodVO5.setFat(rs.getInt("fd_spsix"));
				foodVO5.setMono(rs.getInt("prot"));
				foodVO5.setPoly(rs.getInt("fat"));
				foodVO5.setSfa(rs.getInt("mono"));
				foodVO5.setTrans(rs.getInt("poly"));
				foodVO5.setCho(rs.getInt("sfa"));
				foodVO5.setCarb(rs.getInt("trans"));
				foodVO5.setSugar(rs.getInt("cho"));
				foodVO5.setDf(rs.getInt("carb"));
				foodVO5.setNa(rs.getInt("sugar"));
				foodVO5.setCa(rs.getInt("df"));
				foodVO5.setK(rs.getInt("na"));
				foodVO5.setK(rs.getInt("ca"));
				foodVO5.setK(rs.getInt("k"));
				foodVO5.setCal(rs.getInt("cal"));
				list3.add(foodVO5);
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return list3;
	}
//
//	public static void main(String[] args) {
//
//		FoodDAO dao = new FoodDAO();
//
//		// 新增
//		FoodVO foodVO1 = new FoodVO();
//		 foodVO1.setFd_name("紅心辣椒");
//		 foodVO1.setFd_spone(1500);
//		 foodVO1.setFd_sptwo(1500);
//		 foodVO1.setFd_spthr(1500);
//		 foodVO1.setFd_spfor(1500);
//		 foodVO1.setFd_spfir(1500);
//		 foodVO1.setFd_spsix(1500);
//		 foodVO1.setProt(1500);
//		 foodVO1.setFat(1500);
//		 foodVO1.setMono(1500);
//		 foodVO1.setPoly(1500);
//		 foodVO1.setSfa(1500);
//		 foodVO1.setTrans(1500);
//		 foodVO1.setCho(1500);
//		 foodVO1.setCarb(1500);
//		 foodVO1.setSugar(1500);
//		 foodVO1.setDf(1500);
//		 foodVO1.setNa(1500);
//		 foodVO1.setCa(1500);
//		 foodVO1.setK(1500);
//		 foodVO1.setCal(20);
//		
//		 dao.insert(foodVO1);

	@Override
	public List<FoodVO> findByKeyPoint(String keyWord) {
		String query = "SELECT fd_no ,fd_name from fd_food where name like '%"+ keyWord
				+ "%' order by fd_no";	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FoodVO foodVO = null;
		List<FoodVO> list = new ArrayList<FoodVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				foodVO = new FoodVO();
				foodVO.setFd_no(rs.getInt("fd_no"));
				foodVO.setFd_name(rs.getString("fd_name"));
				list.add(foodVO);
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
	public FoodVO findByFood_Name(String fd_name) {

		FoodVO foodVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			String	query = "select fd_name From FD_FOOD where fd_name like '"+fd_name+"'";
			System.out.println(query);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				foodVO1 = new FoodVO();	
				foodVO1.setFd_name(rs.getString("fd_name"));
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return foodVO1;
	}
	}

		// 修改
		// FoodVO empVO2 = new FoodVO();
		// foodVO1.setFd_no(12);
		// foodVO1.setFd_name("紅心辣椒");
		// foodVO1.setFd_spone(1500);
		// foodVO1.setFd_sptwo(1500);
		// foodVO1.setFd_spthr(1500);
		// foodVO1.setFd_spfor(1500);
		// foodVO1.setFd_spfir(1500);
		// foodVO1.setFd_spsix(1500);
		// foodVO1.setProt(1500);
		// foodVO1.setFat(1500);
		// foodVO1.setMono(1500);
		// foodVO1.setPoly(1500);
		// foodVO1.setSfa(1500);
		// foodVO1.setTrans(1500);
		// foodVO1.setCho(1500);
		// foodVO1.setCarb(1500);
		// foodVO1.setSugar(1500);
		// foodVO1.setDf(1500);
		// foodVO1.setNa(1500);
		// foodVO1.setCa(1500);
		// foodVO1.setK(1500);
		// foodVO1.setCal(20.5);
		//
		// dao.update(foodVO1);

		// 刪除
		//dao.delete(12);

		// 查詢
		// FoodVO foodVO3 = dao.findByPrimaryKey(12);
		// System.out.print(foodVO1.getFd_no() + ",");
		// System.out.print(foodVO3.getFd_name() + ",");
		// System.out.print(foodVO3.getFd_spone() + ",");
		// System.out.print(foodVO3.getFd_sptwo()+ ",");
		// System.out.print(foodVO3.getFd_spthr() + ",");
		// System.out.print(foodVO3.getFd_spfor() + ",");
		// System.out.print(foodVO3.getFd_spfir() + ",");
		// System.out.print(foodVO3.getFd_spsix() + ",");
		// System.out.print(foodVO3.getProt() + ",");
		// System.out.print(foodVO3.getFat() + ",");
		// System.out.print(foodVO3.getMono() + ",");
		// System.out.print(foodVO3.getPoly() + ",");
		// System.out.print(foodVO3.getSfa() + ",");
		// System.out.print(foodVO3.getTrans() + ",");
		// System.out.print(foodVO3.getCho() + ",");
		// System.out.print(foodVO3.getCarb() + ",");
		// System.out.print(foodVO3.getSugar() + ",");
		// System.out.print(foodVO3.getDf() + ",");
		// System.out.print(foodVO3.getNa() + ",");
		// System.out.print(foodVO3.getCa() + ",");
		// System.out.print(foodVO3.getK() + ",");
		// System.out.print(foodVO3.getCal() + ",");
		// System.out.println("---------------------");

		// 查詢
//		 List<FoodVO> list = dao.getAll();
//		 for (FoodVO aEmp : list) {
//			 System.out.print(aEmp.getFd_name());
//			 System.out.print(aEmp.getFd_spone());
//			 System.out.println();
//		 }
		// 查詢
//				 FoodVO foodVO3 = dao.findByKeyWord("冬");
//				 System.out.print(foodVO3.getFd_no() + ",");
//				 System.out.print(foodVO3.getFd_name() + ",");
//				 System.out.print(foodVO3.getFd_spone() + ",");
//				 System.out.print(foodVO3.getFd_sptwo()+ ",");
//				 System.out.print(foodVO3.getFd_spthr() + ",");
//				 System.out.print(foodVO3.getFd_spfor() + ",");
//				 System.out.print(foodVO3.getFd_spfir() + ",");
//				 System.out.print(foodVO3.getFd_spsix() + ",");
//				 System.out.print(foodVO3.getProt() + ",");
//				 System.out.print(foodVO3.getFat() + ",");
//				 System.out.print(foodVO3.getMono() + ",");
//				 System.out.print(foodVO3.getPoly() + ",");
//				 System.out.print(foodVO3.getSfa() + ",");
//				 System.out.print(foodVO3.getTrans() + ",");
//				 System.out.print(foodVO3.getCho() + ",");
//				 System.out.print(foodVO3.getCarb() + ",");
//				 System.out.print(foodVO3.getSugar() + ",");
//				 System.out.print(foodVO3.getDf() + ",");
//				 System.out.print(foodVO3.getNa() + ",");
//				 System.out.print(foodVO3.getCa() + ",");
//				 System.out.print(foodVO3.getK() + ",");
//				 System.out.print(foodVO3.getCal() + ",");
//				 System.out.println("---------------------");
//		
//	}
