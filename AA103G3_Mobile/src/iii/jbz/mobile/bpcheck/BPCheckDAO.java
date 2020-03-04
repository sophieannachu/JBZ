package iii.jbz.mobile.bpcheck;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;
import iii.jbz.mobile.bgcheck.BGCheckVO;

public class BPCheckDAO implements BPCheckDAO_interface {

	@Override
	public int insert(BPCheckVO bpCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bpCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int update(BPCheckVO bpCheckVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bpCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public int delete(Integer bpCheckno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BPCheckVO bpCheckVO = (BPCheckVO) session.get(BPCheckVO.class, bpCheckno);
			session.delete(bpCheckVO);
			session.getTransaction().commit();
			return 1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			return 0;
		}
	}

	@Override
	public List<BPCheckVO> getAllbymemno(Integer memno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<BPCheckVO> list = null;
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(BPCheckVO.class);
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
