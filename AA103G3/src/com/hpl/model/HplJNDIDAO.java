package com.hpl.model;

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

public class HplJNDIDAO implements HplDAO_interface{

	private static DataSource ds=null;
	
	static{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT="INSERT INTO hplist (hpno, memno, hpname, hpstdate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg, hpeddate) VALUES (hplist_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE="DELETE FROM hplist WHERE hpno=?";
	private static final String SELECT_ONE="SELECT hpno, memno, hpname, hpstdate, hpeddate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg FROM hplist WHERE hpno=?";
	private static final String SELECT_ALL_NOW="SELECT hpno, memno, hpname, hpstdate, hpeddate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg FROM hplist WHERE memno=? and trunc(hpeddate, 'DD') >= TRUNC(SYSDATE, 'DD') ORDER BY hpno";
	private static final String SELECT_ALL_PAST="SELECT hpno, memno, hpname, hpstdate, hpeddate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg FROM hplist WHERE memno=? and trunc(hpeddate, 'DD') < TRUNC(SYSDATE, 'DD') ORDER BY hpno";
//	private static final String SELECT_ALL="SELECT hpno, memno, hpname, hpdate, hpstep, hpexer, hpbmi, hpwaist, hpcal, hpbp, hpbg FROM hplist ORDER BY hpno";
	
	@Override
	public void insert(HplVO hplVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, hplVO.getMemno());
			pstmt.setString(2, hplVO.getHpname());
			pstmt.setDate(3, hplVO.getHpstdate());
			pstmt.setInt(4, hplVO.getHpstep());
			pstmt.setInt(5, hplVO.getHpexer());
			pstmt.setInt(6, hplVO.getHpbmi());
			pstmt.setDouble(7, hplVO.getHpwaist());
			pstmt.setInt(8, hplVO.getHpcal());
			pstmt.setInt(9, hplVO.getHpbp());
			pstmt.setInt(10, hplVO.getHpbg());
			pstmt.setDate(11, hplVO.getHpeddate());
			
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
	public void delete(Integer hpno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hpno);

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
	public HplVO getOne(Integer hpno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HplVO hplVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, hpno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				hplVO = new HplVO();
				hplVO.setHpno(rs.getInt("hpno"));
				hplVO.setMemno(rs.getInt("memno"));
				hplVO.setHpname(rs.getString("hpname"));
				hplVO.setHpstdate(rs.getDate("hpstdate"));
				hplVO.setHpeddate(rs.getDate("hpeddate"));
				hplVO.setHpstep(rs.getInt("hpstep"));
				hplVO.setHpexer(rs.getInt("hpexer"));
				hplVO.setHpbmi(rs.getInt("hpbmi"));
				hplVO.setHpwaist(rs.getDouble("hpwaist"));
				hplVO.setHpcal(rs.getInt("hpcal"));
				hplVO.setHpbp(rs.getInt("hpbp"));
				hplVO.setHpbg(rs.getInt("hpbg"));
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
		return hplVO;
	}



	@Override
	public List<HplVO> getAll_Now(Integer memno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HplVO> list = new ArrayList();
		HplVO hplVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_NOW);
			
			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hplVO = new HplVO();
				hplVO.setHpno(rs.getInt("hpno"));
				hplVO.setMemno(rs.getInt("memno"));
				hplVO.setHpname(rs.getString("hpname"));
				hplVO.setHpstdate(rs.getDate("hpstdate"));
				hplVO.setHpeddate(rs.getDate("hpeddate"));
				hplVO.setHpstep(rs.getInt("hpstep"));
				hplVO.setHpexer(rs.getInt("hpexer"));
				hplVO.setHpbmi(rs.getInt("hpbmi"));
				hplVO.setHpwaist(rs.getDouble("hpwaist"));
				hplVO.setHpcal(rs.getInt("hpcal"));
				hplVO.setHpbp(rs.getInt("hpbp"));
				hplVO.setHpbg(rs.getInt("hpbg"));
				list.add(hplVO);
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
	public List<HplVO> getAll_Past(Integer memno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HplVO> list = new ArrayList();
		HplVO hplVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_PAST);
			
			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hplVO = new HplVO();
				hplVO.setHpno(rs.getInt("hpno"));
				hplVO.setMemno(rs.getInt("memno"));
				hplVO.setHpname(rs.getString("hpname"));
				hplVO.setHpstdate(rs.getDate("hpstdate"));
				hplVO.setHpeddate(rs.getDate("hpeddate"));
				hplVO.setHpstep(rs.getInt("hpstep"));
				hplVO.setHpexer(rs.getInt("hpexer"));
				hplVO.setHpbmi(rs.getInt("hpbmi"));
				hplVO.setHpwaist(rs.getDouble("hpwaist"));
				hplVO.setHpcal(rs.getInt("hpcal"));
				hplVO.setHpbp(rs.getInt("hpbp"));
				hplVO.setHpbg(rs.getInt("hpbg"));
				list.add(hplVO);
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
