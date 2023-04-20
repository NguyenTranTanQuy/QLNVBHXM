package ptithcm.nhom27.dao;

import java.sql.Time;
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

import ptithcm.nhom27.entity.CTLuongEntity;
import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.CT_LLVKey;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.LichLamViecService;

@Repository(value = "CT_LLVDao")
@Transactional
public class CT_LLVDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	NhanVienDao nvService;

	@Autowired
	LichLamViecService llvService;

	public boolean addoreditCTLLV(LichLamViecEntity llv, NhanVienEntity nv) {
		CT_LLVEntity ctllv = new CT_LLVEntity();
		CT_LLVKey key = new CT_LLVKey();
		key.setManv(nv.getManv());
		key.setIdllv(llv.getId());
		ctllv.setIdctllv(key);
		ctllv.setId(llv);
		ctllv.setManvlv(nv);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctllv);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public List<CT_LLVEntity> getAllCTLLV() {
		Session session = sessionFactory.openSession();
		String hql = "FROM CT_LLVEntity";
		Query query = session.createQuery(hql);
		return query.getResultList();
	}

	public List<CT_LLVEntity> getCTLLVofDate(Date date) {
		Session session = sessionFactory.openSession();
		String hql = "FROM CT_LLVEntity WHERE id.ngaylam=:date";
		Query query = session.createQuery(hql);
		query.setParameter("date", date);
		return query.getResultList();
	}

	public List<CT_LLVEntity> lichLamViecCuaNVNow(String manv) {
		List<CT_LLVEntity> lcllv = llvService.getCTLLVnow();
		List<CT_LLVEntity> lcllvnv = lcllv.stream().filter(o -> o.getIdctllv().getManv().equals(manv))
				.collect(Collectors.toList());
		return lcllvnv;
	}

//	public void addforeditCTLLVallNV(LichLamViecEntity llv) {
//		CT_LLVEntity ctllv = new CT_LLVEntity();
//		List<NhanVienEntity> listnv = nvService.getAllNhanVienOfPb();
//		ctllv.setIdllv(llv.getId());
//		listnv.forEach(o->{
//			ctllv.setManv(o.getManv());
//			ctllv.setManvlv(o);
//			ctllv.setId(llv);
//			Session session = sessionFactory.openSession();
//			Transaction t = session.beginTransaction();
//			try {
//				session.saveOrUpdate(ctllv);
//				t.commit();
//			} catch (Exception e) {
//				t.rollback();
//				e.printStackTrace();
//				return;
//			} finally {
//				session.close();
//			}
//		});
//	}

	public void deleteCTLLV(LichLamViecEntity llv) {
		List<CT_LLVEntity> lctllv = llv.getDsllv().stream().collect(Collectors.toList());
		lctllv.forEach(o -> {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(o);
				t.commit();
			} catch (Exception e) {
				e.printStackTrace();
				t.rollback();
			} finally {
				session.close();
			}
		});
	}
}
