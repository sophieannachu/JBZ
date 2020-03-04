package iii.jbz.mobile.sleepcheck;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;
import iii.jbz.mobile.bpcheck.BPCheckVO;

public class SleepCheckDAO implements SleepCheckDAO_interface {

	@Override
	public int insert(SleepCheckVO sleepCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(sleepCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int update(SleepCheckVO sleepCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(sleepCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int delete(Integer sleepCheckno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			SleepCheckVO sleepCheckVO = (SleepCheckVO) session.get(SleepCheckVO.class, sleepCheckno);
			session.delete(sleepCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public List<SleepCheckVO> getAllbymemno(Integer memno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<SleepCheckVO> list = null;
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(SleepCheckVO.class);
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
