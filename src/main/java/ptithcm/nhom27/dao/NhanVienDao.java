package ptithcm.nhom27.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.HDLDEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;
import ptithcm.nhom27.entity.TaiKhoanEntity;


@Repository(value = "NhanVienDao")
@Transactional
public class NhanVienDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public int getQuantityNhanVien() {
		List<NhanVienEntity> lnv = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String hql = "FROM NhanVienEntity";
		Query query = session.createQuery(hql);
		lnv = query.getResultList();
		session.close();
		return lnv.size();
	}
	
	public List<NhanVienEntity> getAllNhanVien() {
		List<NhanVienEntity> lnv = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String hql = "FROM NhanVienEntity WHERE matk.chucvu.macv = 'E'";
		Query query = session.createQuery(hql);
		lnv = query.getResultList();
		session.close();
		return lnv;
	}
	
	public List<NhanVienEntity> getAllQuanLy() {
		List<NhanVienEntity> lnv = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String hql = "FROM NhanVienEntity WHERE matk.chucvu.macv = 'A'";
		Query query = session.createQuery(hql);
		lnv = query.getResultList();
		session.close();
		return lnv;
	}
	
	public NhanVienEntity getNhanVien(String manv) {
		Session session = sessionFactory.openSession();
		NhanVienEntity nv = session.get(NhanVienEntity.class, manv);
		session.close();
		return nv;
	}
	
	public boolean addNhanVien(NhanVienEntity nv) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(nv);
			t.commit();
			return true;
			
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	public boolean deleteNhanVien(NhanVienEntity nv) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(nv);
			t.commit();
			return true;
			
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
	}
	
	public String Createmnv() {
		return "NV"+String.valueOf(getQuantityNhanVien()+1);
	}
	
	public String getNameUser() {
		Session session = sessionFactory.openSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		TaiKhoanEntity tk = session.get(TaiKhoanEntity.class, name);
		session.close();
		return tk.getManv().getHo()+" "+tk.getManv().getTen();
	}
	
	
	public PhongBanEntity getPhongBan() {
		Session session = sessionFactory.openSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		TaiKhoanEntity tk = session.get(TaiKhoanEntity.class, name);
		session.close();
		return tk.getManv().getMapb();
	}
	
	public NhanVienEntity getNVLogin() {
		Session session = sessionFactory.openSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		TaiKhoanEntity tk = session.get(TaiKhoanEntity.class, name);
		session.close();
		return tk.getManv();
	}
	
	public String getPhongBanUser() {
		PhongBanEntity pb = getPhongBan();
		return pb.getTenpb();
	}
	
	public List<NhanVienEntity> getAllNhanVienOfPb() {
		List<NhanVienEntity> lnv = getAllNhanVien();
		String phongbanuser = getPhongBan().getMapb();
		return lnv.stream().filter(o->o.getMapb().getMapb().equals(phongbanuser)).collect(Collectors.toList());
	}
	
	
}
