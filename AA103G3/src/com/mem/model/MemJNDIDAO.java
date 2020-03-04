package com.mem.model;

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



public class MemJNDIDAO implements MemDAO_interface{
	private static DataSource ds=null;
	
	static{
		try {
			Context context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT="INSERT INTO mem (memno, id, acc, pwd, name, sex, bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood, sugar, oil, bone, breathe, heart, stom, photo) VALUES (mem_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE="UPDATE mem SET id=?, acc=?, pwd=?, name=?, sex=?, bir=?, height=?, weight=?, phone=?, email=?, act_type=?, sos1=?, sos2=?, sos3=?, blood=?, sugar=?, oil=?, bone=?, breathe=?, heart=?, stom=?, photo=? WHERE memno=?";
	private static final String UPDATE_PHOTO="UPDATE mem SET photo=? WHERE memno=?";
	private static final String DELETE="DELETE FROM mem WHERE memno=?";
	private static final String SELECT_BYACC_ONE="SELECT memno, id, acc, pwd, name, sex, bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood, sugar, oil, bone, breathe, heart, stom, photo,cpt FROM mem WHERE acc=?";
	private static final String SELECT_ONE="SELECT memno, id, acc, pwd, name, sex, bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood, sugar, oil, bone, breathe, heart, stom, photo, cpt FROM mem WHERE memno=?";
	private static final String SELECT_ALL="SELECT memno, id, acc, pwd, name, sex, bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood, sugar, oil, bone, breathe, heart, stom, photo FROM mem ORDER BY memno";
	private static final String SELECT_IMG="SELECT photo FROM mem WHERE memno=?";
	private static final String UPDATE_CPT="UPDATE mem SET cpt=? WHERE memno=?";

	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memVO.getId());
			pstmt.setString(2, memVO.getAcc());
			pstmt.setString(3, memVO.getPwd());
			pstmt.setString(4, memVO.getName());
			pstmt.setString(5, memVO.getSex());
			pstmt.setDate(6, memVO.getBir());
			pstmt.setInt(7, memVO.getHeight());
			pstmt.setInt(8, memVO.getWeight());
			pstmt.setString(9, memVO.getPhone());
			pstmt.setString(10, memVO.getEmail());
			pstmt.setString(11, memVO.getAct_type());
			pstmt.setString(12, memVO.getSos1());
			pstmt.setString(13, memVO.getSos2());
			pstmt.setString(14, memVO.getSos3());
			pstmt.setString(15, memVO.getBlood());
			pstmt.setString(16, memVO.getSugar());
			pstmt.setString(17, memVO.getOil());
			pstmt.setString(18, memVO.getBone());
			pstmt.setString(19, memVO.getBreathe());
			pstmt.setString(20, memVO.getHeart());
			pstmt.setString(21, memVO.getStom());
			pstmt.setBytes(22, memVO.getPhoto());
			
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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getId());
			pstmt.setString(2, memVO.getAcc());
			pstmt.setString(3, memVO.getPwd());
			pstmt.setString(4, memVO.getName());
			pstmt.setString(5, memVO.getSex());
			pstmt.setDate(6, memVO.getBir());
			pstmt.setInt(7, memVO.getHeight());
			pstmt.setInt(8, memVO.getWeight());
			pstmt.setString(9, memVO.getPhone());
			pstmt.setString(10, memVO.getEmail());
			pstmt.setString(11, memVO.getAct_type());
			pstmt.setString(12, memVO.getSos1());
			pstmt.setString(13, memVO.getSos2());
			pstmt.setString(14, memVO.getSos3());
			pstmt.setString(15, memVO.getBlood());
			pstmt.setString(16, memVO.getSugar());
			pstmt.setString(17, memVO.getOil());
			pstmt.setString(18, memVO.getBone());
			pstmt.setString(19, memVO.getBreathe());
			pstmt.setString(20, memVO.getHeart());
			pstmt.setString(21, memVO.getStom());
			pstmt.setBytes(22, memVO.getPhoto());
			pstmt.setInt(23, memVO.getMemno());

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
	public void delete(Integer memno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

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
	public MemVO findByPrimaryKey(Integer memno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setId(rs.getString("id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBir(rs.getDate("bir"));
				memVO.setHeight(rs.getInt("height"));
				memVO.setWeight(rs.getInt("weight"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAct_type(rs.getString("act_type"));
				memVO.setSos1(rs.getString("sos1"));
				memVO.setSos2(rs.getString("sos2"));
				memVO.setSos3(rs.getString("sos3"));
				memVO.setBlood(rs.getString("blood"));
				memVO.setSugar(rs.getString("sugar"));
				memVO.setOil(rs.getString("oil"));
				memVO.setBone(rs.getString("bone"));
				memVO.setBreathe(rs.getString("breathe"));
				memVO.setHeart(rs.getString("heart"));
				memVO.setStom(rs.getString("stom"));
				memVO.setPhoto(rs.getBytes("Photo"));
				memVO.setCpt(rs.getString("cpt"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemVO> list = new ArrayList();
		MemVO memVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setId(rs.getString("id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBir(rs.getDate("bir"));
				memVO.setHeight(rs.getInt("height"));
				memVO.setWeight(rs.getInt("weight"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAct_type(rs.getString("act_type"));
				memVO.setSos1(rs.getString("sos1"));
				memVO.setSos2(rs.getString("sos2"));
				memVO.setSos3(rs.getString("sos3"));
				memVO.setBlood(rs.getString("blood"));
				memVO.setSugar(rs.getString("sugar"));
				memVO.setOil(rs.getString("oil"));
				memVO.setBone(rs.getString("bone"));
				memVO.setBreathe(rs.getString("breathe"));
				memVO.setHeart(rs.getString("heart"));
				memVO.setStom(rs.getString("stom"));
				memVO.setPhoto(rs.getBytes("Photo"));
				list.add(memVO);
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
	public byte[] getImg(Integer memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] photo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_IMG);

			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				photo=rs.getBytes("photo");
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
	public MemVO findByAcc(String acc) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BYACC_ONE);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setId(rs.getString("id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPwd(rs.getString("pwd"));
				memVO.setName(rs.getString("name"));
				memVO.setSex(rs.getString("sex"));
				memVO.setBir(rs.getDate("bir"));
				memVO.setHeight(rs.getInt("height"));
				memVO.setWeight(rs.getInt("weight"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("email"));
				memVO.setAct_type(rs.getString("act_type"));
				memVO.setSos1(rs.getString("sos1"));
				memVO.setSos2(rs.getString("sos2"));
				memVO.setSos3(rs.getString("sos3"));
				memVO.setBlood(rs.getString("blood"));
				memVO.setSugar(rs.getString("sugar"));
				memVO.setOil(rs.getString("oil"));
				memVO.setBone(rs.getString("bone"));
				memVO.setBreathe(rs.getString("breathe"));
				memVO.setHeart(rs.getString("heart"));
				memVO.setStom(rs.getString("stom"));
				memVO.setPhoto(rs.getBytes("Photo"));
				memVO.setCpt(rs.getString("cpt"));
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
		return memVO;
	}

	@Override
	public List<MemVO> findByKeyPoint(String keyWord) {
		// TODO Auto-generated method stub
		String query = "SELECT memno ,name from mem where name like '%"+ keyWord
				+ "%' order by memno";	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;
		List<MemVO> list = new ArrayList<MemVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setName(rs.getString("name"));
				list.add(memVO);
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
	public void updatePhoto(Integer memno,byte[] photo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PHOTO);

			pstmt.setBytes(1, photo);
			pstmt.setInt(2,memno );


			pstmt.executeUpdate();
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
	public void updatecpt(Integer memno,String cpt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CPT);

			pstmt.setString(1, cpt);
			pstmt.setInt(2,memno );


			pstmt.executeUpdate();
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
