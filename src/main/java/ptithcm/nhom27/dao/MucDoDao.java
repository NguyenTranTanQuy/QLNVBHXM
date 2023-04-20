package ptithcm.nhom27.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.MucdoEntity;

@Repository(value = "MucDoDao")
@Transactional
public class MucDoDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<MucdoEntity> getAllMucdo() {
		Session session = sessionFactory.openSession();
		String hql = "FROM MucdoEntity";
		Query query = session.createQuery(hql);
		List<MucdoEntity> lmucdo = query.getResultList();
		return lmucdo;
	}
	
	public MucdoEntity getMucdobyid(int id) {
		Session session = sessionFactory.openSession();
		return (MucdoEntity) session.get(MucdoEntity.class, id);
	}
}
