package com.bgcheck.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;


public class BGCheckDAO implements BGCheckDAO_interface{
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
		
	private static final String GET_ALLBYMEM_STMT="from BGCheckVO where memno=:memno order by checkdate desc";

	private static final String GET_ALL_AVG_NEWEST_STMT=
			"SELECT avg(bgbfmeat) bgbfmeat,avg(bgafmeat) bgafmeat,avg(bgbfsleep) bgbfsleep from bgcheck where memno=? and to_char(checkdate,'yyyy-mm-dd') = " + 
			"(select to_char(max(checkdate),'yyyy-mm-dd') checkdate from bgcheck where memno=?)";
	private static final String GET_TOTALTIMES_STMT=
			"SELECT sum(count(*)) times from bgcheck where memno=? and trunc(checkdate, 'DD') between trunc(?, 'DD') and trunc(?, 'DD') group by to_char(checkdate,'yyyy-mm-dd')";
	private static final String GET_TODAYTIMES_STMT=
			"SELECT count(*) times from bgcheck where memno=? and to_char(checkdate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')";
	private static final String GET_GOALTIMES_STMT=
			"select count(*) times from (SELECT to_char(checkdate,'yyyy-mm-dd'), count(*) times from bpcheck where memno=? and trunc(checkdate, 'DD') between trunc(?, 'DD') and trunc(?, 'DD') group by to_char(checkdate,'yyyy-mm-dd') having count(*) >= ?)";
		
	
	@Override
	public void insert(BGCheckVO bgCheckVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bgCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	
	@Override
	public void update(BGCheckVO bgCheckVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bgCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer bgCheckno) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BGCheckVO bgCheckVO = (BGCheckVO) session.get(BGCheckVO.class, bgCheckno);
			session.delete(bgCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public BGCheckVO findByPrimaryKey(Integer bgCheckno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bgCheckVO = (BGCheckVO) session.get(BGCheckVO.class, bgCheckno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return bgCheckVO;
	}

	@Override
	public List<BGCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		List<BGCheckVO> list=new ArrayList<BGCheckVO>();
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
	public BGCheckVO getAllAvgNewest(Integer memno) {
		// TODO Auto-generated method stub
		BGCheckVO bgCheckVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		

		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_AVG_NEWEST_STMT);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, memno);

			
			rs=pstmt.executeQuery();
			
			rs.next();
			bgCheckVO=new BGCheckVO();
			bgCheckVO.setBgBfMeat(rs.getInt("bgbfmeat"));
			bgCheckVO.setBgAfMeat(rs.getInt("bgafmeat"));
			bgCheckVO.setBgBfSleep(rs.getInt("bgbfsleep"));
			
				
			
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
		return bgCheckVO;
	}
	
	@Override
	public Integer getTotalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate) {
		// TODO Auto-generated method stub
		Integer times=null;
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
	public Integer getGoalTimes(Integer memno, java.sql.Date stdate, java.sql.Date eddate, Integer value) {
		// TODO Auto-generated method stub
		Integer times=null;
		Integer count=0;
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
