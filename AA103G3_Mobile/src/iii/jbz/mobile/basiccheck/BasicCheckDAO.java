package iii.jbz.mobile.basiccheck;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;



public class BasicCheckDAO implements BasicCheckDAO_interface{
	
	@Override
	public int insert(BasicCheckVO basicCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(basicCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
		
	}
	@Override
	public int update(BasicCheckVO basicCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(basicCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
		
	}
	@Override
	public int delete(Integer basicCheckno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BasicCheckVO basicCheckVO = (BasicCheckVO) session.get(BasicCheckVO.class, basicCheckno);
			session.delete(basicCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
		
	}
	
	@Override
	public List<BasicCheckVO> getAllbymemno(Integer memno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<BasicCheckVO> list = null;
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(BasicCheckVO.class);
			query.add(Restrictions.eq("memno", memno));
			list = query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex; 
		}
	return list;
		
	}

	
	
}
