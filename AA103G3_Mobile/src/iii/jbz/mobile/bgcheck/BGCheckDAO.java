package iii.jbz.mobile.bgcheck;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;
import iii.jbz.mobile.basiccheck.BasicCheckVO;

public class BGCheckDAO implements BGCheckDAO_interface{

	@Override
	public int insert(BGCheckVO bgCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bgCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int update(BGCheckVO bgCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bgCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int delete(Integer bgCheckno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BGCheckVO bgCheckVO = (BGCheckVO) session.get(BGCheckVO.class, bgCheckno);
			session.delete(bgCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public List<BGCheckVO> getAllbymemno(Integer memno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<BGCheckVO> list = null;
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(BGCheckVO.class);
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
