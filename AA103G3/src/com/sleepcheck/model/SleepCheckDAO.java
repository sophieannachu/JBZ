package com.sleepcheck.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;


public class SleepCheckDAO implements SleepCheckDAO_interface{
	private static final String GET_ALLBYMEM_STMT="from SleepCheckVO where memno=:memno order by waketime desc";
	private static final String GET_ALL_STMT = "from SleepCheckVO order by waketime desc";
		
	@Override
	public void insert(SleepCheckVO sleepCheckVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(sleepCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(SleepCheckVO sleepCheckVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(sleepCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(Integer sleepCheckno) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			SleepCheckVO sleepCheckVO = (SleepCheckVO) session.get(SleepCheckVO.class, sleepCheckno);
			session.delete(sleepCheckVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public SleepCheckVO findByPrimaryKey(Integer sleepCheckno) {
		// TODO Auto-generated method stub
		SleepCheckVO sleepCheckVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			sleepCheckVO = (SleepCheckVO) session.get(SleepCheckVO.class, sleepCheckno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return sleepCheckVO;
	}

	@Override
	public List<SleepCheckVO> getAll() {
		// TODO Auto-generated method stub
		List<SleepCheckVO> list = null;
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
	public List<SleepCheckVO> getAllbymemno(Integer memno) {
		// TODO Auto-generated method stub
		List<SleepCheckVO> list=new ArrayList<SleepCheckVO>();
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
}
