package ptithcm.nhom27.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.LuongEntity;

@Repository(value = "LuongDao")
@Transactional
public class LuongDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<LuongEntity> getAllBacluong(){
		Session session = sessionFactory.openSession();
		String hql = "From LuongEntity";
		Query query = session.createQuery(hql);
		return query.getResultList();
	}
	
	public LuongEntity getbacluong(int bacluong) {
		List<LuongEntity> list = getAllBacluong();
		return list.stream().filter(o->o.getBacluong()==bacluong).findFirst().orElse(null);
	}
}
