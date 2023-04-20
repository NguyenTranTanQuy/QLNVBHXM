package ptithcm.nhom27.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CT_TBEntity;
import ptithcm.nhom27.entity.CT_TBKey;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.ThongBaoEntity;

@Repository(value = "CT_TBDao")
@Transactional
public class CT_TBDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean addOneNV(NhanVienEntity nv, ThongBaoEntity tb) {
		CT_TBEntity cttb = new CT_TBEntity();
		CT_TBKey key = new CT_TBKey(nv.getManv(), tb.getId());
		cttb.setManventity(nv);
		cttb.setMatbentity(tb);
		cttb.setIdtb(key);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(cttb);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return false;
		}  finally {
			session.close();
		}
	}
	
	public void autoAddAllNV(List<NhanVienEntity> lnv, ThongBaoEntity tb) {
		lnv.forEach(o->{
			CT_TBEntity cttb = new CT_TBEntity();
			CT_TBKey key = new CT_TBKey(o.getManv(), tb.getId());
			cttb.setIdtb(key);
			cttb.setManventity(o);
			cttb.setMatbentity(tb);
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(cttb);
				t.commit();
			} catch (Exception e) {
				e.printStackTrace();
				t.rollback();
			}  finally {
				session.close();
			}
		});
	}
	
	public List<ThongBaoEntity> getAllTBMaNV(String manv) {
		Session session = sessionFactory.openSession();
		String hql = "FROM CT_TBEntity WHERE manventity.manv=:manv";
		Query query = session.createQuery(hql);
		query.setParameter("manv", manv);
		List<CT_TBEntity> cttb = query.getResultList();
		List<ThongBaoEntity> ltb = new ArrayList<>();
		cttb.forEach(o->{
			ltb.add(o.getMatbentity());
		});
		return ltb;
	}
	
}
