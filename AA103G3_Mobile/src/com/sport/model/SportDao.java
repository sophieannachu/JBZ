package com.sport.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SportDao implements SportDao_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ONE_STMT = " SELECT sportrec_no, memno, type_no, "
			+ "to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, sport_duration, "
			+ "gps_distance, sport_cal, gps_track FROM sportrec WHERE sportrec_no =  ?";
	private static final String GROUPBYDATE = "SELECT to_char(sport_date,'yyyy-mm') sport_date FROM sportrec WHERE (type_no between 50 and 53) and memno =? GROUP BY to_char(sport_date,'yyyy-mm')";
	private static final String GET_ALLWATCHBYMEM_STMT = "SELECT sportrec_no, memno, type_no, to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, "
			+ "sport_duration, gps_distance, sport_cal, gps_track FROM sportrec WHERE "
			+ "(type_no between 54 and 63) and memno =  ? ORDER BY sport_date DESC";
	private static final String GET_ALLBYDATE_STMT = " SELECT sportrec_no, memno, type_no, "
			+ "to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, sport_duration, "
			+ "gps_distance, sport_cal, gps_track FROM sportrec WHERE (type_no between 50 and 53) and memno = ? and to_char(sport_date,'yyyy-mm')=?"
			+ " ORDER BY sport_date DESC";
	private static final String GET_GPSSUMBYDATE_STMT = "SELECT to_char(sport_date,'yyyy-mm-dd') sport_date, sum(sport_duration) sport_duration, "
			+ "sum(gps_distance) gps_distance, sum(sport_cal) sport_cal FROM sportrec WHERE (type_no between 50 and 53) and memno = ?"
			+ " group by to_char(sport_date,'yyyy-mm-dd') ORDER BY sport_date DESC";
	private static final String GET_SUMBYDATE_STMT = "SELECT to_char(sport_date,'yyyy-mm-dd') sport_date, sum(sport_duration) sport_duration, "
			+ "sum(sport_cal) sport_cal FROM sportrec WHERE memno = ?"
			+ " group by to_char(sport_date,'yyyy-mm-dd') ORDER BY sport_date DESC";
	private static final String GET_WATCHSUMBYDATE_STMT = "SELECT to_char(sport_date,'yyyy-mm-dd') sport_date, sum(sport_duration) sport_duration, "
			+ "sum(sport_cal) sport_cal FROM sportrec WHERE (type_no between 54 and 63) and memno = ?"
			+ " group by to_char(sport_date,'yyyy-mm-dd') ORDER BY sport_date DESC";
	private static final String GET_AVGBYDATE_STMT = "select sport_date, round(avg(sport_duration),0) sport_duration,round(avg(sport_cal),0) "
			+ "sport_cal from (SELECT memno,to_char(sport_date,'yyyy-mm-dd') sport_date, sum(sport_duration) sport_duration ,sum(sport_cal) "
			+ "sport_cal FROM sportrec group by to_char(sport_date,'yyyy-mm-dd'),memno ORDER BY sport_date DESc) group by sport_date order by "
			+ "sport_date desc";
	private static final String GET_ALLSUM_STMT = "SELECT sum(sport_duration) sport_duration ,sum(sport_cal) "
			+ "sport_cal FROM sportrec";
	private static final String GET_SUMBYMEM_STMT = "SELECT sum(sport_duration) sport_duration ,sum(sport_cal) "
			+ "sport_cal FROM sportrec where memno=?";
	private static final String GET_ALLBYMEM_STMT = "SELECT sportrec_no, memno, type_no, to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, "
			+ "sport_duration, gps_distance, sport_cal, gps_track FROM sportrec WHERE "
			+ "memno =  ? ORDER BY sport_date DESC";
	private static final String GET_DURWEEK_STMT ="SELECT round((sum(sport_duration)/60), 0) sport_duration FROM sportrec WHERE memno = ? and sport_date between ?-6 and ?+1";
	private static final String GET_TOTALDURTIMES_STMT =
	"select count(*) times from (SELECT to_char(sport_date,'yyyy-mm-dd'), sum(sport_duration) sport_duration from sportrec where memno=? and trunc(sport_date, 'DD') between trunc(?, 'DD') and trunc(?, 'DD') group by to_char(sport_date,'yyyy-mm-dd') having sum(sport_duration) > 1800)";
	
	@Override
	public List<Sport> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sport> findGPSByMemno(Integer memno) {
		String sql = "SELECT sportrec_no, memno, type_no, to_char(sport_date,'yyyy-mm-dd hh:mm:ss') sport_date, sport_duration, gps_distance, sport_cal, gps_track FROM sportrec WHERE (type_no between 50 and 53) and memno =  ? ORDER BY sport_date DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<Sport> sportList = new ArrayList<Sport>();
			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);

				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance,
						sport_cal, gps_track);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int sportInsert(Sport sport) {
		int count = 0;
		String sql = "INSERT INTO sportrec"
				+ "(sportrec_no, memno, type_no, sport_date, sport_duration, gps_distance, sport_cal, gps_track)"
				+ "VALUES(gpsrec_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sport.getMemno());
			ps.setInt(2, sport.getType_no());
			ps.setTimestamp(3, sport.getSport_date());
			ps.setInt(4, sport.getSport_duration());
			ps.setFloat(5, sport.getGps_distance());
			ps.setDouble(6, sport.getSport_cal());
			ps.setString(7, sport.getGps_track());
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
	public int sportDelete(Integer sportrec_no) {
		int count = 0;
		String sql = "DELETE FROM sportrec WHERE sportrec_no = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sportrec_no);
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
	public List<Sport> getAllWatchByMemno(Integer memno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_ALLWATCHBYMEM_STMT);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<Sport> sportList = new ArrayList<Sport>();
			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);

				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance,
						sport_cal, gps_track);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<String> groupbydateGPS(Integer memno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> dateList = new ArrayList<String>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GROUPBYDATE);
			pstmt.setInt(1, memno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String date = rs.getString(1);
				dateList.add(date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dateList;
	}

	@Override
	public List<Sport> getAllGPSbydate(Integer memno, String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Sport> sportList = new ArrayList<Sport>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYDATE_STMT);
			pstmt.setInt(1, memno);
			pstmt.setString(2, date);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);

				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance,
						sport_cal, gps_track);
				sportList.add(sport);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sportList;
	}

	@Override
	public List<Sport> getGPSSumbydate(Integer memno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Sport> sportList = new ArrayList<Sport>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GPSSUMBYDATE_STMT);
			pstmt.setInt(1, memno);
			ResultSet rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				Timestamp sport_date = new java.sql.Timestamp(sdf.parse(rs.getString("sport_date")).getTime());
				Integer sport_duration = rs.getInt("sport_duration");
				Float gps_distance = rs.getFloat("gps_distance");
				Double sport_cal = rs.getDouble("sport_cal");

				Sport sport = new Sport(null, null, null, sport_date, sport_duration, gps_distance, sport_cal, null);
				sportList.add(sport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sportList;
	}

	@Override
	public Sport findByPrimaryKey(Integer sportrec_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		Sport sport = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, sportrec_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);
				sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance, sport_cal,
						gps_track);
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sport;
	}

	@Override
	public List<Sport> getAllSumbydate(Integer memno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Sport> sportList = new ArrayList<Sport>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SUMBYDATE_STMT);
			pstmt.setInt(1, memno);
			ResultSet rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				Timestamp sport_date = new java.sql.Timestamp(sdf.parse(rs.getString("sport_date")).getTime());
				Integer sport_duration = rs.getInt("sport_duration");
				Double sport_cal = rs.getDouble("sport_cal");

				Sport sport = new Sport(null, null, null, sport_date, sport_duration, null, sport_cal, null);
				sportList.add(sport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sportList;
	}

	@Override
	public List<Sport> getWatchSumbydate(Integer memno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Sport> sportList = new ArrayList<Sport>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_WATCHSUMBYDATE_STMT);
			pstmt.setInt(1, memno);
			ResultSet rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				Timestamp sport_date = new java.sql.Timestamp(sdf.parse(rs.getString("sport_date")).getTime());
				Integer sport_duration = rs.getInt("sport_duration");
				Double sport_cal = rs.getDouble("sport_cal");

				Sport sport = new Sport(null, null, null, sport_date, sport_duration, null, sport_cal, null);
				sportList.add(sport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sportList;
	}

	@Override
	public List<Sport> getAllAvgbydate() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Sport> sportList = new ArrayList<Sport>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AVGBYDATE_STMT);
			ResultSet rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				Timestamp sport_date = new java.sql.Timestamp(sdf.parse(rs.getString("sport_date")).getTime());
				Integer sport_duration = rs.getInt("sport_duration");
				Double sport_cal = rs.getDouble("sport_cal");

				Sport sport = new Sport(null, null, null, sport_date, sport_duration, null, sport_cal, null);
				sportList.add(sport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sportList;
	}

	@Override
	public Sport getAllSum() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		Sport sport=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLSUM_STMT);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer sport_duration = rs.getInt("sport_duration");
				Double sport_cal = rs.getDouble("sport_cal");

				 sport = new Sport(null, null, null,null, sport_duration, null, sport_cal, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sport;
	}

	@Override
	public Sport getSumByMem(Integer memno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		Sport sport=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SUMBYMEM_STMT);
			
			pstmt.setInt(1, memno);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer sport_duration = rs.getInt("sport_duration");
				Double sport_cal = rs.getDouble("sport_cal");

				 sport = new Sport(null, memno, null,null, sport_duration, null, sport_cal, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sport;
	}

	@Override
	public List<Sport> getAllByMemno(Integer memno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_ALLBYMEM_STMT);
			ps.setInt(1, memno);
			ResultSet rs = ps.executeQuery();
			List<Sport> sportList = new ArrayList<Sport>();
			while (rs.next()) {
				Integer sportrec_no = rs.getInt(1);
				Integer memnoOut = rs.getInt(2);
				Integer type_no = rs.getInt(3);
				Timestamp sport_date = rs.getTimestamp(4);
				Integer sport_duration = rs.getInt(5);
				Float gps_distance = rs.getFloat(6);
				Double sport_cal = rs.getDouble(7);
				String gps_track = rs.getString(8);

				Sport sport = new Sport(sportrec_no, memnoOut, type_no, sport_date, sport_duration, gps_distance,
						sport_cal, gps_track);
				sportList.add(sport);
			}
			return sportList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public Integer getDurWeek(Integer memno, Date date) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer duration= null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DURWEEK_STMT);
			pstmt.setInt(1, memno);
			pstmt.setDate(2, date);
			pstmt.setDate(3, date);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			duration=rs.getInt("sport_duration");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return duration;
	}
	
	@Override
	public Integer getDurCount(Integer memno, Date stdate, Date eddate) {
		// TODO Auto-generated method stub
//		BPCheckVO bpCheckVO=null;
		Integer times=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
	
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_TOTALDURTIMES_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setDate(2, stdate);
			pstmt.setDate(3, eddate);
//			pstmt.setInt(3, value);
			
	//		pstmt.setString(2, checkDate);
	
			rs=pstmt.executeQuery();
			
			rs.next();
			times=rs.getInt("times");

//			bpCheckVO=new BPCheckVO();
//			bpCheckVO.setBpCheckno(rs.getInt("bpCheckno"));
//			bpCheckVO.setsPressure(rs.getInt("sPressure"));
//			bpCheckVO.setdPressure(rs.getInt("dPressure"));
//			bpCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
//			bpCheckVO.setMemno(rs.getInt("memno"));
				
			
				
			
		}catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
		return times;
	}


}
