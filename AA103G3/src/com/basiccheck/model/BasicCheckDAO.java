package com.basiccheck.model;


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

import org.hibernate.*;

import hibernate.util.HibernateUtil;



public class BasicCheckDAO implements BasicCheckDAO_interface{

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
//	HQL«ü¥O
	private static final String GET_ALLBYMEM_STMT="from BasicCheckVO where memno=:memno order by checkdate desc";
	private static final String GET_ALL_STMT = "from BasicCheckVO order by checkdate desc";
	private static final String GET_NEWEST_STMT=
			"from BasicCheckVO where memno=? and to_char(checkdate,'yyyy-mm-dd')=? order by checkdate desc";
	private static final String GET_AVG_NEWEST_WEIGHT_STMT2=
			"SELECT AVG(weight) weight,AVG(bmi) bmi,AVG(bmr) bmr, AVG(bFat) bFat,AVG(waisyline) waisyline from basiccheck " +
			"where memno=? and to_char(checkdate,'yyyy-mm-dd') =" +
			"(select to_char(max(checkdate),'yyyy-mm-dd') checkdate from basiccheck where weight is not null and memno=? and trunc(checkdate, 'DD') <= trunc(?, 'DD'))";
	private static final String GET_AVG_NEWEST_WAIST_STMT2=
			"SELECT AVG(weight) weight,AVG(bmi) bmi,AVG(bmr) bmr, AVG(bFat) bFat,AVG(waisyline) waisyline from basiccheck " +
			"where memno=? and to_char(checkdate,'yyyy-mm-dd') =" +
			"(select to_char(max(checkdate),'yyyy-mm-dd') checkdate from basiccheck where waisyline is not null and memno=? and trunc(checkdate, 'DD') <= trunc(?, 'DD'))";
	
	@Override
	public void insert(BasicCheckVO basicCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(basicCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(BasicCheckVO basicCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(basicCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer basicCheckno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BasicCheckVO basicCheckVO = (BasicCheckVO) session.get(BasicCheckVO.class, basicCheckno);
			session.delete(basicCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public BasicCheckVO findByPrimaryKey(Integer basicCheckno) {
		BasicCheckVO basicCheckVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			basicCheckVO = (BasicCheckVO) session.get(BasicCheckVO.class, basicCheckno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return basicCheckVO;
	}

	@Override
	public List<BasicCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		
		List<BasicCheckVO> list=new ArrayList<BasicCheckVO>();
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
	public List<BasicCheckVO> getAll() {
		List<BasicCheckVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public BasicCheckVO getByDateMem(Integer memno , Date checkDate) {
		List<BasicCheckVO> list = null;
		BasicCheckVO basicCheckVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			basicCheckVO= list.get(0);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return basicCheckVO;
	}
	
	@Override
	public BasicCheckVO findAVG_NEWEST_WEIGHT2(Integer memno, Date stdate) {
		// TODO Auto-generated method stub
		BasicCheckVO basicCheckVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_AVG_NEWEST_WEIGHT_STMT2);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, memno);
			pstmt.setDate(3, stdate);
			
			rs=pstmt.executeQuery();
			
			rs.next();
			basicCheckVO=new BasicCheckVO();
			basicCheckVO.setWeight(rs.getInt("weight"));
			basicCheckVO.setBmi(rs.getDouble("bmi"));
			basicCheckVO.setBmr(rs.getDouble("bmr"));
			basicCheckVO.setbFat(rs.getDouble("bFat"));
			basicCheckVO.setWaisyline(rs.getDouble("waisyline"));
			
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
		return basicCheckVO;
	}
	
	@Override
	public Double findAVG_NEWEST_WAIST2(Integer memno, Date stdate) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Double waistLine=null;
		
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_AVG_NEWEST_WAIST_STMT2);
			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, memno);
			pstmt.setDate(3, stdate);
			
			
			rs=pstmt.executeQuery();
			
			rs.next();
			waistLine=rs.getDouble("waisyline");
			
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
		return waistLine;
	}

}
