package com.bpcheck.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class BPCheckDAO implements BPCheckDAO_interface{
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALLBYMEM_STMT="from BPCheckVO where memno=:memno order by checkdate desc";

	private static final String GET_NEWEST_STMT2=
			"SELECT bpCheckno,sPressure,dPressure,checkdate,memno from bpcheck where memno=? and to_char(checkdate,'YYYY-MM-DD HH24:MI') =" + 
			"(select to_char(max(checkdate),'YYYY-MM-DD HH24:MI') checkdate from bpcheck where memno=?)";
	private static final String GET_TOTALTIMES_STMT=
			"SELECT sum(count(*)) times from bpcheck where memno=? and trunc(checkdate, 'DD') between trunc(?, 'DD') and trunc(?, 'DD') group by to_char(checkdate,'yyyy-mm-dd')";
	private static final String GET_TODAYTIMES_STMT=
			"SELECT count(*) times from bpcheck where memno=? and to_char(checkdate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')";
	private static final String GET_GOALTIMES_STMT=
			"select count(*) times from (SELECT to_char(checkdate,'yyyy-mm-dd'), count(*) times from bpcheck where memno=? and trunc(checkdate, 'DD') between trunc(?, 'DD') and trunc(?, 'DD') group by to_char(checkdate,'yyyy-mm-dd') having count(*) >= ?)";
	
	
	
	@Override
	public void insert(BPCheckVO bpCheckVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bpCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(BPCheckVO bpCheckVO) {
		// TODO Auto-generated method stub
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				try {
					session.beginTransaction();
					session.saveOrUpdate(bpCheckVO);
					session.getTransaction().commit();
				} catch (RuntimeException ex) {
					session.getTransaction().rollback();
					throw ex;
				}
	}

	@Override
	public void delete(Integer bpCheckno) {
		// TODO Auto-generated method stub
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				try {
					session.beginTransaction();
					BPCheckVO bpCheckVO = (BPCheckVO) session.get(BPCheckVO.class, bpCheckno);
					session.delete(bpCheckVO);
					session.getTransaction().commit();
				} catch (RuntimeException ex) {
					session.getTransaction().rollback();
					throw ex;
				}
	}
	
	@Override
	public BPCheckVO findByPrimaryKey(Integer bpCheckno) {
		// TODO Auto-generated method stub
				BPCheckVO bpCheckVO = null;
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				try {
					session.beginTransaction();
					bpCheckVO = (BPCheckVO) session.get(BPCheckVO.class, bpCheckno);
					session.getTransaction().commit();
				} catch (RuntimeException ex) {
					session.getTransaction().rollback();
					throw ex;
				}
				return bpCheckVO;
	}	
	
//	@Override
//	public BPCheckVO findNewestByDate(Integer memno, java.sql.Date checkDate) {
//		// TODO Auto-generated method stub
//		BPCheckVO bpCheckVO=null;
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//
//		try {
//			con=ds.getConnection();
//			pstmt=con.prepareStatement(GET_NEWEST_STMT);
//			
//			pstmt.setInt(1, memno);
////			pstmt.setString(2, checkDate);
//			pstmt.setString(2, checkDate.toString());
//
//			
//			rs=pstmt.executeQuery();
//			
//			rs.next();
//			bpCheckVO=new BPCheckVO();
//			bpCheckVO.setBpCheckno(rs.getInt("bpCheckno"));
//			bpCheckVO.setsPressure(rs.getInt("sPressure"));
//			bpCheckVO.setdPressure(rs.getInt("dPressure"));
//			bpCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
//			bpCheckVO.setMemno(rs.getInt("memno"));
//				
//			
//				
//			
//		}catch (SQLException se) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		}finally{
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
//		return bpCheckVO;
//	}

	@Override
	public List<BPCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		List<BPCheckVO> list=new ArrayList<BPCheckVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALLBYMEM_STMT);
			query.setParameter("memno", memno);
			list = query.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;

	}
	
	@Override
	public BPCheckVO findNewestCheck(Integer memno) {
		// TODO Auto-generated method stub
		BPCheckVO bpCheckVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
	
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_NEWEST_STMT2);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, memno);
	
			
			rs=pstmt.executeQuery();
			
			rs.next();
			bpCheckVO=new BPCheckVO();
			bpCheckVO.setBpCheckno(rs.getInt("bpCheckno"));
			bpCheckVO.setsPressure(rs.getInt("sPressure"));
			bpCheckVO.setdPressure(rs.getInt("dPressure"));
			bpCheckVO.setCheckDate(rs.getTimestamp("checkdate"));
			bpCheckVO.setMemno(rs.getInt("memno"));
				
			
				
			
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
		return bpCheckVO;
	}
	
	@Override
	public Integer getTotalTimes(Integer memno, Date stdate, Date eddate) {
		// TODO Auto-generated method stub
		Integer times=null;
		Integer count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
	
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_TOTALTIMES_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setDate(2, stdate);
			pstmt.setDate(3, eddate);

			rs=pstmt.executeQuery();
			
			rs.next();
			times=rs.getInt("times");
			
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
	
	@Override
	public Integer getTodayTimes(Integer memno) {
		// TODO Auto-generated method stub
		Integer times=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
	
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_TODAYTIMES_STMT);
			
			pstmt.setInt(1, memno);
	
			rs=pstmt.executeQuery();
			
			rs.next();
			times=rs.getInt("times");
			
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
	
	@Override
	public Integer getGoalTimes(Integer memno, Date stdate, Date eddate, Integer value) {
		// TODO Auto-generated method stub
		Integer times=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
	
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_GOALTIMES_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setDate(2, stdate);
			pstmt.setDate(3, eddate);
			pstmt.setInt(4, value);
	
			rs=pstmt.executeQuery();
			
			rs.next();
			times=rs.getInt("times");
			
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
