package idv.ron.server.mem;

import idv.ron.server.main.Common;
import java.sql.Timestamp;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemDaoOracleImpl implements MemDao 
{

	public MemDaoOracleImpl() 
	{
		super();
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String checkLogin(Mem mem)
	{
		final String check = "select pwd, memno from MEM where acc = ?";
		String feedback = "0";
		String password = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = con.prepareStatement(check);
			ps.setString(1, mem.getAcc());
			rs = ps.executeQuery();
			System.out.println("sssssssssss");
			if (rs.next()){
				password = rs.getString("pwd");
				
				if (password.equals(mem.getPwd())) 
				{
					feedback = rs.getString("memno");
				} else {
					feedback = "-1";
				}
			} 
			
		} catch (SQLException se) 
		{
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (rs != null) 
			{
				try 
				{
					rs.close();
				} 
				catch (SQLException se) 
				{
					se.printStackTrace(System.err);
				}
			}	
		}	
		System.out.println(feedback);
		return feedback;
	}
	
	@Override
	public int insert(Mem mem, byte[] photo) {
		int count = 0;
		String sql = "INSERT INTO MEM"
				+ "(memno, id,  acc, pwd, name, sex,"
				+ "bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood,"
				+ "sugar, oil, bone, breathe, heart, stom, cpt, regId, photo) "
				+ "VALUES (Mem_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getId());
			ps.setString(2, mem.getAcc());
			ps.setString(3, mem.getPwd());
			ps.setString(4, mem.getName());
			ps.setString(5, mem.getSex());
			ps.setTimestamp(6,mem.getBir());
			ps.setInt(7,mem.getHeight());
			ps.setInt(8,mem.getWeight());
			ps.setString(9,mem.getPhone());
			ps.setString(10,mem.getEmail());
			ps.setString(11,mem.getAct_type());
			ps.setString(12,mem.getSos1());
			ps.setString(13,mem.getSos2());
			ps.setString(14,mem.getSos3());
			ps.setString(15,mem.getBlood());
			ps.setString(16,mem.getSugar());
			ps.setString(17,mem.getOil());
			ps.setString(18,mem.getBone());
			ps.setString(19,mem.getBreathe());
			ps.setString(20,mem.getHeart());
			ps.setString(21,mem.getStom());
			ps.setString(22,mem.getCpt());
			ps.setString(23,mem.getRegId());
			ps.setBytes(24,photo);
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int update(Mem mem, byte[] photo) {
		int count = 0;
		String sql = "UPDATE MEM SET id = ?, acc = ?, pwd = ?, name = ?, sex = ?, bir = ?, height = ?,"
				+"weight = ?, phone = ?, email = ?, act_type = ?, sos1 = ?, sos2 = ?, sos3 = ?,"
				+ "blood = ?, sugar = ?, oil = ?, bone = ?, breathe = ?, heart = ?, stom = ?, cpt = ?, regid = ?, photo = ? WHERE id = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getId());
			ps.setString(2, mem.getAcc());
			ps.setString(3, mem.getPwd());
			ps.setString(4, mem.getName());
			ps.setString(5, mem.getSex());
			ps.setTimestamp(6, mem.getBir());
			ps.setInt(7,mem.getHeight());
			ps.setInt(8,mem.getWeight());
			ps.setString(9,mem.getPhone());
			ps.setString(10,mem.getEmail());
			ps.setString(11,mem.getAct_type());
			ps.setString(12,mem.getSos1());
			ps.setString(13,mem.getSos2());
			ps.setString(14,mem.getSos3());
			ps.setString(15,mem.getBlood());
			ps.setString(16,mem.getSugar());
			ps.setString(17,mem.getOil());
			ps.setString(18,mem.getBone());
			ps.setString(19,mem.getBreathe());
			ps.setString(20,mem.getHeart());
			ps.setString(21,mem.getStom());
			ps.setString(22,mem.getCpt());
			ps.setString(23,mem.getRegId());
			ps.setBytes(24,photo);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int delete(int id) {
		int count = 0;
		String sql = "DELETE FROM MEM WHERE id = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public byte[] getImage(int id) {
		String sql = "SELECT photo FROM MEM WHERE memno = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] image = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				image = rs.getBytes("photo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(image.length);
		return image;
	}

	@Override
	 public Mem findByMemno(int memno) 
	{
	  String sql = "SELECT memno, id,  acc, pwd, name, sex,"
	    + "bir, height, weight, phone, email, act_type, sos1, sos2, sos3, blood,"
	    + "sugar, oil, bone, breathe, heart, stom, cpt, regId  FROM mem WHERE memno = ?";
	  Connection conn = null;
	  PreparedStatement ps = null;
	  Mem mem = null;
	  try {
	   conn = DriverManager.getConnection(Common.URL, Common.USER,
	     Common.PASSWORD);
	   ps = conn.prepareStatement(sql);
	   ps.setInt(1, memno);
	   ResultSet rs = ps.executeQuery();
	   if (rs.next()) {
	    memno = rs.getInt(1);
	    String id = rs.getString(2);
	    String acc = rs.getString(3);
	    String pwd = rs.getString(4);
	    String name = rs.getString(5);
	    String sex = rs.getString(6);
	    Timestamp bir = rs.getTimestamp(7);
	    Integer height = rs.getInt(8);
	    Integer weight = rs.getInt(9);
	    String phone = rs.getString(10);
	    String email = rs.getString(11);
		String act_type = rs.getString(12);
	    String sos1 = rs.getString(13);
	    String sos2 = rs.getString(14);
	    String sos3 = rs.getString(15);
	    String blood = rs.getString(16);
	    String sugar = rs.getString(17);
	    String oil = rs.getString(18);
	    String bone = rs.getString(19);
	    String breathe =  rs.getString(20);
	    String heart = rs.getString(21);
	    String stom = rs.getString(22);
	    String cpt = rs.getString(23);
	    String regId = rs.getString(24);
	    
		mem = new Mem(memno,id, acc, pwd, name, sex, bir, height, weight, phone,
	      email, act_type, sos1, sos2, sos3, blood, sugar, oil, bone, breathe,
	      heart, stom, cpt, regId);
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	    if (ps != null) {
	     ps.close();
	    }
	    if (conn != null) {
	     conn.close();
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }
	  return mem;
	 }
	

	@Override
	public List<Mem> getAll() {
//		String sql = "SELECT id, name, phoneNo, address, latitude, longitude "
//				+ "FROM Spot ORDER BY timestamp DESC;";
//		Connection connection = null;
//		PreparedStatement ps = null;
//		List<Mem> spotList = new ArrayList<Mem>();
//		try {
//			connection = DriverManager.getConnection(Common.URL, Common.USER,
//					Common.PASSWORD);
//			ps = connection.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String name = rs.getString(2);
//				String phoneNo = rs.getString(3);
//				String address = rs.getString(4);
//				double latitude = rs.getDouble(5);
//				double longitude = rs.getDouble(6);
//				Mem spot = new Mem(id, name, phoneNo, address, latitude,
//						longitude);
//				spotList.add(spot);
//			}
//			return spotList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return spotList;
		return null;
	}
	
	@Override
	public int updateRegId(int memno, String regId) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "UPDATE MEM SET regId = ? WHERE memno = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, regId);
			ps.setInt(2, memno);
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int deleteRegId(int memno) {
		int count = 0;
		String sql = "UPDATE MEM SET regId = null WHERE memno = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, memno);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
