package ptithcm.nhom27.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;
import ptithcm.nhom27.services.NhanVienService;

@Repository(value = "ThongBaoDao")
@Transactional
public class ThongBaoDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	NhanVienService nvService;
	
	public List<ThongBaoEntity> getAllThongBao() {
		Session session = sessionFactory.openSession();
		String hql = "FROM ThongBaoEntity";
		Query query = session.createQuery(hql);
		return query.getResultList();
	}
	
	public boolean addThongBao(ThongBaoEntity tb) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(tb);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public String createNewMaTb() {
		List<ThongBaoEntity> ltb = getAllThongBao();
		return "TB"+String.valueOf(ltb.size()+1);
	}
	
	public ThongBaoEntity autoCreateTBXP(String tenqd, Date date, int sotien) {
		ThongBaoEntity tb = new ThongBaoEntity();
		NhanVienEntity nv = nvService.getNVLogin();
		tb.setManvql(nv);
		tb.setNgaytb(date);
		
		if (sotien == 0)
			tb.setNoidung("Bạn đã vi phạm quy định với tên " +tenqd + " ngày "+date.toString());
		else 
			tb.setNoidung("Bạn đã vi phạm quy định với tên " +tenqd + " ngày "+date.toString()+ " với số tiền phạt là "+String.valueOf(sotien) +" VND");
		return tb;
	}
}
