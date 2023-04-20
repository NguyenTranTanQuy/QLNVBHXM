package ptithcm.nhom27.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.ChucVuEntity;

@Repository(value = "ChucVuDao")
@Transactional
public class ChucVuDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public ChucVuEntity getE() {
		Session session = sessionFactory.openSession();
		return (ChucVuEntity)session.get(ChucVuEntity.class, "E");
	}
}
