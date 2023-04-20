package ptithcm.nhom27.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.HDLDEntity;

@Repository(value = "HDLDDao")
@Transactional
public class HDLDDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public List<HDLDEntity> getAllHDLD() {
		Session session = sessionFactory.openSession();
		String hql = "FROM HDLDEntity";
		Query query = session.createQuery(hql);
		List<HDLDEntity> list = query.getResultList();
		session.close();
		return list;
	}
	
	public boolean addHDLD(HDLDEntity hdld) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(hdld);
			t.commit();
			return true;
			
		}catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		
	}
	
	public boolean deleteHDLD(HDLDEntity hdld) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(hdld);
			t.commit();
			return true;
			
		}catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		
	}
	
	public HDLDEntity getHDLD(String manv)
	{
		List<HDLDEntity> list = getAllHDLD();
		return list.stream().filter(o->manv.equals(o.getManv().getManv())).findFirst().orElse(null);
	}
	
	public int getQuantityHDLD() {
		Session session = sessionFactory.openSession();
		String hql = "FROM HDLDEntity";
		Query query = session.createQuery(hql);
		List<HDLDEntity> list = query.getResultList();
		session.close();
		return list.size();
	}
	
	public String createMaHDLD() {
		return "HDLD"+String.valueOf(getQuantityHDLD()+1);
	}
}
