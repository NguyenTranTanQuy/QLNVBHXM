package ptithcm.nhom27.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.entity.QuyDinhEntity;

@Repository(value = "QuyDinhDao")
@Transactional
public class QuyDinhDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public List<QuyDinhEntity> getAllQDPB(PhongBanEntity pb) {
		Session session = sessionFactory.openSession();
		String hql = "FROM QuyDinhEntity WHERE manvql.manv=:manv";
		Query query = session.createQuery(hql);
		query.setParameter("manv", pb.getManvql().getManv());
		return query.getResultList();
	}
	
	public boolean addQuyDinh(QuyDinhEntity qd) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(qd);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteQD(QuyDinhEntity qd) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(qd);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	public QuyDinhEntity getqdbyID(int id) {
		Session session = sessionFactory.openSession();
		return session.get(QuyDinhEntity.class, id);
	}
}
