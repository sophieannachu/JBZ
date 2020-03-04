package iii.jbz.mobile.foodrec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;

public class FoodListDAO implements FoodListDAO_interface {

	@Override
	public int insert(FoodListVO foodListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(foodListVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int delete(Integer fdrecno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			FoodListVO foodListVO = (FoodListVO) session.get(FoodListVO.class, fdrecno);
			session.delete(foodListVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public List<FoodListVO> getAllbymemno(Integer memno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<FoodListVO> list = null;
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(FoodListVO.class);
			query.add(Restrictions.eq("memno", memno));
			list = query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex; 
		}
	return list;
	}

	@Override
	public List<FoodVO> getAllFoods() {
		String GET_ALLFOODS_STMT = "FROM FoodVO ORDER BY fd_no";
		List<FoodVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALLFOODS_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<FoodVO> findByKeyWord(String fd_name) {
		String GET_KEYWORDFOODS_STMT = "FROM FoodVO WHERE fd_name like '%"+fd_name+"%' ORDER BY fd_no";
		List<FoodVO> list = new ArrayList<FoodVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_KEYWORDFOODS_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

}
